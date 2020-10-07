package com.geekbrains.javaCoreAdvancedLevel;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c, int countStage) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);

            MainClass.getLockForCheckWIN().lock();
            System.out.println(c.getName() + " закончил этап: " + description);
            if (countStage == 0){
                c.checkWinner();
            }
            MainClass.getLockForCheckWIN().unlock();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}