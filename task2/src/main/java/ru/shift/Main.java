package ru.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.exceptions.FileNotFoundMyException;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.figure.Figure;
import ru.shift.logic.*;
import ru.shift.print.ConsolePrinter;
import ru.shift.print.FilePrinter;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        InputParametersKeeper inputParametersKeeper = new InputParametersKeeper();

        try {
            inputParametersKeeper.processInputParameters(args);
            String[] content = new FileManager().readFile(inputParametersKeeper.getInputFilePath());
            Figure figure = new FigureFactory().createFigure(content);

            if (inputParametersKeeper.isPrintConsole()) {
                new ConsolePrinter().printFigure(figure, null);
            } else {
                new FilePrinter().printFigure(figure, inputParametersKeeper.getOutputFilePath());
            }

        } catch (InputParametersNotValidateException | FileNotFoundMyException | InvalidInputParamException e) {
            LOG.error("Возникла ошибка: ", e);
        } catch (NumberFormatException e) {
            LOG.error("Заданы нечисловые параметры фигуры!", e);
        }
    }
}