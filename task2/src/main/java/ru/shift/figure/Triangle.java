package ru.shift.figure;

import ru.shift.exceptions.InputParametersNotValidateException;

public class Triangle extends Figure {
    private static final String NAME_FIGURE = "Треугольник";
    private static final double SUM_ANGLES_TRIANGLE = 180;

    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        super(NAME_FIGURE);

        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new InputParametersNotValidateException
                    ("Длины сторон треугольника не могут быть равными 0 или быть меньше 0!");
        }

        if ((sideA + sideB) <= sideC || (sideA + sideC) <= sideB || (sideB + sideC) <= sideA) {
            throw new InputParametersNotValidateException("Треугольник с заданами сторонами не существует!");
        }

        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    /**
     * Метод считает площадь через полупериметр треугольника
     * @return площидь
     */
    @Override
    public double calculateArea() {
        double halfPerimeter = (sideA + sideB + sideC) / 2;
        return Math.sqrt(halfPerimeter
                * (halfPerimeter - sideA)
                * (halfPerimeter - sideB)
                * (halfPerimeter - sideC));
    }

    /**
     * Метод считает угол в градусах по теореме косинусов
     * @return возвращает значение угла в градусах
     */
    public double calculateAngleAb() {
        double cosAB = (sideA * sideA + sideB * sideB - sideC * sideC) / (2 * sideA * sideB);

        if (cosAB < 0) {
            return SUM_ANGLES_TRIANGLE - Math.toDegrees(Math.acos(Math.abs(cosAB)));
        }

        return Math.toDegrees(Math.acos(cosAB));
    }

    /**
     * Считаем угол в градусах по теореме косинусов
     * @return возвращает значение угла в градусах
     */
    public double calculateAngleBc() {
        double cosBC = (sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideC * sideB);

        if (cosBC < 0) {
            return SUM_ANGLES_TRIANGLE - Math.toDegrees(Math.acos(Math.abs(cosBC)));
        }

        return Math.toDegrees(Math.acos(cosBC));
    }

    /**
     * Считаем угол в градусах по теореме косинусов
     * @return возвращает значение угла в градусах
     */
    public double calculateAngleCa() {
        double cosCA = (sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideC * sideA);

        if (cosCA < 0) {
            return SUM_ANGLES_TRIANGLE - Math.toDegrees(Math.acos(Math.abs(cosCA)));
        }

        return Math.toDegrees(Math.acos(cosCA));
    }

    @Override
    public String printFigure() {
        return super.printFigure()
            + "Длина стороны A: " + String.format("%.3f", sideA)
            + ", противолежащий угол: " + String.format("%.3f", calculateAngleBc()) + " град." + ";"
            + System.lineSeparator()
            + "Длина стороны B: " + String.format("%.3f", sideB)
            + ", противолежащий угол: " + String.format("%.3f", calculateAngleCa()) + " град." + ";"
            + System.lineSeparator()
            + "Длина стороны C: " + String.format("%.3f", sideC)
            + ", противолежащий угол: " + String.format("%.3f", calculateAngleAb()) + " град.";
    }
}