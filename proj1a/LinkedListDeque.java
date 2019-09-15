public class LinkedListDeque<T> {
    private int size;
    private Node<T> sentinel;

    private class Node<T> {
        private T data;
        private Node<T> pre, next;

        public Node(T data) {
            this.data = data;
            pre = null;
            next = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node<T>(null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        Node<T> node = new Node<T>(item);
        sentinel.next.pre = node;
        node.next = sentinel.next;
        sentinel.next = node;
        node.pre = sentinel;
    }

    public void addLast(T item) {
        size += 1;
        Node<T> last = sentinel.pre;
        Node<T> node = new Node<T>(item);
        last.next = node;
        node.pre = last;
        node.next = sentinel;
        sentinel.pre = node;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.data + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        Node<T> p = sentinel.next;
        if (p == null) {
            return null;
        }
        T res = p.data;
        sentinel.next = p.next;
        p.next.pre = sentinel;
        size -= 1;
        return res;
    }

    public T removeLast() {
        Node<T> last = sentinel.pre;
        if (last == null) {
            return null;
        }
        T res = last.data;
        Node<T> pre = last.pre;
        pre.next = sentinel;
        sentinel.pre = pre;
        size -= 1;
        return res;
    }

    public T get(int index) {
        int i = 0;
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            if (i == index) {
                return p.data;
            }
            p = p.next;
            i += 1;
        }
        return null;
    }

    public T getRecursive(int index) {
        Node<T> node = sentinel.next;
        return helper(index, node);
    }

    private T helper(int index, Node<T> node) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.data;
        }
        return helper(--index, node.next);
    }
}
