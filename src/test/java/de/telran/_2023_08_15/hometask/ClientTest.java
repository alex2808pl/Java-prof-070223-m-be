package de.telran._2023_08_15.hometask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientTest {

    @InjectMocks
    Client clientTest;

    @Mock
    List<Account> accountsMock;

    @Captor
    ArgumentCaptor<Account> argCaptor;

    @Test
    void idNotEmptyTest() {

        assertNotNull(clientTest.getId());
        assertFalse(clientTest.getId().isEmpty());

    }

    @Test
    void isAdultTrueTest() {
        LocalDate dateBirthTest = LocalDate.of(1900, 01, 01);
        clientTest.setDateBirth(dateBirthTest);
        LocalDate dateCurrentTest = LocalDate.of(2023, 01, 01);
        assertTrue(clientTest.isAdult(dateCurrentTest));
    }

    @Test
    void isAdultFalseTest() {
        LocalDate dateBirthTest = LocalDate.of(2020, 01, 01);
        clientTest.setDateBirth(dateBirthTest);
        LocalDate dateCurrentTest = LocalDate.of(2023, 01, 01);
        assertFalse(clientTest.isAdult(dateCurrentTest));
    }

    @Test
    void isAdultArgumentisNullTest() {
        LocalDate dateCurrentNull = null;
        assertThrows(IllegalArgumentException.class, () -> clientTest.isAdult(dateCurrentNull));
    }

    @Test
    void isNameNullTest() {
        String stringNull = null;
        assertThrows(IllegalArgumentException.class, () -> clientTest.setName(stringNull));
    }

    @Test
    void isSurnameNullTest() {
        String stringNull = null;
        assertThrows(IllegalArgumentException.class, () -> clientTest.setSurname(stringNull));
    }

    @Test
    void createNewAccountSuccessTest() {
        Account expactedNewAccount = new Account(true, Account.Zone.ZONE_1, 0.0);
        LocalDate dateCurrentTest = LocalDate.of(2023, 01, 01);
        clientTest.createNewAccount(expactedNewAccount, dateCurrentTest);
        verify(accountsMock).add(expactedNewAccount);
    }

    @Test
    void createNewAccountForOldManTest() {
        Account expactedNewAccount = new Account(true, Account.Zone.ZONE_1, 0.0);
        LocalDate dateCurrentTest = LocalDate.of(2023, 01, 01);
        clientTest.setDateBirth(LocalDate.of(1900, 01, 01));
        assertThrows(IllegalArgumentException.class, () -> clientTest.createNewAccount(expactedNewAccount, dateCurrentTest));
    }

    @Test
    void createNewAccountSuccessOldManTest() {
        Account expactedNewAccount = new Account(true, Account.Zone.ZONE_1, 1.0);
        LocalDate dateCurrentTest = LocalDate.of(2023, 01, 01);
        clientTest.setDateBirth(LocalDate.of(1900, 01, 01));
        clientTest.createNewAccount(expactedNewAccount, dateCurrentTest);

        verify(accountsMock).add(argCaptor.capture());
        Account actualAccount = argCaptor.getValue();
        assertTrue(clientTest.getDateBirth().isBefore(dateCurrentTest.minusYears(100))
                && actualAccount.getBalance()>0);
    }

}

/*
String id;
String name;
String surname;
LocalDate dateBirth;
List<Account> accounts;

boolean isAdult(LocalDate dateCurrent)


Домашнее задание:
-----------------
1) Через тесты, в классе Client создать метод добавления счета Account клиенту.
   void createNewAccount(Account newAccount);

2) Написать тест, который будет проверять чтобы люди старше 100 лет, не могли
создать счет с пустым балансом. Реализовать эту логику проверки в самом классе.

 */


