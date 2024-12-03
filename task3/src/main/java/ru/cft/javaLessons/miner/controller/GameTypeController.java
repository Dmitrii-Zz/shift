package ru.cft.javaLessons.miner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.model.Difficulty;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.view.GameType;
import ru.cft.javaLessons.miner.view.GameTypeListener;
import ru.cft.javaLessons.miner.view.MainWindow;

public class GameTypeController implements GameTypeListener {
    private static final Logger log = LoggerFactory.getLogger(GameTypeController.class);
    private final GameField gameField;
    private final MainWindow view;

    public GameTypeController(MainWindow view, GameField gameField) {
        this.view = view;
        this.gameField = gameField;
    }

    @Override
    public void onGameTypeChanged(GameType gameType) {

        switch (gameType) {
            case NOVICE -> gameField.setDifficulty(Difficulty.NOVICE);
            case MEDIUM -> gameField.setDifficulty(Difficulty.MEDIUM);
            case EXPERT -> gameField.setDifficulty(Difficulty.EXPERT);
            default -> log.info("Game type '{}' not supported", gameType);
        }
        newGame();
    }

    private void newGame() {
        gameField.newGame();
        Difficulty difficulty = gameField.getDifficulty();
        view.createGameField(difficulty.getRowCount(), difficulty.getColsCount());
        view.setBombsCount(difficulty.getMinesCount());
    }
}