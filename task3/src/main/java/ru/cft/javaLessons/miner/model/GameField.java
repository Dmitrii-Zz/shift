package ru.cft.javaLessons.miner.model;

import ru.cft.javaLessons.miner.controller.GameListener;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameField {
    private final GameListener gameListener;
    private Difficulty difficulty;
    private Cell[][] cells;
    private boolean isFirstOpen;
    private boolean isGameOver;
    private int countFlag;

    public GameField(Difficulty difficulty, GameListener gameListener) {
        this.difficulty = difficulty;
        this.gameListener = gameListener;
        cells = new Cell[difficulty.getColsCount()][difficulty.getRowCount()];
        createCells();
        gameListener.changeGameState(GameState.NEW);
    }

    private void createCells() {
        for (int i = 0; i < difficulty.getColsCount(); i++) {
            for (int j = 0; j < difficulty.getRowCount(); j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void newGame() {
        cells = new Cell[difficulty.getColsCount()][difficulty.getRowCount()];
        countFlag = difficulty.getMinesCount();
        isFirstOpen = false;
        isGameOver = false;
        createCells();
        gameListener.changeGameState(GameState.NEW);
    }

    public void toggleFlag(int x, int y) {

        if (canHandleAction(x, y)) {
            return;
        }

        boolean canRemoveFlag = countFlag == 0 && !cells[x][y].isFlag();
        if (cells[x][y].isOpen() || canRemoveFlag) {
            return;
        }

        if (cells[x][y].placeFlag()) {
            countFlag--;
        } else {
            countFlag++;
        }

        gameListener.changeCell(x, y, cells[x][y]);
        gameListener.changeCountMines(countFlag);
    }

    public void openCell(int x, int y) {

        if (canHandleAction(x, y)) {
            return;
        }

        if (!isFirstOpen) {
            gameListener.changeGameState(GameState.RUN);
            generateMines(x, y);
            computeMinesAroundCell();
            isFirstOpen = true;
        }

        if (cells[x][y].isFlag() || cells[x][y].isOpen()) {
            return;
        }

        if (cells[x][y].isMin()) {
            finishGame();
            return;
        }

        if (cells[x][y].getCountMinesAround() == 0) {
            openEmptyCellsAround(x, y);
            return;
        }

        cells[x][y].setIsOpen(true);
        gameListener.changeCell(x, y, cells[x][y]);
        checkForWinnings();
    }

    private boolean canHandleAction(int x, int y) {

        if (isGameOver) {
            return true;
        }

        return x < 0 || x >= difficulty.getColsCount() || y < 0 || y >= difficulty.getRowCount();
    }

    private void finishGame() {
        isGameOver = true;
        gameListener.visibleAllBomb(cells);
        gameListener.changeGameState(GameState.LOSE);
    }

    private void openEmptyCellsAround(int x, int y) {

        if (x < 0 || x > difficulty.getColsCount() - 1 || y < 0 || y > difficulty.getRowCount() - 1) {
            return;
        }

        if (cells[x][y].isFlag() || cells[x][y].isCheck() || cells[x][y].isOpen()) {
            return;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if (cells[x][y].getCountMinesAround() == 0) {
                    cells[x][y].setIsOpen(true);
                    cells[x][y].setIsCheck(true);
                    gameListener.changeCell(x, y, cells[x][y]);
                    openEmptyCellsAround(x + i, y + j);
                } else {
                    cells[x][y].setIsOpen(true);
                    gameListener.changeCell(x, y, cells[x][y]);
                    return;
                }
            }
        }
    }

    public void openCellsAroundCell(int x, int y) {

        if (canHandleAction(x, y)) {
            return;
        }

        if (cells[x][y].isFlag() || !cells[x][y].isOpen()) {
            return;
        }

        if (calculateAroundCell(x, y, true) != cells[x][y].getCountMinesAround()) {
            return;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if ((x + i) < 0 || (x + i) > difficulty.getColsCount() - 1
                        || (y + j) < 0 || (y + j) > difficulty.getRowCount() - 1) {
                    continue;
                }

                if (cells[x + i][y + j].isOpen() || cells[x + i][y + j].isFlag()) {
                    continue;
                }

                if (cells[x + i][y + j].isMin()) {
                    finishGame();
                    return;
                }

                if (cells[x + i][y + j].getCountMinesAround() == 0) {
                    openEmptyCellsAround(x + i, y + j);
                } else {
                    cells[x + i][y + j].setIsOpen(true);
                    gameListener.changeCell(x + i, y + j, cells[x + i][y + j]);
                }
            }
        }

        checkForWinnings();
    }

    private void generateMines(int x, int y) {
        int[][] coordinates = createPossibleCoordinates();

        int countSetMines = difficulty.getMinesCount();
        for (int i = 0; i < countSetMines; i++) {

            if (countSetMines > coordinates[0].length) {
                break;
            }

            if (coordinates[0][i] == x && coordinates[1][i] == y) {
                countSetMines++;
                continue;
            }

            if (cells[coordinates[0][i]][coordinates[1][i]].isMin()) {
                countSetMines++;
                continue;
            }

            cells[coordinates[0][i]][coordinates[1][i]].setIsMin(true);
        }
    }

    private int[][] createPossibleCoordinates() {
        int[][] coordinates = new int[2][difficulty.getRowCount() * difficulty.getColsCount()];

        int countColumn = 0;
        int countRow = 0;
        int countCells = difficulty.getRowCount() * difficulty.getColsCount();
        for (int i = 0; i < countCells; i++) {
            coordinates[0][i] = countColumn++;
            coordinates[1][i] = countRow++;

            if (countColumn == difficulty.getColsCount()) {
                countColumn = 0;
            }

            if (countRow == difficulty.getRowCount()) {
                countRow = 0;
            }
        }

        shuffleArray(coordinates[0]);
        shuffleArray(coordinates[1]);

        return coordinates;
    }

    private void shuffleArray(int[] array) {
        Random rnd = ThreadLocalRandom.current();

        int minesCount = difficulty.getMinesCount();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rnd.nextInt(minesCount - 1);

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private void computeMinesAroundCell() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int countNeighboringMin = calculateAroundCell(i, j, false);
                cells[i][j].setCountMinesAround(countNeighboringMin);
            }
        }
    }

    private void checkForWinnings() {
        int countOpenCell = 0;

        for (int i = 0; i < difficulty.getColsCount(); i++) {
            for (int j = 0; j < difficulty.getRowCount(); j++) {
                if (cells[i][j].isOpen()) {
                    countOpenCell++;
                }
            }
        }

        int countCells = difficulty.getColsCount() * difficulty.getColsCount();
        int minesCount = difficulty.getMinesCount();
        if (countCells - countOpenCell == minesCount) {
            gameListener.changeGameState(GameState.WIN);
        }
    }

    private int calculateAroundCell(int x, int y, boolean isFlag) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                if ((x + i) < 0 || (x + i) >= cells.length || (y + j) < 0 || (y + j) >= cells[1].length) {
                    continue;
                }

                if (isFlag) {

                    if (cells[x + i][y + j].isFlag()) {
                        count++;
                    }

                } else {

                    if (cells[x + i][y + j].isMin()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}