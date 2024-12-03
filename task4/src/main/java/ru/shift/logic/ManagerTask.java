package ru.shift.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.NotFunctionException;
import ru.shift.functions.Function;
import ru.shift.functions.InverseSquareFunction;
import ru.shift.functions.OneMyFunction;
import ru.shift.functions.TwoMyFunction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ManagerTask {
    private static final int COUNT_PROCESSOR = Runtime.getRuntime().availableProcessors();
    private static final Logger log = LoggerFactory.getLogger(ManagerTask.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    public void start() throws InterruptedException {
        int numberFunction = selectionFunction();
        long inputLimitNumber = getInputLimitNumber();

        log.info("Count processor '{}'", COUNT_PROCESSOR);

        double result;
        if (inputLimitNumber < COUNT_PROCESSOR) {
            result = calculateSingleThread(inputLimitNumber, numberFunction);
        } else {
            result = calculateMultiThread(inputLimitNumber, numberFunction);
        }

        log.info("Calculation result '{}'", result);
    }

    public long getInputLimitNumber() {
        long inputLimitNumber = -1;
        while (inputLimitNumber < 0) {
            System.out.print("Specify the number of function values from 0 to n: ");

            try {
                inputLimitNumber = SCANNER.nextLong();
            } catch (InputMismatchException e) {
                log.info("An integer is required");
                SCANNER.next();
            }
        }

        log.info("Input limit number '{}'", inputLimitNumber);
        return inputLimitNumber;
    }

    public int selectionFunction() {
        System.out.println("""
                1. 1 / n^2 = 1.6449340668
                2. 1 / 2^n = 2
                3. 1 / n - 1 / (n + 1) = 1""");

        int numberFunction = 0;
        while (numberFunction < 1 || numberFunction > 3) {
            System.out.print("Select function from 1 to 3: ");
            try {
                numberFunction = SCANNER.nextInt();
            } catch (InputMismatchException e) {
                log.info("An integer is required from 1 to 3");
                SCANNER.next();
            }
        }

        log.info("function '{}' selected", numberFunction);
        return numberFunction;
    }

    public double calculateSingleThread(long endRow, int numberFunction) {
        log.info("Calculating in one thread");

        Function function = getFunction(numberFunction);
        Task task = new Task(0, endRow, function);

        double result = 0;
        try {
            result = task.call();
        } catch (Exception e) {
            log.error("An error occurred", e);
        }

        return result;
    }

    private double calculateMultiThread(long inputLimitNumber, int numberFunction) throws InterruptedException {
        log.info("Calculating in multithreading");

        List<Callable<Double>> callables = createCollectTasks(inputLimitNumber, numberFunction);
        ExecutorService executor = Executors.newFixedThreadPool(COUNT_PROCESSOR);

        double sum = 0d;
        try {
            long startTime = System.currentTimeMillis();
            List<Future<Double>> futures = executor.invokeAll(callables);
            long endTime = System.currentTimeMillis();

            long timeElapsed = endTime - startTime;
            log.info("Program execution time '{}' ms", timeElapsed);

            for (Future<Double> futuresTasks : futures) {
                sum += futuresTasks.get();
            }

        } catch (InterruptedException e) {
            log.error("An error occurred", e);
            throw e;
        } catch (ExecutionException | CancellationException e) {
            log.error("An error occurred", e);
        } finally {
            executor.shutdown();
        }

        return sum;
    }

    public List<Callable<Double>> createCollectTasks(long inputLimitNumber, int numberFunction) {
        List<Callable<Double>> callables = new ArrayList<>();

        long countWholePart = inputLimitNumber / COUNT_PROCESSOR;
        long remainder = inputLimitNumber % COUNT_PROCESSOR;
        long beginningRow;
        long endingRow = 0;

        for (int i = 0; i < COUNT_PROCESSOR; i++) {

            if (endingRow != 0) {
                beginningRow = endingRow + 1;
            } else {
                beginningRow = endingRow;
            }

            if (i < (COUNT_PROCESSOR - remainder)) {
                endingRow += countWholePart;
            } else {
                endingRow += countWholePart + 1;
            }

            Function function = getFunction(numberFunction);
            Task task = new Task(beginningRow, endingRow, function);
            log.info("Create task: '{}'", task);

            callables.add(task);
        }

        return callables;
    }

    private Function getFunction(int numberFunction) {
        switch (numberFunction) {
            case 1 -> {
                return new InverseSquareFunction();
            }
            case 2 -> {
                return new OneMyFunction();
            }
            case 3 -> {
                return new TwoMyFunction();
            }
            default -> throw new NotFunctionException("Not supported function " + numberFunction);
        }
    }
}