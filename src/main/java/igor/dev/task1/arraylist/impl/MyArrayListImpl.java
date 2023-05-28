package igor.dev.task1.arraylist.impl;

import igor.dev.task1.arraylist.MyArrayList;

import java.util.Comparator;

/**
 * Класс, который предоставляет реализацию интерфейса MyArrayList, используя массив.
 * Это динамический массив, который может расширяться по мере добавления количества элементов.
 * Класс предоставляет методы для добавления, получения, удаления, обращения, сортировки и получения размера и индекса элементов в списке.
 *
 * @param <T> Тип объекта, хранящегося в списке
 */
@SuppressWarnings("unchecked")
public class MyArrayListImpl<T> implements MyArrayList<T> {
    private final int defaultCapacity = 10;
    private T[] myList = (T[]) new Object[defaultCapacity];
    private int size = 0;

    /**
     * Добавляет элемент в конец списка. Если список заполнен, он
     * копируется в больший массив для создания большего пространства.
     *
     * @param item Элемент для добавления
     */
    @Override
    public void add(T item) {
        for (int i = 0; i < myList.length; i++) {
            if (myList[i] == null) {
                myList[i] = item;
                size++;
                break;
            }
        }
        if (size > myList.length - 1) {
            copyMyArrayList();
        }
    }

    /**
     * Добавляет элемент по заданному индексу
     *
     * @param index - индекс позиции, в которую нужно добавить элемент.
     * @param item - элемент, который нужно добавить.
     */
    @Override
    public void add(int index, T item) {
        if (size == myList.length) {
            copyMyArrayList();
        }
        for (int i = size; i > index; i--) {
            myList[i] = myList[i - 1];
        }
        myList[index] = item;
        size++;
        if (size == myList.length) {
            copyMyArrayList();
        }
    }

    /**
     * Получает элемент по указанному индексу.
     *
     * @param index Индекс элемента для получения
     * @return Элемент по указанному индексу
     */
    @Override
    public T get(int index) {
        for (int i = 0; i < myList.length - 1; i++) {
            if (myList[index] == null) {
                myList[index] = myList[i + 1];
                myList[i + 1] = null;
            }
        }
        return myList[index];
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return Количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Удаляет элемент по указанному индексу. Если элемент не находится в конце,
     * оставшиеся элементы смещаются для заполнения пробела. Если список меньше
     * чем наполовину, он копируется в меньший массив, чтобы сэкономить память.
     *
     * @param index Индекс удаляемого элемента
     */
    @Override
    public void remove(int index) {
        for (int i = 0; i < myList.length; i++) {
            if (i == index) {
                myList[i] = null;
                size--;
                if (myList[index] == null) {
                    myList[index] = myList[i + 1];
                    myList[i + 1] = null;
                }
            }
        }
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < myList.length; i++) {
            myList[i] = null;

        }
        size = 0;
    }

    /**
     * Находит индекс первого вхождения элемента в список или возвращает -1,
     * если элемент не найден. Если элемент null, то возвращает индекс первого
     * вхождения null элемента в списке, если таковой имеется.
     *
     * @param item Элемент, индекс которого нужно найти.
     * @return Индекс первого вхождения элемента в списке или -1, если элемент не найден.
     */
    @Override
    public int indexOf(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (myList[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(myList[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Переворачивает порядок элементов в списке таким образом, что первый элемент становится последним,
     * второй элемент становится предпоследним и так далее.
     */
    @Override
    public void reverse() {
        int left = 0;
        int right = size - 1;
        while (left < right) {
            T temp = myList[left];
            myList[left] = myList[right];
            myList[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Сортирует элементы списка с помощью заданного компаратора. Если список пуст, метод просто возвращает управление.
     *
     * @param comparator Компаратор, используемый для сравнения элементов списка.
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size == 0) {
            return;
        }
        quickSort(myList, 0, size - 1, comparator);
    }

    private void copyMyArrayList() {
        T[] newMyList = (T[]) new Object[myList.length * 2];
        System.arraycopy(myList, 0, newMyList, 0, size);
        myList = newMyList;
    }

    private void quickSort(T[] myList, int left, int right, Comparator<? super T> comparator) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(myList, left, right, comparator);
        quickSort(myList, left, pivotIndex - 1, comparator);
        quickSort(myList, pivotIndex + 1, right, comparator);
    }

    private int partition(T[] myList, int left, int right, Comparator<? super T> comparator) {
        T pivot = myList[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && comparator.compare(myList[i], pivot) <= 0) {
                i++;
            }
            while (i <=j && comparator.compare(myList[j], pivot) > 0) {
                j--;
            }
            if (i < j) {
                T temp = myList[i];
                myList[i] = myList[j];
                myList[j] = temp;
            }
        }
        T temp = myList[left];
        myList[left] = myList[j];
        myList[j] = temp;
        return j;
    }
}