package dsassignment3;

/**
 * An Empty Collection Exception class.
 * Prints out what type of collection is empty
 * for use in ITCS 2214 Data Structures and Algorithms
 * UNC Charlotte, 2016
 * @author clatulip
 * @version 0
 */
public class EmptyCollectionException extends Exception {

    /**
     * A project-specific exception for this 2214 course.
     * @param collection - A String identifying the class throwing the error
     */
    public EmptyCollectionException(String collection) {
        super("The " + collection + " is empty.");
    }
    
}
