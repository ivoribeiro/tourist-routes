/*
 *
 */
package Collections.Node;

/**
 * @param <T> Any data type
 * @author Ivo Ribeiro
 */
public class LinearNode<T> {

    protected T element;
    protected LinearNode<T> next;

    //Constructors

    /**
     * Creates a Linear Node with a element
     *
     * @param element a value of any data type
     */
    public LinearNode(T element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Creates a full Linear Node
     *
     * @param element a value of any data type
     * @param next    the next Linear Node to set
     */
    public LinearNode(T element, LinearNode<T> next) {
        this.element = element;
        this.next = next;
    }

    //Access Methods

    /**
     * Gets the Linear Node element
     *
     * @return the element
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Sets the Linear Node element
     *
     * @param element a value of any data type
     */
    public final void setElement(T element) {
        this.element = element;
    }

    /**
     * Gets the next Linear Node reference
     *
     * @return the next linear node
     */
    public LinearNode<T> getNext() {
        return this.next;
    }

    /**
     * Set the next Linear Node reference
     *
     * @param next the next Linear Node to set
     */
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }

    /**
     * String representation of the element stored at node
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "LinearNode{" + "element=" + this.element + "}";
    }

}
