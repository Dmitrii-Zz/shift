package ru.cft.javaLessons.miner.utils;

import ru.cft.javaLessons.miner.model.Record;
import ru.cft.javaLessons.miner.view.HighScoresWindow;

import java.util.List;

public class HighScorePresenter {
    private static final FileManager FILE_MANAGER = new FileManager();
    private final HighScoresWindow highScoresWindow;

    public HighScorePresenter(HighScoresWindow highScoresWindow) {
        this.highScoresWindow = highScoresWindow;
        visibleRecord();
    }

    public void visibleRecord() {
        List<Record> records = FILE_MANAGER.readFileRecord();

        for (Record record : records) {
            switch (record.getDifficulty()) {
                case NOVICE -> highScoresWindow.setNoviceRecord(record.getName(), record.getSecond());
                case MEDIUM -> highScoresWindow.setMediumRecord(record.getName(), record.getSecond());
                case EXPERT -> highScoresWindow.setExpertRecord(record.getName(), record.getSecond());
            }
        }
    }
}