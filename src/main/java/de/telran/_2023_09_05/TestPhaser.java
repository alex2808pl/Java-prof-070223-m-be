package de.telran._2023_09_05;

import java.util.concurrent.Phaser;

public class TestPhaser {
    public static void main(String[] args) throws InterruptedException {
        Phaser ph = new Phaser(1);
//        ph.register();

        new Thread(new LongRunningAction("AAA", ph)).start();
        new Thread(new LongRunningAction("BBB", ph)).start();
        new Thread(new LongRunningAction("CCC", ph)).start();

        System.out.println(String.format("Thread %s waiting for others", Thread.currentThread().getName()));
        ph.arriveAndAwaitAdvance();
        System.out.println(String.format("Thread %s proceeding in phase %d", Thread.currentThread().getName(), ph.getPhase()));
        Thread.sleep(1000);

        new Thread(new LongRunningAction("DDD", ph)).start();
        new Thread(new LongRunningAction("FFF", ph)).start();

        System.out.println(String.format("Thread %s waiting for new phase", Thread.currentThread().getName()));
        ph.arriveAndAwaitAdvance();
        System.out.println(String.format("Thread %s proceeding in phase %d", Thread.currentThread().getName(), ph.getPhase()));
        Thread.sleep(1000);

        new Thread(new LongRunningAction("EEE", ph)).start();
        System.out.println(String.format("Thread %s waiting for new phase", Thread.currentThread().getName()));
        ph.arriveAndAwaitAdvance();
        System.out.println(String.format("Thread %s proceeding in phase %d", Thread.currentThread().getName(), ph.getPhase()));
        Thread.sleep(1000);

        ph.arriveAndDeregister();
        Thread.sleep(1000);

    }

}

class LongRunningAction implements Runnable {

    private final String threadName;
    private final Phaser ph;

    LongRunningAction(String threadName, Phaser ph) {
        this.threadName = threadName;
        this.ph = ph;

        this.randomWait();

        ph.register();
        System.out.println(String.format("Thread %s registered during phase %d", threadName, ph.getPhase()));
    }

    @Override
    public void run() {
        System.out.println(String.format("Thread %s BEFORE long running action in phase %d", threadName, ph.getPhase()));
        ph.arriveAndAwaitAdvance();

        randomWait();

        System.out.println(String.format("Thread %s AFTER long running action", threadName));
        ph.arriveAndDeregister();
    }

    // Симулируем работу ;)
    private void randomWait() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
