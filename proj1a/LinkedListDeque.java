public class LinkedListDeque<T> {
    public class InNode {
        private InNode prev;
        private T x;
        private InNode next;

        public InNode(InNode prev, T x, InNode next) {
            this.prev = prev;
            this.x = x;
            this.next = next;
        }
    }

    private InNode first;
    private int size;

    public LinkedListDeque() {
        first = new InNode(null, null, null);
        first.prev = first;
        first.next = first;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        InNode p = other.first.next;
        first = new InNode(null, null, null);
        first.next = first;
        first.prev = first;
        while (p != other.first) {
            addLast(p.x);
            p = p.next;
        }
        size = other.size;
    }

    public void addFirst(T item) {
        if (size == 0) {
            first.next = new InNode(first, item, first.next);
            first.prev = first.next;
            size += 1;
            return;
        }
        first.next = new InNode(first, item, first.next);
        first.next.next.prev = first.next.next;
        size += 1;
    }

    public void addLast(T item) {
        InNode last = first.prev;
        last.next = new InNode(last, item, first);
        first.prev = last.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        InNode p = first.next;
        while (p != first) {
            System.out.print(p.x + " ");
            p = p.next;
        } System.out.println();
    }

    public T removeFirst() {
        if (first.next == null) return null;
        T res = first.next.x;
        if (size == 1) {
            first.next = first;
            first.prev = first;
        } else {
            first.next = first.next.next;
            first.next.prev = first.next;
        }
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (size == 0) return null;
        T res = first.prev.x;
        if (size == 1) {
            first.next = first;
            first.prev = first;
        } else {
            InNode last = first.prev;
            InNode preLast = last.prev;
            preLast.next = last.next;
            first.prev = preLast;
        }
        size -= 1;
        return res;
    }

    public T get(int index) {
        if (index >= size) return null;
        InNode p = first.next;
        while (index != 0) {
            p = p.next;
            index --;
        }
        return p.x;
    }

    public T getRecursive(int index) {
        if (index >= size) return null;
        return helper(index, first.next);
    }

    public T helper(int index, InNode p) {
        if (index == 0) return p.x;
        else return helper(index - 1, p.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lst = new LinkedListDeque<>();
        lst.addFirst(1);
        lst.addLast(2);
        lst.addFirst(0);
        lst.addLast(3);
        lst.addLast(4);
        lst.addFirst(-1);
        System.out.println(lst.getRecursive(0));
        System.out.println(lst.getRecursive(3));
        LinkedListDeque<Integer> other = new LinkedListDeque<>(lst);
        other.printDeque();
        lst.printDeque();
    }
}

