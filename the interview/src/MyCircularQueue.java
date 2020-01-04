import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MyCircularQueue {

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    private int size;
    private Deque<Integer> queue;

    public MyCircularQueue(int k) {
        this.size = k;
        this.queue = new LinkedList<>();
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (this.queue.size() < this.size) {
            this.size++;
            return this.queue.add(value);
        }
        return false;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (this.size != 0) {
            this.size--;
            this.queue.poll();
            return true;
        }
        return false;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (this.size != 0) {
            this.size--;
            return this.queue.pollFirst();
        }
        return 0;
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        return this.queue.getFirst();
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
