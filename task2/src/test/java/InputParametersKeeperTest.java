import org.junit.jupiter.api.Test;
import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;
import ru.shift.logic.InputParametersKeeper;

import static org.junit.jupiter.api.Assertions.*;

class InputParametersKeeperTest {

    @Test
    void processInputParamWithoutPathInputFileArgsTest() {
        String[] args = {"-a"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithoutParamInputFileAndPathInputFileArgsTest() {
        String[] args = {"-c"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithInputPathArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt"};
        var keeperInParam = new InputParametersKeeper();
        keeperInParam.processInputParameters(args);

        assertEquals(args[1], keeperInParam.getInputFilePath());
        assertTrue(keeperInParam.isPrintConsole());
        assertNull(keeperInParam.getOutputFilePath());
    }

    @Test
    void processInputParamWithDoubleInputPathArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt", "-a"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithInputPathWithParamConsoleArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt", "-c"};
        var keeperInParam = new InputParametersKeeper();
        keeperInParam.processInputParameters(args);

        assertEquals(args[1], keeperInParam.getInputFilePath());
        assertTrue(keeperInParam.isPrintConsole());
        assertNull(keeperInParam.getOutputFilePath());
    }

    @Test
    void processInputParamWithInputPathWithDoubleParamConsoleArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt", "-c", "-c"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithAllInputArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt",
                "-f", ".\\task2\\src\\test\\resources\\outputTest.txt"};
        var keeperInParam = new InputParametersKeeper();
        keeperInParam.processInputParameters(args);

        assertEquals(args[1], keeperInParam.getInputFilePath());
        assertEquals(args[3], keeperInParam.getOutputFilePath());
    }

    @Test
    void processInputParamWithDoubleParamOutPathArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt",
                "-f", ".\\task2\\src\\test\\resources\\outputTest.txt", "-f"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithoutOutputPathArgsTest() {
        String[] args = {"-a", ".\\task2\\src\\test\\resources\\inputTest.txt", "-f"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWithoutParamTest() {
        String[] args = {};
        assertThrows(InvalidInputParamException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }

    @Test
    void processInputParamWhenParamNullTest() {
        assertThrows(InvalidInputParamException.class,
                () -> new InputParametersKeeper().processInputParameters(null));
    }

    @Test
    void processInputParamWithNotExistsParamTest() {
        String[] args = {"-d"};
        assertThrows(InputParametersNotValidateException.class,
                () -> new InputParametersKeeper().processInputParameters(args));
    }
}