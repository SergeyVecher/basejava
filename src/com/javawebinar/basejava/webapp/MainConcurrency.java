package com.javawebinar.basejava.webapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
    private static final int THREADS_NUMBER = 10000;
    //    private static int counter;
//    private static final Object LOCK = new Object();
//    private static final Lock LOCK = new ReentrantLock();
    private static final AtomicInteger counter = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MainConcurrency mainConcurrency = new MainConcurrency();
        List<Future> futureList = new ArrayList<>(THREADS_NUMBER);
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService service = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    //System.out.println(ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")).get().format(new Date()));
                    System.out.println(ThreadLocal.withInitial(LocalDateTime::now).get().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
                }
                latch.countDown();
                return 5;
            });
            futureList.add(service.poll());
        }
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(MainConcurrency.counter.get());
    }

    private void inc() {
//        LOCK.lock();
//        try {
        counter.incrementAndGet();
//        } finally {
//            LOCK.unlock();
//        }
    }
}
