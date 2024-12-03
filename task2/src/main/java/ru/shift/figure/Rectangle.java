package ru.shift.figure;

import ru.shift.exceptions.InputParametersNotValidateException;

public class Rectangle extends Figure {
    private static final String NAME_FIGURE = "Прямоугольник";
    private final double width;
    private final double length;

    public Rectangle(double sideA, double sideB) {
        super(NAME_FIGURE);

        if (sideA <= 0 || sideB <= 0) {
            throw new InputParametersNotValidateException
                    ("Длины сторон прямоугольника не могут быть равными 0 или быть меньше 0!");
        }

        if (sideA > sideB) {
            length = sideA;
            width = sideB;
        } else {
            length = sideB;
            width = sideA;
        }
    }

    @Override
    public double calculateArea() {
        return width * length;
    }

    @Override
    public double calculatePerimeter() {
       return width * 2 + length * 2;
    }

    public double calculateDiagonal() {
        return Math.sqrt(width * width + length * length);
    }

    @Override
    public String printFigure() {
        return super.printFigure()
                + "Длина диагонали: " + String.format("%.3f", calculateDiagonal()) + ";" + System.lineSeparator()
                + "Длина: " + String.format("%.3f", length) + ";" + System.lineSeparator()
                + "Ширина: " + String.format("%.3f", width) + ".";
    }
}
