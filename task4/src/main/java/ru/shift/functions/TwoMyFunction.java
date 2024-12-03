package ru.shift.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoMyFunction implements Function {
    private static final Logger log = LoggerFactory.getLogger(TwoMyFunction.class);

    @Override
    public double compute(long beginningRow, long endRow) {
        log.info("Started working from thread '{}'", Thread.currentThread().getName());

        double result = 0d;
        for (double i = beginningRow; i <= endRow; i++) {

            if (i == 0) {
                continue;
            }

            result += 1d / i - 1d / (i + 1d);
        }

        log.info("Finished working from thread '{}' with result '{}'", Thread.currentThread().getName(), result);

        return result;
    }
}
