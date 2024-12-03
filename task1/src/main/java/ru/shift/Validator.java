package ru.shift;

public class Validator {

    private static final int MIN_TABLE_LENGTH = 1;
    private static final int MAX_TABLE_LENGTH = 32;

    public static void validateSize(int tableSize) throws InputNumberValidateException {

        if (tableSize < MIN_TABLE_LENGTH || tableSize > MAX_TABLE_LENGTH) {
            throw new InputNumberValidateException("Возможный диапазон таблицы: [1, 32]");
        }
    }
}