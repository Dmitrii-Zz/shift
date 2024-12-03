import org.junit.jupiter.api.Test;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.logic.FigureFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FigureFactoryTest {

    @Test
    void buildFigureWithoutTypeFigureTest() {
        String[] param = {"HEXAGON"};
        assertThrows(InvalidInputParamException.class, () -> new FigureFactory().createFigure(param));
    }

    //тесты для круга
    @Test
    void buildCircleWithoutParamTest() {
        String[] param = {"CIRCLE"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildCircleWhenRadiusNegativeTest() {
        String[] param = {"CIRCLE", "-5"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    //тесты для прямоугольника
    @Test
    void buildRectangleWithoutParamTest() {
        String[] param = {"RECTANGLE"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildRectangleWhenMoreParamTest() {
        String[] param = {"RECTANGLE", "1 2 3"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildRectangleWhenSideANegativeTest() {
        String[] param = {"RECTANGLE", "-1 2"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildRectangleWhenSideBNegativeTest() {
        String param[] = {"RECTANGLE", "1 -2"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    //тесты для треугольника
    @Test
    void buildTriangleWithoutParamTest() {
        String[] param = {"TRIANGLE"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildTriangleWhenMoreParamTest() {
        String[] param = {"TRIANGLE", "1 2 3 4"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildTriangleWhenSideANegativeTest() {
        String[] param = {"TRIANGLE", "-1 2 2"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildTriangleWhenSideBNegativeTest() {
        String[] param = {"TRIANGLE", "1 -2 2"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildTriangleWhenSideCNegativeTest() {
        String[] param = {"TRIANGLE", "1 2 -2"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }

    @Test
    void buildTriangleWhenTriangleNotExist() {
        String[] param = {"TRIANGLE", "1 2 3"};
        assertThrows(InputParametersNotValidateException.class, () -> new FigureFactory().createFigure(param));
    }
}