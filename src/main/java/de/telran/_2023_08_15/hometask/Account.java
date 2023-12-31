package de.telran._2023_08_15.hometask;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Account {
    private String id = String.valueOf(getRandomNumber(100000, 999999));
    private boolean status = true;
    private Zone zone = Zone.ZONE_1;
    private double balance = 0.0;

    public Account() {}

    public Account(boolean status, Zone zone, double balance) {
        this.id = generateId(6);
        this.status = status;
        this.zone = zone;
        if (balance < 0)
            throw new IllegalArgumentException("The balance can not be negative");
        this.balance = balance;
    }

    public enum Zone {
        ZONE_1, ZONE_2, ZONE_3
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public Zone getZone() {
        return zone;
    }

    public double getBalance() {
        return balance;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    // Lera
    static String generateId(Integer length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        return IntStream.range(0, length)
                .mapToObj(i -> (char) ('a' + i % 26))
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
