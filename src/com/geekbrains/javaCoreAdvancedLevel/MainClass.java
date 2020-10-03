package com.geekbrains.javaCoreAdvancedLevel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

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

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        countDownLatchFinish = new CountDownLatch(CARS_COUNT);
        Car[] cars = new Car[CARS_COUNT];
        countDownLatchStart = new CountDownLatch(CARS_COUNT);
        cyclicBarrier = new CyclicBarrier(CARS_COUNT);
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





