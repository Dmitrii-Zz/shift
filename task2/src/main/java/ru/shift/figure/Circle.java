package ru.shift.figure;

import ru.shift.exceptions.InputParametersNotValidateException;

public class Circle extends Figure {
    private static final String NAME_FIGURE = "Круг";
    private static final double PI = 3.14;
    private final double radius;

    public Circle(double radius) {
        super(NAME_FIGURE);

        if (radius <= 0) {
            throw new InputParametersNotValidateException("Радиус круга не может быть равным 0 или быть меньше 0!");
        }

        this.radius = radius;
        calculateDiameter();
    }

    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }

    public double calculateDiameter() {
        return radius * 2;
    }

    @Override
    public String printFigure() {
        return super.printFigure()
                + "Радиус: " + String.format("%.3f", radius) + ";" + System.lineSeparator()
                + "Диаметр: " + String.format("%.3f", calculateDiameter()) + ".";
    }
}