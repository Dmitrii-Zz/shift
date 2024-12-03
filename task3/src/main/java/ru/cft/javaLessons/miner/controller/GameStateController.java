package ru.cft.javaLessons.miner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.model.Difficulty;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.model.GameState;
import ru.cft.javaLessons.miner.view.LoseWindow;
import ru.cft.javaLessons.miner.view.MainWindow;
import ru.cft.javaLessons.miner.view.WinWindow;

import java.awt.event.ActionListener;

public class GameStateController implements GameStateListener {
    private static final Logger log = LoggerFactory.getLogger(GameStateController.class);
    private final MainWindow view;
    private final GameField gameField;

    public GameStateController(MainWindow view, GameField gameField) {
        this.view = view;
        this.gameField = gameField;
    }

    @Override
    public void changeGameState(GameState gameState) {

        switch (gameState) {
            case NEW -> newGame();
            case WIN -> createWinWindow();
            case LOSE -> createLoseWindow();
            default -> log.info("State '{}' skip", gameState);
        }
    }

    private void newGame() {
        Difficulty difficulty = gameField.getDifficulty();
        view.createGameField(difficulty.getRowCount(), difficulty.getColsCount());
        view.setBombsCount(gameField.getDifficulty().getMinesCount());
        view.setTimerValue(0);
    }

    private void createLoseWindow() {
        LoseWindow loseWindow = new LoseWindow(view);
        loseWindow.setNewGameListener(getActionListener());
        loseWindow.setVisible(true);
    }

    private void createWinWindow() {
        WinWindow winWindow = new WinWindow(view);
        winWindow.setNewGameListener(getActionListener());
        winWindow.setVisible(true);
    }

    private ActionListener getActionListener() {
        return e -> {
            gameField.newGame();
        };
    }
}