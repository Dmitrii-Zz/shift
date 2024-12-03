package ru.shift.figure;

public abstract class Figure {
    private final String name;

    protected Figure (String name) {
        this.name = name;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    public String getName() {
        return name;
    }

    public String printFigure() {
        return "Тип фигуры: " + getName() + ";" + System.lineSeparator()
                + "Площадь: " + String.format("%.3f", calculateArea()) + ";" + System.lineSeparator()
                + "Периметр: " + String.format("%.3f", calculatePerimeter()) + ";" + System.lineSeparator();
    }
}