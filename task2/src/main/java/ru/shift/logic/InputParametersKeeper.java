package ru.shift.logic;

import ru.shift.exceptions.InputParametersNotValidateException;
import ru.shift.exceptions.InvalidInputParamException;

public class InputParametersKeeper {
    public static final String INPUT_FILE_OPTION = "-a";
    public static final String OUTPUT_FILE_OPTION = "-f";
    public static final String CONSOLE_OPTION = "-c";

    private String inputFilePath;
    private String outputFilePath;
    private boolean isPrintConsole = false;

    public void processInputParameters(String[] args) {

        if (args == null || args.length == 0) {
            throw new InvalidInputParamException("Не задана ни одна опция для запуска программы!");
        }

        for(int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case INPUT_FILE_OPTION:

                    if (inputFilePath != null) {
                        throw new InputParametersNotValidateException(
                                "Путь к файлу с входными параметрами нельзя задать дважды!");
                    }

                    if (args.length == (i + 1)) {
                        throw new InputParametersNotValidateException(
                                "Путь к файлу с входными параметрами не задан!");
                    }

                    inputFilePath = args[i + 1];
                    i++;
                    break;
                case OUTPUT_FILE_OPTION:

                    if (outputFilePath != null) {
                        throw new InputParametersNotValidateException(
                                "Путь к файлу для записи результатов нельзя задать дважды!");
                    }

                    if (args.length == (i + 1)) {
                        throw new InputParametersNotValidateException(
                                "Путь к файлу для записи результатов не задан!");
                    }

                    outputFilePath = args[i + 1];
                    i++;
                    break;
                case CONSOLE_OPTION:

                    if (isPrintConsole) {
                        throw new InputParametersNotValidateException("Опцию '-с' нельзя задать дважды!");
                    }

                    isPrintConsole = true;
                    break;
                default:
                    throw new InputParametersNotValidateException("Опция '" + args[i] + "' не поддерживается!");
            }
        }

        if (inputFilePath == null) {
            throw new InputParametersNotValidateException(
                    "Необходимо задать путь к файлу, с входными данными " +
                            "[-a <путь до файла с входными данными>]");
        }

        if (!isPrintConsole && (outputFilePath == null)) {
            isPrintConsole = true;
        }
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public boolean isPrintConsole() {
        return isPrintConsole;
    }
}