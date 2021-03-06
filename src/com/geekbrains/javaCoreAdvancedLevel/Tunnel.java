package com.geekbrains.javaCoreAdvancedLevel;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c, int countStage) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                MainClass.getSemaphoreForTunnel().acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                MainClass.getLockForCheckWIN().lock();
                System.out.println(c.getName() + " закончил этап: " + description);
                if (countStage == 0){
                    c.checkWinner();
                }
                MainClass.getLockForCheckWIN().unlock();

                MainClass.getSemaphoreForTunnel().release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
