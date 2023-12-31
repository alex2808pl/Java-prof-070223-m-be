package de.telran._2023_08_15.hometask;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class AccountTest {

    @Test
    public void defaultAccountCreationTest() {
        Account account = new Account();
        assertEquals(account.getId().length(), 6);
        assertEquals(account.getStatus(), true);
        assertEquals(account.getZone(), Account.Zone.ZONE_1);
        assertEquals(account.getBalance(), 0.00);
    }

    @Test
    public void customAccountCreationTest() {
        Account account = new Account(false, Account.Zone.ZONE_3, 125.95);
        assertEquals(account.getId().length(), 6);
        assertEquals(account.getStatus(), false);
        assertEquals(account.getZone(), Account.Zone.ZONE_3);
        assertEquals(account.getBalance(), 125.95);
    }

    @Test
    public void negativeBalanceTest() {
        assertThrows(IllegalArgumentException.class, () -> new Account(false, Account.Zone.ZONE_3, -200));
    }

    // Тестирование static методов
    @Test
    void staticGenerateIdTest() {
        try (MockedStatic<Account> accountTest = mockStatic(Account.class)) {
            accountTest.when(() -> Account.generateId(6)).thenReturn("123456");
            Account account = new Account(false, Account.Zone.ZONE_3, 125.95);
            assertEquals("123456", account.getId());
        }
    }
}
