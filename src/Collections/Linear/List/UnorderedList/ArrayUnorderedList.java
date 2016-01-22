package Collections.Linear.List.UnorderedList;

import Collections.Exception.ElementNotFoundException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.ArrayList;

/**
 * Created by ivo on 22-11-2015.
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {
    //TODO this Class

    /**
     * Add a new element to the front of the list
     *
     * @param element anything
     */
    @Override
    public void addToFront(T element) {

    }

    /**
     * Add a new element to the rear of the list
     *
     * @param element anything
     */
    @Override
    public void addToRear(T element) {

    }

    /**
     * Add a new element to next of the target
     *
     * @param element anything
     * @param target  the target element
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {

    }
}
