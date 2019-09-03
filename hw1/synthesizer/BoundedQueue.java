package synthesizer;
public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();         //return the size of buffer
    int fillCount();        //return the number of items currently in the buffer
    void enqueue(T x);      //add item x to the end
    T dequeue();            //delete and return item from the front
    T peek();               //return item from the front
    default boolean isEmpty() {
        return 0 == fillCount();
    }
    default boolean isFull() {
        return capacity() == fillCount();
    }


}
