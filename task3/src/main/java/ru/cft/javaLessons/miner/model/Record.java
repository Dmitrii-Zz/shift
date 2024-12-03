package ru.cft.javaLessons.miner.model;

public class Record {
    private Difficulty difficulty;
    private String name;
    private int second;

    public Record(Difficulty difficulty, String name, int second) {
        this.difficulty = difficulty;
        this.name = name;
        this.second = second;
    }

    public Record() {
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public int getSecond() {
        return second;
    }
}