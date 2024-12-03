package ru.shift.build;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.figure.Circle;
import ru.shift.figure.Figure;

public class CircleBuilder implements FigureBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(CircleBuilder.class);
    private static final int RADIUS_CIRCLE = 0;
    private static final String SEPARATOR = " ";

    @Override
    public Figure buildFigure(String parameters) {

        String[] param = parameters.split(SEPARATOR);

        if (param.length >= 2) {
            throw new InputParametersNotValidateException("Неверно заданы параметры во входном файле!");
        }

        try {
            return new Circle(Double.parseDouble(param[RADIUS_CIRCLE]));
        } catch (NumberFormatException e) {
            LOG.error("Заданы нечисловые параметры фигуры!", e);
            throw new InvalidInputParamException("Введено нечисловое значение для парметров фигуры!");
        }
    }
}
