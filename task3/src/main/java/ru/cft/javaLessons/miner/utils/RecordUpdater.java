package ru.cft.javaLessons.miner.utils;

import ru.cft.javaLessons.miner.model.Difficulty;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.model.Record;

import java.util.Iterator;
import java.util.List;

public class RecordUpdater {
    private static final FileManager FILE_MANAGER = new FileManager();
    private final HighScorePresenter highScorePresenter;
    private final GameField gameField;
    private final MyTimer myTimer;

    public RecordUpdater(GameField gameField, MyTimer myTimer, HighScorePresenter highScorePresenter) {
        this.highScorePresenter = highScorePresenter;
        this.gameField = gameField;
        this.myTimer = myTimer;
    }

    public void writeRecord(String name) {
        List<Record> records = FILE_MANAGER.readFileRecord();

        Iterator<Record> iterator = records.iterator();
        while(iterator.hasNext()) {
            Record record = iterator.next();
            if (record.getDifficulty() == gameField.getDifficulty()) {
                iterator.remove();
                break;
            }
        }

        Difficulty difficulty = gameField.getDifficulty();
        int second = myTimer.getSecond();
        records.add(new Record(difficulty, name, second));

        FILE_MANAGER.writeRecords(records);
        highScorePresenter.visibleRecord();
    }
}