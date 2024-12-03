package ru.cft.javaLessons.miner.utils;

public class MyTimer {
    private final TimerListener listener;
    private Thread thread;
    private int second = 0;

    public MyTimer(TimerListener listener) {
        this.listener = listener;
    }

    public void startTimer() {
        long startTime = System.currentTimeMillis();
        second = 0;

        thread = new Thread(() -> {
            while (!thread.isInterrupted()) {
                second = (int) (System.currentTimeMillis() - startTime) / 1000;
                listener.changeSecond(second);

                try {
                    Thread.sleep(1000);

                    if (second == 1000) {
                        thread.interrupt();
                    }

                } catch (InterruptedException e) {
                    thread.interrupt();
                }
            }
        });

        thread.start();
    }

    public void stopTimer() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public int getSecond() {
        return second;
    }
}