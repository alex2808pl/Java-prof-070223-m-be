package de.telran._2023_08_24.practice;

import java.util.Random;



public class TestThreadCallback extends Thread {
    private Work work;

    public TestThreadCallback(Work work) {
        this.work = work;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        Data data = new Data();
        int n1 = random.nextInt(100);
        int n2 = random.nextInt(200);
        int n3 = random.nextInt(300);
        System.out.println(Thread.currentThread().getName()+" -> "+n1+" - "+n2+" - "+n3);

        work.process(data, n1, n2, n3);

        System.out.println(Thread.currentThread().getName()+" -> Сумма = "+data.value);
}

    public static void main(String[] args) {
        Thread tr1 = new TestThreadCallback(new Work());
        tr1.start();

        Thread tr2 = new TestThreadCallback(new Work());
        tr2.start();

    }
}

class Data {
    public int value = 0;
}

class Work {
    public void process(Data data, Integer ... numbers) {
        for (int n : numbers) data.value += n;
    }
}

