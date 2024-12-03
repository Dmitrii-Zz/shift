package ru.shift.build;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.figure.Figure;
import ru.shift.figure.Triangle;

public class TriangleBuilder implements FigureBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(TriangleBuilder.class);
    private static final String SEPARATOR = " ";
    private static final int SIDE_A_TRIANGLE = 0;
    private static final int SIDE_B_TRIANGLE = 1;
    private static final int SIDE_C_TRIANGLE = 2;

    @Override
    public Figure buildFigure(String parameters) {
        String[] param = parameters.split(SEPARATOR);

        if (param.length >= 4) {
            throw new InputParametersNotValidateException("Неверно заданы параметры во входном файле!");
        }

        try {
            double sideA = Double.parseDouble(param[SIDE_A_TRIANGLE]);
            double sideB = Double.parseDouble(param[SIDE_B_TRIANGLE]);
            double sideC = Double.parseDouble(param[SIDE_C_TRIANGLE]);

            return new Triangle(sideA, sideB, sideC);
        } catch (NumberFormatException e) {
            LOG.error("Заданы нечисловые параметры фигуры!", e);
            throw new InvalidInputParamException("Введено нечисловое значение для парметров фигуры!");
        }

    }
}
