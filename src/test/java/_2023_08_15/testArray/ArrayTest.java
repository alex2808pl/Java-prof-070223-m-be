package _2023_08_15.testArray;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArrayTest {
    @Mock
    ArrayList<String> testArrayMock = new ArrayList<>();

    @Spy
    ArrayList<String> testArraySpy = new ArrayList<>();

    @Test
    void testAnnotationSpy() {
        testArraySpy.add("1");
        testArraySpy.add("2");

        verify(testArraySpy).add("1");
        assertEquals(2, testArraySpy.size());

        when(testArrayMock.size()).thenReturn(3);
        assertEquals(3, testArrayMock.size());

    }


    @Test
    void testAnnotationMock() {
        testArrayMock.add("1");
        testArrayMock.add("2");

        verify(testArrayMock).add("1");
        assertEquals(0, testArrayMock.size());

        when(testArrayMock.size()).thenReturn(3);
        assertEquals(3, testArrayMock.size());

    }

    @Test
    void testMock() {
        ArrayList<String> testArrayMockLocal  = mock(ArrayList.class);
        testArrayMockLocal.add("1");
        testArrayMockLocal.add("2");

        verify(testArrayMockLocal).add("1");
        assertEquals(0, testArrayMockLocal.size());

        when(testArrayMockLocal.size()).thenReturn(3);
        assertEquals(3, testArrayMockLocal.size());

    }
    @Test
    void testSpy() {
        ArrayList<String> testArraySpyLocal  = spy(ArrayList.class);
        testArraySpyLocal.add("1");
        testArraySpyLocal.add("2");

        verify(testArraySpyLocal).add("1");
        assertEquals(2, testArraySpyLocal.size());

        when(testArraySpyLocal.size()).thenReturn(3);
        assertEquals(3, testArraySpyLocal.size());

    }

}
