public class ArrayDeque<T> {
    private final int START_SIZE = 8;
    private final double USAGE_RATE = 0.3;
    private T[]  array;
    private int size;
    private int capacity;
    private int first, last;

    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[START_SIZE];
        capacity = START_SIZE;
        first = 0;
        last = 0;
    }

    public void addFirst(T item) {
        if (size + 1 == capacity || size == capacity) {
            resize(size * 2);
        }
        first = ((first - 1) + array.length) % array.length;
        array[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size + 1 == capacity || size == capacity) {
            resize(size * 2);
        }
        array[last] = item;
        last = (last + 1) % array.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int a = first;
        int b = last;
        while (a != b) {
            System.out.println(array[a] + " ");
            a = (a + 1) % array.length;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((double) size / capacity < USAGE_RATE) {
            T[] newArray = (T[]) new Object[size * 2];
            capacity = 2 * size;
            int a = first;
            int b = last;
            int index = 0;
            for (; index < size; index++){
                newArray[index] = array[a];
                a = (a + 1) % array.length;
            }
            array = newArray;
            first = 0;
            last = (size) % array.length;
        }
        T res = array[first];
        array[first] = null;
        first = (first + 1) % array.length;
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((double) size / capacity < USAGE_RATE) {
            T[] newArray = (T[]) new Object[size * 2];
            capacity = 2 * size;
            int a = first;
            int b = last;
            int index = 0;
            for(; index < size; index++){
                newArray[index] = array[a];
                a = (a + 1) % array.length;
            }
            array = newArray;
            first = 0;
            last = (size) % array.length;
        }
        last = (last - 1 + array.length) % array.length;
        T res = array[last];
        array[last] = null;
        size -= 1;
        return res;
    }

    public T get(int index) {
        int i = 0;
        int a = first;
        int b = last;
        while (a != b) {
            if (i == index) {
                return array[a];
            }
            i += 1;
            a = (a + 1) % array.length;
        }
        return null;
    }

    private void resize(int sz) {
        T[] newArray = (T[]) new Object[sz];
        capacity = sz;
        int index = 0;
        int a = first, b = last;
        for (; index < size; index++){
            newArray[index] = array[a];
            a = (a + 1) % array.length;
        }
        first = 0;
        last =  size;
        array = newArray;
    }

}
