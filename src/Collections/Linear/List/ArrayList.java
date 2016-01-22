package Collections.Linear.List;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.ListADT;

import java.util.Iterator;

/**
 * Created by ivo on 22-11-2015.
 */
public class ArrayList<T> implements ListADT<T> {
    //TODO This class

    /**
     * Removes and returns the first element from this List.
     *
     * @return the first element from this List
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        return null;
    }

    /**
     * Removes and returns the last element from this List.
     *
     * @return the last element from this List
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        return null;
    }

    /**
     * Removes and returns the specified element from this List.
     *
     * @param target the element to be removed from the List
     * @return T
     * @throws EmptyCollectionException
     * @throws ElementNotFoundException
     */
    @Override
    public T remove(T target) throws EmptyCollectionException, ElementNotFoundException {
        return null;
    }

    /**
     * Returns a reference to the first element in this List.
     *
     * @return a reference to the first element in this List
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        return null;
    }

    /**
     * Returns a reference to the last element in this List.
     *
     * @return a reference to the last element in this List
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        return null;
    }

    /**
     * Returns true if this List contains the specified target element.
     *
     * @param target the target that is being sought in the List
     * @return true if the List contains this element
     * @throws EmptyCollectionException
     */
    @Override
    public boolean contains(T target) throws EmptyCollectionException {
        return false;
    }

    /**
     * Returns true if this List contains no elements.
     *
     * @return true if this List contains no elements
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the number of elements in this List.
     *
     * @return the integer representation of number of elements in this List
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns an iterator for the elements in this List.
     *
     * @return an iterator over the elements in this List
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
