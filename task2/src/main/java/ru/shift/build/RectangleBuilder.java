package ru.shift.build;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.figure.Figure;
import ru.shift.figure.Rectangle;

public class RectangleBuilder implements FigureBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(RectangleBuilder.class);
    private static final String SEPARATOR = " ";
    private static final int SIDE_A_RECTANGLE = 0;
    private static final int SIDE_B_RECTANGLE = 1;

    @Override
    public Figure buildFigure(String parameters) {

        String[] param = parameters.split(SEPARATOR);

        if (param.length >= 3) {
            throw new InputParametersNotValidateException("Неверно заданы параметры во входном файле!");
        }

        try {
            double sideA = Double.parseDouble(param[SIDE_A_RECTANGLE]);
            double sideB = Double.parseDouble(param[SIDE_B_RECTANGLE]);

            return new Rectangle(sideA, sideB);
        } catch (NumberFormatException e) {
            LOG.error("Заданы нечисловые параметры фигуры!", e);
            throw new InvalidInputParamException("Введено нечисловое значение для парметров фигуры!");
        }
    }
}
