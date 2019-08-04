public class ArrayDeque<T> {
    private T[] arr;
    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 1;
    private static final int MIN_INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        arr =  (T[]) new Object[MIN_INITIAL_CAPACITY];
    }

    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        arr[nextFirst] = item;
        if (this.nextFirst == 0) {
            nextFirst = this.arr.length - 1;
        } else {
            this.nextFirst -= 1;
        }
        size += 1;
        if (size == arr.length) {
            this.resize(arr.length * 2);
        }
    }

    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        arr[nextLast] = item;
        nextLast = (nextLast + 1) % (this.arr.length - 1);
        size += 1;
        if (size == arr.length) {
            this.resize(arr.length * 2);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {

        int pointer = nextFirst % (arr.length - 1) + 1;
        while (pointer <= arr.length - 1) {
            System.out.print(this.arr[pointer] + " ");
            pointer += 1;
        }
        pointer = 0;
        while (pointer <= nextFirst) {
            System.out.print(this.arr[pointer] + " ");
            pointer += 1;
        }

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item;
        if (nextFirst == arr.length - 1) {
            item = (T) arr[0];
            arr[0] = null;
            nextFirst = 0;
        } else {
            item = (T) arr[nextFirst + 1];
            arr[nextFirst + 1] = null;
            nextFirst += 1;
        }
        size -= 1;
        // usageFactor = this.size / arr.length;
        // if(arr.length >= 16 && usageFactor <= 0.25){
        //    this.keepUsage();
        //  }
        if (arr.length >= 16 && size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    //Removes and returns the item at the back of the Deque. If no such item exists, returns null.
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item;
        if (nextLast == 0) {
            item = (T) arr[arr.length - 1];
            arr[arr.length - 1] = null;
            nextLast = arr.length - 1;
            //return item;
        } else {
            item = (T) arr[nextLast - 1];
            arr[nextLast - 1] = null;
            nextLast -= 1;
            //return item;
        }
        size -= 1;
        // usageFactor = this.size / arr.length;
        // if(arr.length >= 16 && usageFactor <= 0.25){
        //    this.keepUsage();
        //  }
        if (arr.length >= 16 && size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        int n = arr.length;
        T[] a = (T[]) new Object[capacity];
        int pointer = 0;
        while (arr[pointer] == null) {
            pointer++;
        }
        System.arraycopy(this.arr, pointer, a, 0, size);
        arr = a;
        nextFirst = capacity - 1;
        nextLast = n;
    }

    public T get(int index) {
        int indexInArray;
        int starter = nextFirst + 1;
        if (index > this.size - 1) {
            return null;
        } else {
            if (nextFirst == arr.length - 1) {
                indexInArray = index;
            } else {
                if (index < arr.length - 1 - starter) {
                    indexInArray = (starter + index) % (arr.length - 1);
                } else if (index == arr.length - 1 - starter) {
                    indexInArray = arr.length - 1;
                } else {
                    indexInArray = (starter + index) % (arr.length - 1) - 1;
                }
            }
        }
        return arr[indexInArray];
    }
}
