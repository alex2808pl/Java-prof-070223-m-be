package de.telran._2023_08_29.practice;

public class Account {
    private long money;
    private final String accNumber;
    private boolean isBlocked;

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        isBlocked = false;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void blockedAccount() {
        isBlocked = true;
        setMoney(0);
    }

    public boolean getStatus() {
        return isBlocked;
    }

    @Override
    public String toString() {
        return "Номер аккаунта: " + getAccNumber() + ". Остаток на счёте: " + getMoney() + " евро.";
    }
}
