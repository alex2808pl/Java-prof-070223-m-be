package de.telran._2023_09_05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        CyclicBarrier cb1 = new CyclicBarrier(2);


        CyclicBarrier cb2 = new CyclicBarrier(3, new BarAction());

        System.out.println("---- Start Main Thread");

        new Thread(new MyThread(cb, "AAA", 1000)).start();
        new Thread(new MyThread(cb, "BBB", 2000)).start();
        new Thread(new MyThread(cb, "CCC", 3000)).start();


        new Thread(new MyThread(cb1, "DDD", 1000)).start();
        new Thread(new MyThread(cb1, "FFF", 2000)).start();


        System.out.println("---- End Main Thread");
    }


}

class MyThread implements Runnable {
    CyclicBarrier cbar;
    String name;
    int wait;

    MyThread(CyclicBarrier c, String n, int w) {
        cbar = c;
        name = n;
        wait = w;
    }

    public void run() {

        System.out.println(name+" start");

        try {
            Thread.sleep(wait);
            System.out.println(name+" end");
            cbar.await();
        } catch (BrokenBarrierException exc) {
            System.out.println(name+" ---- BrokenBarrierException");
            System.out.println(exc);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }
}

class BarAction implements Runnable {
    public void run() {
        System.out.println("Barrier Reached!");
    }
}


