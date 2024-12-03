package ru.cft.javaLessons.miner.model;

public enum Difficulty {
    NOVICE(9, 9, 10),
    MEDIUM(16, 16, 40),
    EXPERT(16, 30, 99);

    private final int rowCount;
    private final int colsCount;
    private final int minesCount;

    Difficulty(int rowCount, int colsCount, int minesCount) {
        this.rowCount = rowCount;
        this.colsCount = colsCount;
        this.minesCount = minesCount;
    }

    public int getColsCount() {
        return colsCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getMinesCount() {
        return minesCount;
    }
}