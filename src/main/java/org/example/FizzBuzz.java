package org.example;

public class FizzBuzz extends Thread {
    private static int startNumber = 15;

    private final int number;
    private int current = 1;

    public FizzBuzz(int n) {
        this.number = n;
    }

    public synchronized void fizz() throws InterruptedException {
        while (current <= number) {
            if (current % 3 == 0 && current % 5 != 0) {
                System.out.print("fizz");
                separate();
                current++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (current <= number) {
            if (current % 5 == 0 && current % 3 != 0) {
                System.out.print("buzz");
                separate();
                current++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (current <= number) {
            if (current % 3 == 0 && current % 5 == 0) {
                System.out.print("fizzbuzz");
                separate();
                current++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number() throws InterruptedException {
        while (current <= number) {
            if (current % 3 != 0 && current % 5 != 0) {
                System.out.print(current);
                separate();
                current++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public void separate()  {
        if (current < number) {
            System.out.print(", ");
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(startNumber);

        Thread A = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            }
            catch (InterruptedException ignored) {
                System.out.println("A Exception");
            }
        });

        Thread B = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException ignored) {
                System.out.println("B Exception");
            }
        });

        Thread C = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException ignored) {
                System.out.println("C Exception");
            }
        });

        Thread D = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException ignored) {
                System.out.println("D Exception");
            }
        });

        A.start();
        B.start();
        C.start();
        D.start();
    }
}