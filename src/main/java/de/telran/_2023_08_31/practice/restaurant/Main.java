package de.telran._2023_08_31.practice.restaurant;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Dish dish1 = new Dish("Salad", 1000, Status.NEW);
        Dish dish2 = new Dish("Dessert", 2000, Status.NEW);
        Dish dish3 = new Dish("Steak", 3000, Status.NEW);
        Order order1 = new Order("1", Arrays.asList(dish1, dish2, dish3), Status.NEW);
        Order order2 = new Order("2", Arrays.asList(dish1, dish3), Status.NEW);
        Order order3 = new Order("3", Arrays.asList(dish2, dish3), Status.NEW);

        OrderProcessor orderProcessor = new OrderProcessor(new Kitchen(5));
//        orderProcessor.processOrder(order1);

        orderProcessor.processOrder2(order1);
        Thread.sleep(5000);
        dish1.setStatusDish(Status.NEW);
        dish3.setStatusDish(Status.NEW);
        orderProcessor.processOrder2(order2);
        Thread.sleep(5000);
        dish2.setStatusDish(Status.NEW);
        dish3.setStatusDish(Status.NEW);
        orderProcessor.processOrder2(order3);
    }

}
