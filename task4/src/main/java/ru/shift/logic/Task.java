package ru.shift.logic;

import ru.shift.functions.Function;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {
    Function function;
    long beginningRow;
    long endRow;

    public Task(long beginningRow, long endRow, Function function) {
        this.beginningRow = beginningRow;
        this.endRow = endRow;
        this.function = function;
    }

    @Override
    public Double call() {
        return function.compute(beginningRow, endRow);
    }

    @Override
    public String toString() {
        return "Beginning row " + beginningRow
                + " end row " + endRow;
    }
}