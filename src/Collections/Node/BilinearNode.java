/*
 * 
 */
package Collections.Node;

/**
 * @param <T> Any data type
 * @author Ivo Ribeiro
 */
public class BilinearNode<T> {

    private BilinearNode<T> prev;
    private BilinearNode<T> next;
    private T element;

    //Constructors

    /**
     * Creates a Bilinear Node with a element
     *
     * @param element Value to be saved
     */
    public BilinearNode(T element) {
        this.element = element;
        this.prev = null;
        this.next = null;
    }

    /**
     * Creates a full Bilinear Node
     *
     * @param element element to be saved
     * @param next    reference to the previous node
     * @param prev    reference to the next node
     */
    public BilinearNode(T element, BilinearNode<T> prev, BilinearNode<T> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Gets the Bilinear Node element
     *
     * @return the element
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the Bilinear Node element
     *
     * @param element a value of any data type
     */
    public final void setElement(T element) {
        this.element = element;
    }

    /**
     * Gets the previous Bilinear Node reference
     *
     * @return the previous Bilinear Node
     */
    public BilinearNode<T> getPrev() {
        return this.prev;
    }


    /**
     * Sets the previous Bilinear Node reference
     *
     * @param prev the previous Bilinear Node to set
     */
    public void setPrev(BilinearNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Gets the next Linear Node reference
     *
     * @return the next linear node
     */
    public BilinearNode<T> getNext() {

        return this.next;
    }


    /**
     * Set the next Bilinear Node reference
     *
     * @param next the next Linear Node to set
     */
    public void setNext(BilinearNode<T> next) {
        this.next = next;
    }

    /**
     * String representation of the element stored at node
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "BilinearNode{" + "element=" + this.getElement() + "}";
    }

}
