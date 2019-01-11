package com.javawebinar.basejava.webapp.deadlock;

public class DeadLock1 {

    private static <T> void deadLock(T lock1, T lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2);
                }
            }

        }).start();

    }

    public static void main(String[] args) {
        final String lock1 = "Lock1";
        final String lock2 = "Lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }
}
