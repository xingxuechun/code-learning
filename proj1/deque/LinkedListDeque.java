package deque;
import java.util.Iterator;

public class LinkedListDeque<T>implements Deque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p!= sentinel;
            }

            @Override
            public T next() {
                T item = p.item;
                p = p.next;
                return item;
            }
        };
    }


    public void addFirst(T i) {
        Node Node1 = new Node(i,sentinel,sentinel.next);
        sentinel.next.prev = Node1;
        sentinel.next = Node1;
        size++;
    }
    public void addLast(T i) {
        Node Node1 = new Node(i,this.last(),sentinel);
        this.last().next = Node1;
        sentinel.prev = Node1;
        size++;
    }

//    public boolean isEmpty(){
//        if (sentinel.next == sentinel){
//            return true;
//        }
//        return false;
//    }

    public void printDeque(){
        Node node = sentinel.next;
        while(node != sentinel){
            System.out.print(node.item);
            if (node.next != sentinel){
                System.out.print(' ');
            }
            node = node.next;
        }
        System.out.println();
    }

    public Node last() {
        if (size == 0){
            return sentinel;}
        return sentinel.prev;
    }

    public T removeFirst(){
        if (size != 0){
            T result = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return result;}
        return null;
    }

    public T removeLast(){
        if (size != 0){
            T result = this.last().item;
            this.last().prev.next = sentinel;
            sentinel.prev = this.last().prev;
            size--;
            return result;}
        return null;
    }

    public T get(int index) {
        if (index<0 || index >= size){
            return null;
        }
        int i = 0;
        Node node = sentinel;
        while (i <= index){
            node = node.next;
            i++;
        }
        return node.item;
    }

    public T getRecursive(int index){
        if (index<0 || index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
        }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deque<?> )) return false;
        Deque<?> o1 = (Deque<?>) o;
        if (this.size() != o1.size()) return false;

        for (int i = 0; i < size(); i++) {
            T a = this.get(i);
            Object b = o1.get(i);
            if (a == null) {
                if (b != null) return false;
            } else {
                if (!a.equals(b)) return false;
            }
        }
        return true;
    }

    private T getRecursiveHelper(Node node,int index){
        if (index == 0){
            return node.item;
        }
        return getRecursiveHelper(node.next,index-1);
    }
}
