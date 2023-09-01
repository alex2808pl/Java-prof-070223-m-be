package de.telran._2023_08_31.practice.restaurant;

public class Dish {
    private String name;
    private long preparationTime; // in milliseconds
    private Status statusDish;

    public String getName() {
        return name;
    }

    public long getPreparationTime() {
        return preparationTime;
    }

    public Status getStatusDish() {
        return statusDish;
    }

    public void setStatusDish(Status statusDish) {
        this.statusDish = statusDish;
    }

    public Dish(String name, long preparationTime, Status statusDish) {
        this.name = name;
        this.preparationTime = preparationTime;
        this.statusDish = statusDish;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", preparationTime=" + preparationTime +
                ", statusDish=" + statusDish +
                '}';
    }
}
