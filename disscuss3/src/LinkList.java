import java.io.LineNumberReader;

public class LinkList {
    public class Node {
        private int x;
        private Node next;

        public Node(int x, Node next) {
            this.x = x;
            this.next = next;
        }
    }

    private Node first;

    public LinkList() {
        first = new Node(11, null);
    }

    public void addFirst(int x) {
        first.next = new Node(x, first.next);
    }

    public void insert(int x, int index) {
        Node p = first.next;
        while (index != 1) {
            p = p.next;
            index --;
        }
        p.next = new Node(x, p.next);
    }

    public void print() {
        Node p = first.next;
        while (p != null) {
            System.out.print(p.x + " ");
            p = p.next;
        }
    }

    public static void main(String[] args) {
        LinkList lst = new LinkList();
        lst.addFirst(3);
        lst.addFirst(2);
        lst.addFirst(1);
        lst.insert(10,2);
        lst.print();
    }
}
