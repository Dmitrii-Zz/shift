package ru.cft.javaLessons.miner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.view.*;

public class GameFieldController implements CellEventListener {
    private static final Logger log = LoggerFactory.getLogger(GameFieldController.class);
    private final GameField gameField;

    public GameFieldController(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        switch (buttonType) {
            case LEFT_BUTTON -> gameField.openCell(x, y);
            case RIGHT_BUTTON -> gameField.toggleFlag(x, y);
            case MIDDLE_BUTTON -> gameField.openCellsAroundCell(x, y);
            default -> log.info("button type '{}' not supported", buttonType);
        }
    }
}