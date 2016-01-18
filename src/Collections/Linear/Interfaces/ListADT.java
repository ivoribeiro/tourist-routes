/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.Interfaces;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;

import java.util.Iterator;


/**
 * @param <T>
 * @author Ivo Ribeiro
 */
public interface ListADT<T> extends Iterable<T> {

    /**
     * Removes and returns the first element from this List.
     *
     * @return the first element from this List
     * @throws Collections.Exception.EmptyCollectionException
     */
    public T removeFirst() throws EmptyCollectionException;

    /**
     * Removes and returns the last element from this List.
     *
     * @return the last element from this List
     * @throws Collections.Exception.EmptyCollectionException
     */
    public T removeLast() throws EmptyCollectionException;

    /**
     * Removes and returns the specified element from this List.
     *
     * @param target the element to be removed from the List
     * @return T
     * @throws Collections.Exception.EmptyCollectionException
     * @throws Collections.Exception.ElementNotFoundException
     */
    public T remove(T target) throws EmptyCollectionException, ElementNotFoundException;

    /**
     * Returns a reference to the first element in this List.
     *
     * @return a reference to the first element in this List
     * @throws Collections.Exception.EmptyCollectionException
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns a reference to the last element in this List.
     *
     * @return a reference to the last element in this List
     * @throws Collections.Exception.EmptyCollectionException
     */
    public T last() throws EmptyCollectionException;

    /**
     * Returns true if this List contains the specified target element.
     *
     * @param target the target that is being sought in the List
     * @return true if the List contains this element
     * @throws Collections.Exception.EmptyCollectionException
     */
    public boolean contains(T target) throws EmptyCollectionException;

    /**
     * Returns true if this List contains no elements.
     *
     * @return true if this List contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this List.
     *
     * @return the integer representation of number of elements in this List
     */
    public int size();

    /**
     * Returns an iterator for the elements in this List.
     *
     * @return an iterator over the elements in this List
     */
    @Override
    public Iterator<T> iterator();

    /**
     * Returns a string representation of this List.
     *
     * @return a string representation of this List
     */
    @Override
    public String toString();

}
