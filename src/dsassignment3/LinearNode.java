package dsassignment3;

/**
 * A simple Linear Node for use in linked structures such as lists.
 *
 * @author clatulip
 * @param <T> The data type of the object being managed by this node.
 * @version 0
 */
public class LinearNode<T> {

    private LinearNode<T> next;  // Pointer to the next LinearNode in the list
    private LinearNode<T> prev;  // Pointer to the last LinearNode in the list
    private T element;           // Pointer to the object managed by this node

    /**
     * Default constructor creates an empty node.
     */
    public LinearNode() {
        next = null;
        prev = null;
        element = null;
    }

    /**
     * Creates a node to manage the element.
     *
     * @param elem element of data type T to be referenced
     */
    public LinearNode(T elem) {
        next = null;
        prev = null;
        element = elem;
    }

    /**
     * Gets the pointer to the node that follows this one.
     *
     * @return next a pointer to the LinearNode that follows this one
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param n a pointer to the LinearNode that follows this one
     */
    public void setNext(LinearNode<T> n) {
        next = n;
    }

    /**
     * Gets the node that is before this one.
     *
     * @return a pointer to the LinearNode that precedes this one
     */
    public LinearNode<T> getPrev() {
        return prev;
    }

    /**
     * Sets the node that is before this one.
     *
     * @param p a pointer to the LinearNode that precedes this one
     */
    public void setPrev(LinearNode<T> p) {
        prev = p;
    }

    /**
     * Returns the element referenced by this node.
     *
     * @return a pointer to the element referenced by this node
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element referenced by this node.
     *
     * @param elem - the object of T data type referenced by this node
     */
    public void setElement(T elem) {
        element = elem;
    }

    /**
     * Returns a String representing this node.
     *
     * @return the String representation of this node
     */
    @Override
    public String toString() {
        return "{el=" + element + ", next=" + next + ", prev=" + prev + "}";
    }

}
