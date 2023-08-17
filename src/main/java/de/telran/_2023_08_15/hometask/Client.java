package de.telran._2023_08_15.hometask;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private String id = UUID.randomUUID().toString();
    String name;
    String surname;
    LocalDate dateBirth;

    public String getId() {
        return id;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setName(String name) {
        if(name==null) throw new IllegalArgumentException();
        this.name = name;
    }

    public void setSurname(String surname) {
        if(surname==null) throw new IllegalArgumentException();
        this.surname = surname;
    }

    public boolean isAdult(LocalDate dateCurrent) {
        if(dateCurrent==null) throw new IllegalArgumentException();
        return dateBirth.isBefore(dateCurrent.minusYears(18));
    }

}
