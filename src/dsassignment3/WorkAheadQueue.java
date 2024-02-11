/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment3;

import java.util.ArrayList;

/**
 *
 * @author Mahmood
 */
public class WorkAheadQueue<T> implements WorkAheadQueueADT<T> {
    protected LinearNode<T> front;
    protected LinearNode<T> back;
    protected ArrayList<LinearNode<T>> frontThreeNodes;
    protected ArrayList<T> frontThreeElements;
    protected int numNodes;

    
    public WorkAheadQueue() {
        frontThreeElements = new ArrayList<>(); 
        frontThreeNodes = new ArrayList<>(); 
        numNodes = 0;
    }
    
    @Override
    public T dequeue(int x) throws EmptyCollectionException, InvalidArgumentException {
    try {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Queue is empty");
        }
        if (x < 0 || x >= this.size()) {
            throw new InvalidArgumentException("Invalid index: " + x);
        }

        LinearNode<T> current = this.front;

        for (int i = 0; i < x; i++) {
            current = current.getNext();
        }

        LinearNode<T> previous = current.getPrev();
        LinearNode<T> next = current.getNext();

        if (previous != null) {
            previous.setNext(next);
        }

        if (next != null) {
            next.setPrev(previous);
        }

        if (x == 0) {
            // If removing the front node, update the front pointer
            this.front = next;
        }

        this.numNodes--;
        this.settingFrontThree();

        return current.getElement();
    } catch (EmptyCollectionException | InvalidArgumentException e) {
        // Catch and rethrow the exception for the test
        throw e;
    }
}


    public void settingFrontThree() {
        frontThreeNodes.clear();
        frontThreeElements.clear(); 
        try {
            frontThreeElements.add(0, front.getElement());
            frontThreeNodes.add(0, front);
            frontThreeElements.add(1, front.getNext().getElement());
            frontThreeNodes.add(1, front.getNext());         
            frontThreeElements.add(2, front.getNext().getNext().getElement());
            frontThreeNodes.add( front.getNext().getNext());
        } catch (Exception E) {
            // Ignoreing Errors
        }
        
    }
    
    @Override
    public T first(int x) throws EmptyCollectionException, InvalidArgumentException {
        T elementReturned;
        if (isEmpty() || numNodes == 0) {
            throw new EmptyCollectionException("EmptyCollectionException at first(x)");
        } else if (x > 4 || x < 0 || x > numNodes) {
            throw new InvalidArgumentException("InvalidArgumentException at first(x)");
        } else {
            LinearNode<T> currentelm = front;
            elementReturned = currentelm.getElement();
            for (int i = 0; i <= x; i++) {
                if (i == x) { 
                    elementReturned = currentelm.getElement();
                }
                currentelm = currentelm.getNext();
        }

        }
        return elementReturned;
    }

    @Override
    public ArrayList<LinearNode<T>> firstThreeNodes() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("EmptyCollectionException at firstThreeNodes()");
        }
        return this.frontThreeNodes;
    }

    @Override
    public ArrayList<T> firstThreeElements() throws EmptyCollectionException {
        ArrayList<T> retArray = new ArrayList<>();
        if (this.numNodes == 0) {
            throw new EmptyCollectionException("EmptyCollectionException at firstThreeElements()");
        } else {
            
            LinearNode<T> temp = front;
            for (int i = 0; (temp != null) && (i < 3); i++) {
                retArray.add(temp.getElement());
                temp = temp.getNext();
            }

        }

        return retArray;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> current = new LinearNode<>(element);
        if (isEmpty()) { 
            this.front = current;
            this.back = current;
            this.numNodes++;
            frontThreeNodes.clear();
            frontThreeElements.clear();
        } else {
            this.back.setNext(current);
            current.setPrev(this.back);
            this.back = current;
            this.numNodes++;
            frontThreeNodes.clear();
            frontThreeElements.clear();
        }
        
        this.settingFrontThree();
    }
/**
     * Removes and returns the element at the specified index in the queue.
     * 
     * @param x The index of the element to be removed.
     * @return The removed element.
     * @throws EmptyCollectionException if the queue is empty.
     * @throws InvalidArgumentException if the index is out of bounds.
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty() || numNodes == 0) {
            throw new EmptyCollectionException("EmptyCollectionException at dequeue()");
        }
        T removedElement = front.getElement();
        
        if (numNodes != 1) {
            front.getNext().setPrev(null);
        } 
        front = front.getNext();
        numNodes--;

        this.settingFrontThree();
        return removedElement;
    }

    /**
     * Retrieves and returns the element at the specified index in the queue.
     * 
     * @param x The index of the element to be retrieved.
     * @return The element at the specified index.
     * @throws EmptyCollectionException if the queue is empty.
     * @throws InvalidArgumentException if the index is out of bounds.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty() || numNodes == 0) {
            throw new EmptyCollectionException("EmptyCollectionException at first()");
        }
        return this.front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (this.numNodes == 0);
    }

    @Override
    public int size() {
        return this.numNodes;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        LinearNode curr = front;
        for (int i = 0; i < size(); i++) {
            sb.append(curr.getElement().toString());
            if (i < size() - 1) {
                sb.append(", ");
            }
            curr = curr.getNext();
        }
        return sb.toString();
    }
    
}

