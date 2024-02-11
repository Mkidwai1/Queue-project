package dsassignment3;

/**
 * Exception handler for when an element isn't in a collection.
 * @author clatulip
 * @version 0
 */
public class InvalidArgumentException extends Exception {
    
    /**
     * A project-specific exception for this 2214 course.
     * @param arg - the String to be added to the exception statement
     */
    public InvalidArgumentException(String arg) {
        super("The argument " + arg + "was invalid.");
    }
    
}
