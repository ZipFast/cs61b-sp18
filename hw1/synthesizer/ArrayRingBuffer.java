// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;
import java.util.function.DoubleUnaryOperator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends  AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.

        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new IndexOutOfBoundsException("the BoundedQueue is full, can't excute enqueue action");
        }
        if (last == capacity) {
            last = 0;
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("the BoundedQueue is empty");
        }
        T res = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first += 1;
        if (first == capacity) {
            first = 0;
        }
        return res;
     }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.


    @Override
    public Iterator<T> iterator() {
        return new QueueIter();
    }

    private class QueueIter implements Iterator<T> {
        private int Pos;

        public QueueIter() {
            Pos = first;
        }

        @Override
        public boolean hasNext() {
            if (Pos == last) return false;
            return true;
        }

        @Override
        public T next() {
            if (Pos == capacity) {
                Pos = 0;
            }
            T res = rb[Pos];
            Pos += 1;
            return res;
        }
    }

    public static void main(String[] args) {
        BoundedQueue x = new ArrayRingBuffer<Double>(4);
        x.enqueue(33.1); // 33.1 null null  null
        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(62.3);// 33.1 44.8 62.3  null
        x.enqueue(-3.4);// 33.1 44.8 62.3 -3.4
        x.dequeue();    // 44.8 62.3 -3.4  null (returns 33.1)
        for ( Double i : (ArrayRingBuffer<Double>) x) {
            System.out.println(i);
        }
    }
}
