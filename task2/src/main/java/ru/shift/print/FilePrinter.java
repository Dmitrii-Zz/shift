package ru.shift.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.figure.Figure;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePrinter implements FigurePrinter {
    private static final Logger LOG = LoggerFactory.getLogger(FilePrinter.class);

    @Override
    public void printFigure(Figure figure, String outputFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            LOG.info("Печатаем фигуру в файл '{}'", outputFile);
            pw.print(figure.printFigure());
        } catch (IOException | SecurityException e) {
            LOG.error("Произошла ошибка!", e);
        }
    }
}