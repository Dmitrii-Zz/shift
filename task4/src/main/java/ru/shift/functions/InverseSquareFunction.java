package ru.shift.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InverseSquareFunction implements Function {
    private static final Logger log = LoggerFactory.getLogger(InverseSquareFunction.class);

    @Override
    public double compute(long beginningRow, long endRow) {
        log.info("Started working from thread '{}'", Thread.currentThread().getName());

        double result = 0d;
        for (double i = beginningRow; i <= endRow; i++) {

            if (i == 0) {
                continue;
            }

            result += 1.0 / (i * i);
        }

        log.info("Finished working from thread '{}' with result '{}'", Thread.currentThread().getName(), result);

        return result;
    }
}