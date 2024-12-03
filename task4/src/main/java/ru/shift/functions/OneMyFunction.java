package ru.shift.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneMyFunction implements Function {
    private static final Logger log = LoggerFactory.getLogger(OneMyFunction.class);

    @Override
    public double compute(long beginningRow, long endRow) {
        log.info("Started working from thread '{}'", Thread.currentThread().getName());

        double result = 0d;
        for (double i = beginningRow; i <= endRow; i++) {
            result += 1d / Math.pow(2d, i);
        }

        log.info("Finished working from thread '{}' with result '{}'", Thread.currentThread().getName(), result);

        return result;
    }
}