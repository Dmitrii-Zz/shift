package ru.cft.javaLessons.miner.utils;

import ru.cft.javaLessons.miner.view.MainWindow;

public class TimerListenerController implements TimerListener {
    private final MainWindow mainWindow;

    public TimerListenerController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void changeSecond(int second) {
        mainWindow.setTimerValue(second);
    }
}