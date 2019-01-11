package com.javawebinar.basejava.webapp.deadlock;

public class DeadLock implements Runnable {
    private FirstClass a = new FirstClass();
    private SecondClass b = new SecondClass();

    private DeadLock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        a.foo(b);

        System.out.println("Назад в главный поток");
    }

    public void run() {
        b.bar(a);
        System.out.println("Назад в другой поток");
    }

    public static void main(String args[]) {
        new DeadLock();
    }
}
