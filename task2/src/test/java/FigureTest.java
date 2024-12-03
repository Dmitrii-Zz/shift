import org.junit.jupiter.api.Test;
import ru.shift.build.CircleBuilder;
import ru.shift.build.RectangleBuilder;
import ru.shift.build.TriangleBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FigureTest {

    @Test
    void createCircleWhenValidParamTest() {
        String param = "5";

        String expectedToString = "Тип фигуры: Круг;" + System.lineSeparator()
                + "Площадь: 78,500;" + System.lineSeparator()
                + "Периметр: 31,400;" + System.lineSeparator()
                + "Радиус: 5,000;" + System.lineSeparator()
                + "Диаметр: 10,000.";

        assertEquals(expectedToString, new CircleBuilder().buildFigure(param).printFigure());
    }

    @Test
    void createTriangleWhenValidParamTest() {
        String param = "7 5 3";

        String expectedToString = "Тип фигуры: Треугольник;" + System.lineSeparator() +
                "Площадь: 6,495;" + System.lineSeparator() +
                "Периметр: 15,000;" + System.lineSeparator() +
                "Длина стороны A: 7,000, противолежащий угол: 120,000 град.;" + System.lineSeparator() +
                "Длина стороны B: 5,000, противолежащий угол: 38,213 град.;" + System.lineSeparator() +
                "Длина стороны C: 3,000, противолежащий угол: 21,787 град.";

        assertEquals(expectedToString, new TriangleBuilder().buildFigure(param).printFigure());
    }

    @Test
    void createTriangleWhenValidParamDiffOrderTest() {
        String param = "3 7 5";

        String expectedToString = "Тип фигуры: Треугольник;" + System.lineSeparator() +
                "Площадь: 6,495;" + System.lineSeparator() +
                "Периметр: 15,000;" + System.lineSeparator() +
                "Длина стороны A: 3,000, противолежащий угол: 21,787 град.;" + System.lineSeparator() +
                "Длина стороны B: 7,000, противолежащий угол: 120,000 град.;" + System.lineSeparator() +
                "Длина стороны C: 5,000, противолежащий угол: 38,213 град.";

        assertEquals(expectedToString, new TriangleBuilder().buildFigure(param).printFigure());
    }

    @Test
    void createTriangleWhenValidParamSecondDiffOrderTest() {
        String param = "5 3 7";

        String expectedToString = "Тип фигуры: Треугольник;" + System.lineSeparator() +
                "Площадь: 6,495;" + System.lineSeparator() +
                "Периметр: 15,000;" + System.lineSeparator() +
                "Длина стороны A: 5,000, противолежащий угол: 38,213 град.;" + System.lineSeparator() +
                "Длина стороны B: 3,000, противолежащий угол: 21,787 град.;" + System.lineSeparator() +
                "Длина стороны C: 7,000, противолежащий угол: 120,000 град.";

        assertEquals(expectedToString, new TriangleBuilder().buildFigure(param).printFigure());
    }

    @Test
    void createRectangleWhenFirstValidParam() {
        String param = "5 10";

        String expectedToString = "Тип фигуры: Прямоугольник;" + System.lineSeparator()
                + "Площадь: 50,000;" + System.lineSeparator()
                + "Периметр: 30,000;" + System.lineSeparator()
                + "Длина диагонали: 11,180;" + System.lineSeparator()
                + "Длина: 10,000;" + System.lineSeparator()
                + "Ширина: 5,000.";

        assertEquals(expectedToString, new RectangleBuilder().buildFigure(param).printFigure());
    }

    @Test
    void createRectangleWhenSecondValidParam() {
        String param = "10 5";

        String expectedToString = "Тип фигуры: Прямоугольник;" + System.lineSeparator()
                + "Площадь: 50,000;" + System.lineSeparator()
                + "Периметр: 30,000;" + System.lineSeparator()
                + "Длина диагонали: 11,180;" + System.lineSeparator()
                + "Длина: 10,000;" + System.lineSeparator()
                + "Ширина: 5,000.";

        assertEquals(expectedToString, new RectangleBuilder().buildFigure(param).printFigure());
    }
}