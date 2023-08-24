package de.telran._2023_08_24.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestThreadMap extends Thread {

    private final int count;
    private Map map;

    public TestThreadMap(Map map, int count) {
        this.map = map;
        this.count = count;
    }

    @Override
    public void run() {
        int sum = 0;
        for(int i=0; i<count; i++) sum+=i;

        map.put(Thread.currentThread(), sum);

        System.out.println(Thread.currentThread().getName()+" sum = "+sum);
    }

    public static void main(String[] args) throws InterruptedException {
        Map<Thread, Integer> maps = new ConcurrentHashMap<>();


        Thread tr1 = new TestThreadMap(maps, 10);
        Thread tr2 = new TestThreadMap(maps, 20);
        Thread tr3 = new TestThreadMap(maps, 30);

        tr1.start();
        tr2.start();
        tr3.start();

        tr1.join();
        tr2.join();
        tr3.join();

        maps.forEach((k,v) -> System.out.println(k.getName() + " -> " + v));


    }

}
