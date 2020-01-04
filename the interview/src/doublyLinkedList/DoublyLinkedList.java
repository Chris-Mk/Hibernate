package doublyLinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private class Node {
        T data;
        Node next;
        Node previous;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addLast(T element) {
        Node node = new Node(element);

        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            Node currTail = this.tail;
            this.tail = node;
            tail.previous = currTail;
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
            currHead.previous = this.head;
            this.size++;
        }
    }

    public boolean remove(T element) {
        if (this.contains(element)) {
            Node currNode = this.head;

            while (currNode != null && currNode.data != element) {
                currNode = currNode.next;
            }

            if (currNode != null) {
                return deleteNode(currNode);
            }
        }
        return false;
    }

    public boolean removeAt(int index) {
        if (index >= 0 && index < this.size) {
            Node currNode = this.head;

            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }

            return deleteNode(currNode);
        }
        return false;
    }

    public boolean removeFirst() {
        if (this.size != 0) {
            if (this.size == 1) {
                this.head = this.tail = null;
            } else {
                Node head = this.head;
                this.head = head.next;
                this.head.previous = null;
            }
            this.size--;
            return true;
        }
        return false;
    }

    public boolean removeLast() {
        if (this.size != 0) {
            if (this.size == 1) {
                this.head = this.tail = null;
            } else {
                Node tail = this.tail;
                this.tail = tail.previous;
                this.tail.next = null;
            }
            this.size--;
            return true;
        }
        return false;
    }

    public boolean contains(T element) {
        Node currNode = this.head;

        while (currNode != null && currNode.data != element) {
            currNode = currNode.next;
        }

        return currNode != null;
    }

    public T get(int index) {
        Node currNode = this.head;

        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.data;
    }

    private boolean deleteNode(Node currNode) {
        Node prevNode = currNode.previous;
        Node nextNode = currNode.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        }

        if (nextNode != null) {
            nextNode.previous = prevNode;
        }

        this.size--;
        return true;
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
