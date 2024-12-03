package ru.cft.javaLessons.miner.controller;

import ru.cft.javaLessons.miner.model.GameState;

public interface GameStateListener {
   void changeGameState(GameState gameState);
}