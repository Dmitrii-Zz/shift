package ru.shift.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.build.CircleBuilder;
import ru.shift.build.RectangleBuilder;
import ru.shift.build.TriangleBuilder;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.figure.*;

import java.util.Arrays;

public class FigureFactory {
    private static final Logger LOG = LoggerFactory.getLogger(FigureFactory.class);
    private static final int FIGURE_TYPE_INDEX = 0;
    public static final int FIGURE_PARAM_INDEX = 1;

    public Figure createFigure(String[] paramFigureStr) {
        LOG.info("Пробуем создать фигуру из '{}'", Arrays.toString(paramFigureStr));

        try {
            switch (paramFigureStr[FIGURE_TYPE_INDEX]) {
                case "CIRCLE" -> {
                    return new CircleBuilder().buildFigure(paramFigureStr[FIGURE_PARAM_INDEX]);
                }
                case "RECTANGLE" -> {
                    return new RectangleBuilder().buildFigure(paramFigureStr[FIGURE_PARAM_INDEX]);
                }
                case "TRIANGLE" -> {
                    return new TriangleBuilder().buildFigure(paramFigureStr[FIGURE_PARAM_INDEX]);
                }
                default -> throw new InvalidInputParamException("Тип фигуры'"
                        + paramFigureStr[FIGURE_TYPE_INDEX]
                        + "' не предусмотрен!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputParametersNotValidateException("The input parameters are incorrectly specified!");
        }
    }
}