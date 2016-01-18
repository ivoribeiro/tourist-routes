package Collections.Exception;

/**
 * This exception indicates that the element does not exist in the collection.
 */
public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String message) {
        super(message);
    }

}
