//package ru.cft.javaLessons.miner.model;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class GameFieldTest {
//
//    private static GameField gameField;
//
//    @BeforeAll
//    public static void createGameField() {
//        gameField = new GameField();
//        gameField.createCells(10);
//        gameField.setCountMin(6);
//        gameField.setCountFlag(6);
//    }
//
//    @Test
//    public void createCellsTest() {
//        assertEquals(100, gameField.getCells().size());
//    }
//
//    @BeforeEach
//    public void generateMinTest() {
//        gameField.generateMin(1, 2);
//        assertEquals(100, gameField.getCells().size());
//    }
//
//    @Test
//    public void getCountFlagTest() {
//        assertEquals(10, gameField.getCountFlag());
//    }
//
//    @Test
//    public void placeFlagTest() {
//        gameField.placeFlag(5, 6);
//        assertTrue(gameField.getCellByCoordinates(5, 6).isFlag());
//        gameField.placeFlag(5, 6);
//        assertTrue(!gameField.getCellByCoordinates(5, 6).isFlag());
//    }
//
//    @Test
//    public void openCellTest() {
//        gameField.openCell(2, 3);
//        System.out.println(" 2");
//    }
//}
