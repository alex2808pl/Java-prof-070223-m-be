package de.telran._2023_08_31.practice.restaurant;

import java.util.List;

public class Order {
    private String id;
    private List<Dish> dishes;

    private Status status;

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public String getId() {
        return id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order(String id, List<Dish> dishes, Status status) {
        this.id = id;
        this.dishes = dishes;
        this.status = status;
    }
}
