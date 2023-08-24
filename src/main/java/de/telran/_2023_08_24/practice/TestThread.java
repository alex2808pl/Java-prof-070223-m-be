package de.telran._2023_08_24.practice;

public class TestThread extends Thread{
    private String vorname;
    private int count;

    public TestThread(String vorname) {
        this.vorname = vorname;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" -> Hello "+vorname);
        for (int i=0; i<count; i++) vorname += i;
        System.out.println(Thread.currentThread().getName()+" -> "+vorname);
    }

    public static void main(String[] args) throws InterruptedException {
//        Thread tr1 = new TestThread("Вася ");
//        Thread tr2 = new TestThread("Петя ");

        TestThread tr1 = new TestThread("Вася ");
        TestThread tr2 = new TestThread("Петя ");

        tr1.setCount(15);
        tr2.setCount(20);

        tr1.start();
        tr2.start();

        tr1.join();
        tr2.join();


    }
}
