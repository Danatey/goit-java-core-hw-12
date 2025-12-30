package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeCounter {
    public static void main(String[] args) {

        AtomicInteger seconds = new AtomicInteger();

        int second = 1000;
        int interval = 5000;

        Thread timeThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(second);
                    seconds.getAndIncrement();
                    System.out.println(seconds);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread messageThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(interval);
                    System.out.println("Минуло " + (seconds.get() + 1) + " секунд");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        timeThread.start();
        messageThread.start();
    }
}
