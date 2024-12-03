package ru.shift;

import ru.shift.logic.ManagerTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new ManagerTask().start();
    }
}