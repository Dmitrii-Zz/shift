package ru.cft.javaLessons.miner.controller;

import ru.cft.javaLessons.miner.model.Cell;
import ru.cft.javaLessons.miner.model.GameState;

public interface GameListener {
    void changeCell(int x, int y, Cell cell);

    void changeCountMines(int countMines);

    void changeGameState(GameState gameState);

    void addGameStateListener(GameStateListener gameStateListener);

    void visibleAllBomb(Cell[][] cells);
}