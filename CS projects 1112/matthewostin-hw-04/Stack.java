/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2023
author: Charles Peeke, Matthew Ostin

This stack class has methods to push, pop, and check if the stack is empty.
It also gets the size of the stack, and provides a string representation
of the stack.
--------------------------------------------------------------------------*/

public class Stack {
    private StackElement top; // where top element of the stack is stored
    private int count;// num of elements in the stack

    public Stack() {
        this.top = null; //sets top to null
        this.count = 0;  //stores num elements in stack
    }

    public void push(String s) {
        StackElement newElement = new StackElement(s);// creates new stack element with it's value
        newElement.next = this.top; // sets next new to current element
        this.top = newElement; // sets top to the new element
        this.count++; 
    }
    
    public String pop() {
        if (isEmpty()) { // checks if the stack is empty
            return null; // if stack is empty return null because there is nothing to pop
        }
        String popped = this.top.value; //the value of top element is stored then to be popped
        this.top = this.top.next; //sets top to next element
        this.count--; 
        return popped; 
    }

    public boolean isEmpty() {
        return this.top == null; //return true if stack empty means that top is null, otherwise false
    }

    public int size() {
        return this.count; //returns num elements in the stack
    }

    @Override
    public String toString() {
        String s = "";
        StackElement current = this.top; // starts at top of stack
        while (current != null) { //iterate through stack until the end
            s += current.value + " "; 
            current = current.next; 
        }
        return s;//returns the string rep of the stack
    }
}


