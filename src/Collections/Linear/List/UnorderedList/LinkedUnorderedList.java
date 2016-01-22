/*
 * To change this license fronter, choose License fronters in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.List.UnorderedList;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.LinkedList;
import Collections.Node.LinearNode;


/**
 * @author Ivo Ribeiro
 * @param <T>
 */
public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    public LinkedUnorderedList() {
        super();
    }

    //UnorderedListAdt methods

    /**
     * Add a new element to the front of the list
     *
     * @param element anything
     */
    @Override
    public void addToFront(T element) {
        LinearNode<T> temp = new LinearNode(element);
        if (isEmpty()) {
            this.rear = temp;
        } else {
            temp.setNext(this.front);
        }
        this.front = temp;
        ++this.size;
    }

    /**
     * Add a new element to the rear of the list
     *
     * @param element anything
     */
    @Override
    public void addToRear(T element) {
        LinearNode<T> temp = new LinearNode(element);
        if (this.isEmpty()) {
            this.front = temp;
        } else {
            this.rear.setNext(temp);
        }
        this.rear = temp;
        ++this.size;
    }

    /**
     * Add a new element to next of the target
     *
     * @param element anything
     * @param target  the target element
     * @throws Collections.Exception.ElementNotFoundException
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        try {
            if (this.contains(target)) {
                BasicIterator<T> basicIterator = (BasicIterator<T>) this.iterator();
                boolean found = false;
                LinearNode<T> prev = this.front;
                while (basicIterator.hasNext() && found != true) {
                    prev = basicIterator.getCurrent();
                    if (basicIterator.next().equals(target)) {
                        found = true;
                    }
                }
                LinearNode<T> newNode = new LinearNode(element);
                //The target next's will be the new one next's
                newNode.setNext(prev.getNext());
                //The target next's will be the newNode
                prev.setNext(newNode);
            } else throw new ElementNotFoundException("The target doesn't exist");
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}


