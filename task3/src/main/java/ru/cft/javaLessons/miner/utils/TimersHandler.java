package ru.cft.javaLessons.miner.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.javaLessons.miner.controller.GameStateListener;
import ru.cft.javaLessons.miner.model.GameState;

public class TimersHandler implements GameStateListener {
    private static final Logger log = LoggerFactory.getLogger(TimersHandler.class);
    private final MyTimer myTimer;

    public TimersHandler(MyTimer myTimer) {
        this.myTimer = myTimer;
    }

    @Override
    public void changeGameState(GameState gameState) {
        switch (gameState) {
            case RUN -> myTimer.startTimer();
            case NEW, WIN, LOSE -> myTimer.stopTimer();
            default -> log.info("State game '{}' not supported", gameState);
        }
    }
}