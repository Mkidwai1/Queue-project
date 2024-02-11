package dsassignment3;

/**
 * An interface for a Queue.Specific queue implementations will implement this interface
 * For use in ITCS 2214 Data Structures and Algorithms
 * UNC Charlotte, 2016
 * @author clatulip, revised spring 2020 Bruce Long
 * @version 0
 * @param <T> - The generic data type of the objects in this queue
 */
public interface QueueADT<T> extends CollectionADT<T> {
    
    /**
     * Adds the specified element to the back of the queue.
     * @param element element to be added to the end of the queue
     */
    public void enqueue(T element);
    
    /**
     * Removes and returns the element that is at the front of the queue.
     * @return the element removed from the queue
     * @throws EmptyCollectionException if queue is empty
     */
    public T dequeue() throws EmptyCollectionException;
    
    /**
     * Returns (without removing) the element that is at the front of the queue.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if queue is empty
     */
    public T first()throws EmptyCollectionException;
    
}
