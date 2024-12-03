package ru.shift.print;

import ru.shift.figure.Figure;

public class ConsolePrinter implements FigurePrinter {

    @Override
    public void printFigure(Figure figure, String outputPath) {
        System.out.println(figure.printFigure());
    }
}
