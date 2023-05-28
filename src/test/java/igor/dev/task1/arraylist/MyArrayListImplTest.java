package igor.dev.task1.arraylist;


import igor.dev.task1.arraylist.impl.MyArrayListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyArrayListImplTest {

    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayListImpl<>();
    }

    @Test
    void addShouldAddElementToList() {
        list.add("test1");
        list.add("test2");
        assertEquals(2, list.size());
        assertEquals("test1", list.get(0));
        assertEquals("test2", list.get(1));
    }

    @Test
    void addShouldAddElementToListByIndex() {
        list.add(0, "test1");
        list.add(1, "test2");
        list.add(5, "test3");
        assertEquals(3, list.size());
        assertEquals("test1", list.get(0));
        assertEquals("test2", list.get(1));
        assertEquals("test3", list.get(5));
    }

    @Test
    void getShouldReturnElementByIndex() {
        list.add("test");
        assertEquals("test", list.get(0));
    }

    @Test
    void sizeShouldReturnNumberOfElementsInList() {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        assertEquals(3, list.size());
    }

    @Test
    void removeShouldRemoveElementByIndex() {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.remove(2);
        assertEquals(2, list.size());
        assertEquals("test1", list.get(0));
        assertEquals("test2", list.get(1));
    }

    @Test
    void removeAllShouldRemoveAllElementsFromList() {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.removeAll();
        assertEquals(0, list.size());
    }

    @Test
    void indexOfShouldReturnIndexOfElement() {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        assertEquals(1, list.indexOf("test2"));
    }

    @Test
    void reverseShouldReverseOrderOfElements() {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.reverse();
        assertEquals("test3", list.get(0));
        assertEquals("test2", list.get(1));
        assertEquals("test1", list.get(2));
    }

    @Test
    void sortShouldSortElementsUsingGivenComparator() {
        list.add("test1");
        list.add("test3");
        list.add("test2");
        list.add("test4");
        Comparator<String> alphabeticalOrder = Comparator.naturalOrder();
        list.sort(alphabeticalOrder);
        assertEquals("test1", list.get(0));
        assertEquals("test2", list.get(1));
        assertEquals("test3", list.get(2));
        assertEquals("test4", list.get(3));
    }
}