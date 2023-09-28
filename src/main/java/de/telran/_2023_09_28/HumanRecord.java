package de.telran._2023_09_28;

public record HumanRecord(String name, int age) {
    public HumanRecord(String firstName, String lastName, int age) {
        this(firstName+" "+lastName, age);
    }

    static int countLeg;



}
