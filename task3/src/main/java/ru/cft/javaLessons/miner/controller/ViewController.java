package ru.cft.javaLessons.miner.controller;

import ru.cft.javaLessons.miner.model.Cell;
import ru.cft.javaLessons.miner.model.GameState;
import ru.cft.javaLessons.miner.view.GameImage;
import ru.cft.javaLessons.miner.view.MainWindow;

import java.util.ArrayList;
import java.util.List;

public class ViewController implements GameListener {
    private final MainWindow view;
    private final List<GameStateListener> gameStateListenerLists = new ArrayList<>();

    public ViewController(MainWindow view) {
        this.view = view;
    }

    @Override
    public void changeCell(int x, int y, Cell cell) {

        if (cell.isOpen() && cell.getCountMinesAround() == 0) {
            view.setCellImage(x, y, GameImage.EMPTY);
            return;
        } else if (cell.isOpen() && !cell.isMin()) {
            view.setCellImage(x, y, GameImage.getImageNumber(cell.getCountMinesAround()));
            return;
        } else if (cell.isOpen() && cell.isMin()) {
            view.setCellImage(x, y, GameImage.BOMB);
        }

        if (cell.isFlag()) {
            view.setCellImage(x, y, GameImage.MARKED);
        } else {
            view.setCellImage(x, y, GameImage.CLOSED);
        }
    }

    @Override
    public void changeCountMines(int countMines) {
        view.setBombsCount(countMines);
    }

    @Override
    public void changeGameState(GameState gameState) {
        notifyState(gameState);
    }

    @Override
    public void visibleAllBomb(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].isMin()) {
                    view.setCellImage(i, j, GameImage.BOMB);
                }
            }
        }
    }

    @Override
    public void addGameStateListener(GameStateListener gameStateListener) {
        gameStateListenerLists.add(gameStateListener);
    }

    private void notifyState(GameState gameState) {
        for (GameStateListener gameStateListener : gameStateListenerLists) {
            gameStateListener.changeGameState(gameState);
        }
    }
}