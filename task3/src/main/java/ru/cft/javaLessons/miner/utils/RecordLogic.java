package ru.cft.javaLessons.miner.utils;

import ru.cft.javaLessons.miner.controller.GameStateListener;
import ru.cft.javaLessons.miner.model.GameState;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.model.Record;
import ru.cft.javaLessons.miner.view.RecordsWindow;

import java.util.List;

public class RecordLogic implements GameStateListener {
    private static final FileManager FILE_MANAGER = new FileManager();
    private final RecordsWindow recordsWindow;
    private final GameField gameField;
    private final MyTimer myTimer;

    public RecordLogic(RecordsWindow recordsWindow, GameField gameField, MyTimer myTimer) {
        this.recordsWindow = recordsWindow;
        this.gameField = gameField;
        this.myTimer = myTimer;
    }

    @Override
    public void changeGameState(GameState gameState) {
        if (gameState == GameState.WIN) {
            checkNewRecord();
        }
    }

    public void checkNewRecord() {
        List<Record> records = FILE_MANAGER.readFileRecord();

        for (Record record : records) {
            if (record.getDifficulty() == gameField.getDifficulty()) {
                if (myTimer.getSecond() < record.getSecond()) {
                    recordsWindow.setVisible(true);
                }
            }
        }
    }
}