public class ArrayDeque<T> {
    private final int START_SIZE = 8;
    private final double USAGE_RATE = 0.5;
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
        if (size == capacity) {
            resize(size * 2);
        }
        first = ((first - 1) + array.length) % array.length;
        array[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == capacity) {
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
        size -= 1;
        if ((double)size / capacity < USAGE_RATE) {
            T[] newArray = (T[]) new Object[size * 2];
            int a = first;
            int b = last;
            int index = 0;
            while (a != b) {
                newArray[index++] = array[a];
                a = (a + 1) % array.length;
            }
            array = newArray;
            first = 0;
            last = size - 1;
        }
        T res = array[first];
        array[first] = null;
        first = (first + 1) % array.length;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        if ((double)size / capacity < USAGE_RATE) {
            T[] newArray = (T[]) new Object[size * 2];
            int a = first;
            int b = last;
            int index = 0;
            while (a != b) {
                newArray[index++] = array[a];
                a = (a + 1) % array.length;
            }
            array = newArray;
            first = 0;
            last = size + 1;
        }
        last = (last - 1 + array.length) % array.length;
        T res = array[last];
        array[last] = null;
        return res;
    }

    public T get(int index) {
        int i = 0;
        int a = first;
        int b = last;
        while (a != b) {
            i += 1;
            a = (a + 1) % array.length;
            if (i == index) {
                return array[a];
            }
        }
        return null;
    }

    private void resize(int size) {
        T[] newArray = (T[]) new Object[size];
        capacity = size;
        System.arraycopy(array, 0, newArray, 0, this.size);
        array = newArray;
    }
}
