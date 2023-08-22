package de.telran._2023_08_15.hometask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AccountPowerTest {
    @InjectMocks
    private Account accountTest = new Account();

    @Test
    public void testPrivateMethodUsingReflection() throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = Account.class.getDeclaredMethod("generateId", Integer.class);
        method.setAccessible(true);
        String actualId = (String) method.invoke(accountTest, 6);
        assertEquals(6, actualId.length());
    }

    @Test
    public void testPrivateMethodUsingPowerMock() throws Exception {
        int actualId = Whitebox.invokeMethod(accountTest, "getRandomNumber", 100000, 999999);
        assertTrue(actualId>=100000 && actualId<=999999);
    }
}
