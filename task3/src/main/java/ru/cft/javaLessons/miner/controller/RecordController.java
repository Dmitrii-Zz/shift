package ru.cft.javaLessons.miner.controller;

import ru.cft.javaLessons.miner.utils.RecordUpdater;
import ru.cft.javaLessons.miner.view.RecordNameListener;

public class RecordController implements RecordNameListener {
    private final RecordUpdater recordUpdater;

    public RecordController(RecordUpdater recordUpdater) {
        this.recordUpdater = recordUpdater;
    }

    @Override
    public void onRecordNameEntered(String name) {
        recordUpdater.writeRecord(name);
    }
}