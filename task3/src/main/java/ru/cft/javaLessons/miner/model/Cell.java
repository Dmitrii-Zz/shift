package ru.cft.javaLessons.miner.model;

public class Cell {
    private int countMinesAround;
    private boolean isOpen;
    private boolean isMin;
    private boolean isFlag;
    private boolean isCheck;

    public Cell() {
        isCheck = false;
        isFlag = false;
        isOpen = false;
        isMin = false;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsMin(boolean isMin) {
        this.isMin = isMin;
    }

    public boolean isMin() {
        return isMin;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setCountMinesAround(int countMinesAround) {
        this.countMinesAround = countMinesAround;
    }

    public int getCountMinesAround() {
        return countMinesAround;
    }

    public boolean placeFlag() {
        isFlag = !isFlag;
        return isFlag;
    }
}