/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.Interfaces;

import Collections.Exception.ElementNotFoundException;

/**
 * @param <T>
 * @author Ivo Ribeiro
 */
public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * Add a new element to the front of the list
     *
     * @param element anything
     */
    public void addToFront(T element);

    /**
     * Add a new element to the rear of the list
     *
     * @param element anything
     */
    public void addToRear(T element);

    /**
     * Add a new element to next of the target
     *
     * @param element anything
     * @param target  the target element
     */
    public void addAfter(T element, T target) throws ElementNotFoundException;

}
