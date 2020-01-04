package singlyLinkedList;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private class Node {
        T datum;
        Node next;

        Node(T datum) {
            this.datum = datum;
            this.next = null;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public SinglyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    public void addLast(T element) {
        Node node = new Node(element);

        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            Node currTail = this.tail;
            this.tail = node;
            currTail.next = this.tail;
        }
        this.size++;
    }

    public void addFirst(T element) {
        if (this.size == 0) {
            this.addLast(element);
        } else {
            Node node = new Node(element);
            Node currHead = this.head;
            this.head = node;
            this.head.next = currHead;
            this.size++;
        }
    }

    public boolean remove(T element) {
        if (this.size == 0) {
            throw new IllegalArgumentException("Empty list!");
        }

        Node currentNode = this.head;

//        if the head contains the element
        if (currentNode != null && currentNode.datum == element) {
            this.head = currentNode.next;
            this.size--;
            return true;
        }

//        if the element is somewhere in the middle or last
        Node prev = null;
        while (currentNode != null && currentNode.datum != element) {
            prev = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            prev.next = currentNode.next;
            return true;
        }

        return false;
    }

    public boolean removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = this.head;

        if (index == 0 && currentNode != null) {
            this.head = currentNode.next;
            this.size--;
            return true;
        }

        Node prev = null;
        for (int i = 0; i < index; i++) {
            prev = currentNode;
            currentNode = currentNode.next;
        }

        if (prev != null) {
            prev.next = currentNode.next;
            this.size--;
            return true;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node head = this.head;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        return head.datum;
    }

    public boolean contains(T element) {
        Node currNode = this.head;

        while (currNode != null && currNode.datum != element) {
            currNode = currNode.next;
        }

        return currNode != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return this.counter < size;
            }

            @Override
            public T next() {
                return get(this.counter++);
            }
        };
    }
}
