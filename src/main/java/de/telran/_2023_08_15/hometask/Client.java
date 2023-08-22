package de.telran._2023_08_15.hometask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String surname;
    private LocalDate dateBirth;
    private List<Account> accounts;

    public String getId() {
        return id;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public String getName() {
        return name;
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

    private boolean isOldMan(LocalDate dateCurrent) {
        if(dateCurrent==null) throw new IllegalArgumentException();
        return dateBirth.isBefore(dateCurrent.minusYears(100));
    }

    public void createNewAccount(Account newAccount, LocalDate dateCurrent) {
        if(accounts==null) accounts = new ArrayList<>();
        if(newAccount==null || dateBirth!=null && isOldMan(dateCurrent) && newAccount.getBalance()<=0)
            throw new IllegalArgumentException();
        accounts.add(newAccount);

    }
}
