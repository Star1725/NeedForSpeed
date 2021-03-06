package com.geekbrains.javaCoreAdvancedLevel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static CountDownLatch getCountDownLatchStart() {
        return countDownLatchStart;
    }

    private static CountDownLatch countDownLatchStart;

    public static CountDownLatch getCountDownLatchFinish() {
        return countDownLatchFinish;
    }

    private static CountDownLatch countDownLatchFinish;

    public static CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    private static CyclicBarrier cyclicBarrier;

    public static Semaphore getSemaphoreForTunnel() {
        return semaphoreForTunnel;
    }

    private static Semaphore semaphoreForTunnel;

    public static Lock getLockForCheckWIN() {
        return lockForCheckWIN;
    }

    protected static Lock lockForCheckWIN;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        countDownLatchFinish = new CountDownLatch(CARS_COUNT);
        countDownLatchStart = new CountDownLatch(CARS_COUNT);
        semaphoreForTunnel = new Semaphore(CARS_COUNT/2);
        cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        lockForCheckWIN = new ReentrantLock();
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            countDownLatchStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            countDownLatchFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}





