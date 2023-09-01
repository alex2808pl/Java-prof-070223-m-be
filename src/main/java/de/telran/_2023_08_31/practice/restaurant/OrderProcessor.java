package de.telran._2023_08_31.practice.restaurant;

import java.util.List;

public class OrderProcessor {
    private final Kitchen kitchen;

    public OrderProcessor(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public void processOrder(Order order) throws InterruptedException {
        List<Dish> dishList = order.getDishes();
        order.setStatus(Status.COOKING);
        for (Dish dish : dishList) {
            kitchen.cookDish(dish);
        }
        synchronized (kitchen) {
            while (dishList.stream()
                    .anyMatch(d -> !d.getStatusDish().equals(Status.READY))) {
                kitchen.wait();
            }
            order.setStatus(Status.READY);
            System.out.println("READY" + order.getId());
        }
        kitchen.shutdown();
    }

    public void processOrder2(Order order) {
        kitchen.cookOrder(order);
//        kitchen.shutdown();
    }
}
