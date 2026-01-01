package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeCounter {
    private static volatile boolean  isRunning = true;
    private static final int maxTime = 20;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger seconds = new AtomicInteger();

        int second = 1000;
        int interval = 5000;

        Thread timeThread = new Thread(() -> {
            try {
                while (isRunning) {
                    Thread.sleep(second);
                    int current = seconds.incrementAndGet();
                    System.out.println(current);

                    if (current >= maxTime) {
                        isRunning = false;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread messageThread = new Thread(() -> {
            try {
                while (isRunning) {
                    Thread.sleep(interval);
                    if (isRunning) {
                        System.out.println("Минуло " + (seconds.get() + 1) + " секунд");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        timeThread.start();
        messageThread.start();

        timeThread.join();
        messageThread.join();
    }
}
