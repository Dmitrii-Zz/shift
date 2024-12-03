package ru.cft.javaLessons.miner.app;

import ru.cft.javaLessons.miner.controller.GameFieldController;
import ru.cft.javaLessons.miner.controller.GameListener;
import ru.cft.javaLessons.miner.controller.GameStateController;
import ru.cft.javaLessons.miner.controller.GameStateListener;
import ru.cft.javaLessons.miner.controller.GameTypeController;
import ru.cft.javaLessons.miner.controller.RecordController;
import ru.cft.javaLessons.miner.controller.ViewController;
import ru.cft.javaLessons.miner.model.Difficulty;
import ru.cft.javaLessons.miner.model.GameField;
import ru.cft.javaLessons.miner.utils.HighScorePresenter;
import ru.cft.javaLessons.miner.utils.MyTimer;
import ru.cft.javaLessons.miner.utils.RecordLogic;
import ru.cft.javaLessons.miner.utils.RecordUpdater;
import ru.cft.javaLessons.miner.utils.TimerListener;
import ru.cft.javaLessons.miner.utils.TimerListenerController;
import ru.cft.javaLessons.miner.utils.TimersHandler;
import ru.cft.javaLessons.miner.view.GameType;
import ru.cft.javaLessons.miner.view.HighScoresWindow;
import ru.cft.javaLessons.miner.view.MainWindow;
import ru.cft.javaLessons.miner.view.RecordNameListener;
import ru.cft.javaLessons.miner.view.RecordsWindow;
import ru.cft.javaLessons.miner.view.SettingsWindow;

public class Application {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();

        GameListener gameListener = new ViewController(mainWindow);
        GameField gameField = new GameField(Difficulty.NOVICE, gameListener);

        TimerListener timerListener = new TimerListenerController(mainWindow);
        MyTimer myTimer = new MyTimer(timerListener);
        GameStateListener timerController = new TimersHandler(myTimer);
        gameListener.addGameStateListener(timerController);

        GameTypeController gameTypeController = new GameTypeController(mainWindow, gameField);

        SettingsWindow settingsWindow = new SettingsWindow(mainWindow, gameTypeController);
        GameFieldController gameFieldController = new GameFieldController(gameField);

        RecordsWindow recordsWindow = new RecordsWindow(mainWindow);
        GameStateListener recordLogic = new RecordLogic(recordsWindow, gameField, myTimer);
        gameListener.addGameStateListener(recordLogic);

        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        HighScorePresenter highScorePresenter = new HighScorePresenter(highScoresWindow);
        RecordUpdater recordUpdater = new RecordUpdater(gameField, myTimer, highScorePresenter);
        RecordNameListener recordNameListener = new RecordController(recordUpdater);
        recordsWindow.setNameListener(recordNameListener);

        GameStateListener gameStateListener = new GameStateController(mainWindow, gameField);
        gameListener.addGameStateListener(gameStateListener);

        mainWindow.setNewGameMenuAction(e -> gameField.newGame());
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setCellListener(gameFieldController);

        gameTypeController.onGameTypeChanged(GameType.NOVICE);
        mainWindow.setVisible(true);
    }
}