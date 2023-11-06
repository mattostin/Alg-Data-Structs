/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: Charles Peeke, Matthew Ostin

Implementation of a queue data structure, it has methods such as enqueue which
add elements at the back of the queue. It also has dequeues elements from the 
front of the queue. Also checks if the queue is empty, gets the size of the queue
and proved a string representation of the queue.
--------------------------------------------------------------------------*/


public class Queue {
    private QueueElement front;
    private QueueElement back;
    private int count; // num of elements in queue

    //sets front and back to null and start count at zero
    public Queue() {
        this.front = null; 
        this.back = null;
        this.count = 0;
    }

    public void enqueue(Transaction t) {
        QueueElement newElement = new QueueElement(t); //creates new QueueElement with transaction
        if (isEmpty()) {
            this.front = newElement; // sets front to the new element
        } else {
            this.back.next = newElement; //set the next back to new element
        }
        this.back = newElement; 
        this.count++;
    }

    public Transaction dequeue() {
        if (isEmpty()) { // if queue is empty
            return null;
        }
        Transaction result = this.front.value; //get value of front element
        this.front = this.front.next; // moves the front to next element
        this.count--;
        if (isEmpty()) { //checks to see if queue is now empty
            this.back = null; //sets the back to null
        }
        return result; 
    }

    public boolean isEmpty() {
        return this.front == null; //checks to see if the front is null(empty queue)
    }

    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String s = "";
        QueueElement current = this.front;
        while (current != null) { //iterate through the elements that are in the queue
            s += current.value.toString() + ";";
            current = current.next; // move to the next element
        }
        return s;
    }

    private class QueueElement {
        Transaction value; //value of the element
        QueueElement next; //ref to the next element in queue

        public QueueElement(Transaction t) {
            this.value = t; //set val to given transaction
            this.next = null;
        }
    }
}
