package dsassignment3;

import java.util.ArrayList;

/**
 * An interface for the workAheadQueue.
 * Specific workAheadQueue implementations will implement this interface.
 * For use in ITCS 2214 Data Structures and Algorithms
 * UNC Charlotte, 2019
 * @author clatulip, pfranklin.  Revised for spring 2021, nblong.
 * @version 2019-02-26
 * @param <T> a generic parameter
 */
public interface WorkAheadQueueADT<T> extends QueueADT<T> {
    
    /**
     * Removes and returns the element that is at index x in the queue.
     * Precondition: x must be less than 3 and x must be less than size
     * Note: indexing from 0: 0 == front element, 1 == second element, etc.
     * @param x the passed in index of the element to be removed
     * @return the element removed from the queue
     * @throws EmptyCollectionException if the queue is empty
     * @throws InvalidArgumentException if x is greater than 2, or x is 
     * greater than the size of collection
     * 
     */
    public T dequeue(int x) throws EmptyCollectionException, 
            InvalidArgumentException;
    
    /**
     * Returns (without removing) the element that is at place x in the queue.
     * Precondition: x must be less than 3, x must be less than size
     * Note: indexing from 0: 0 == front element, 1 == second element, etc.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if the queue is empty
     * @throws InvalidArgumentException if x is greater than 2, or x is 
     * greater than the size of collection
     * @param x the specified index of the element to return
     */
    public T first(int x)throws EmptyCollectionException, 
            InvalidArgumentException;
    
    /**
     * Returns an ArrayList of the first three nodes in the queue.
     * @return an ArrayList of nodes of LinearNodes of T data type
     * @throws EmptyCollectionException if the queue is empty
     */
    public ArrayList<LinearNode<T>> firstThreeNodes() throws 
            EmptyCollectionException;
    
    /**
     * Returns an ArrayList of the first three elements in the queue.
     * @return an ArrayList of elements of T data type
     * @throws EmptyCollectionException if the queue is empty
     */
    public ArrayList<T> firstThreeElements() throws EmptyCollectionException;

}
