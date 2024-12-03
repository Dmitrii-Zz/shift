package ru.shift;

public class Table {
    private static final String DIVIDING_DASH = "-";
    private static final String DIVIDING_PLUS = "+";
    private static final String DIVIDING_VERTICAL = "|";
    private static final String DIVIDING_SPACE = " ";

    private final int tableSize;
    private final int sizeFirstColumn;
    private final int sizeOtherColumn;
    private String dividingStrip;

    public Table (int tableSize) {
        this.tableSize = tableSize;
        this.sizeFirstColumn = String.valueOf(tableSize).length();
        this.sizeOtherColumn = String.valueOf(tableSize * tableSize).length();
    }

    public void printTable() {
        buildDividingStrip();
        buildRow();
    }

    private void buildDividingStrip() {
        StringBuilder dividingStrip = new StringBuilder();

        dividingStrip.append(System.lineSeparator());
        dividingStrip.append(DIVIDING_DASH.repeat(sizeFirstColumn));

        for (int i = 1; i <= tableSize; i++) {
            dividingStrip.append(DIVIDING_PLUS);
            dividingStrip.append(DIVIDING_DASH.repeat(sizeOtherColumn));
        }

        this.dividingStrip = dividingStrip.toString();
    }

    private void buildRow() {

        System.out.print(DIVIDING_SPACE.repeat(sizeFirstColumn));

        for (int i = 1; i <= tableSize; i++) {
            printNumber(i);
        }

        System.out.println(dividingStrip);

        for (int i = 1; i <= tableSize; i++) {
            System.out.print(DIVIDING_SPACE.repeat(sizeFirstColumn - String.valueOf(i).length()));
            System.out.print(i);

            for (int j = 1; j <= tableSize; j++) {
                printNumber(i * j);
            }

            System.out.println(dividingStrip);
        }
    }

    private int countSpace(int number) {
        return (sizeOtherColumn - String.valueOf(number).length());
    }

    private void printNumber(int number) {
        System.out.print(DIVIDING_VERTICAL + DIVIDING_SPACE.repeat(countSpace(number)) + number);
    }
}