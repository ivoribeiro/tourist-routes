/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.List;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.ListADT;
import Collections.Node.BilinearNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <T>
 * @author Ivo Ribeiro This class implements a double linked List.
 */
public class DoubleLinkedList<T> implements ListADT<T> {
    protected int size = 0;
    protected BilinearNode<T> front;
    protected BilinearNode<T> rear;


    public DoubleLinkedList() {
        this.front = null;
        this.rear = null;
    }

    public BilinearNode<T> getFront() {
        return front;
    }

    public BilinearNode<T> getRear() {
        return rear;
    }

    /**
     * Removes and returns the first element from this List.
     *
     * @return the first element from this List
     * @throws EmptyCollectionException
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            T tempElement = this.first();
            this.front.getNext().setPrev(null);
            //The new first node will be the next of the old one
            this.front = this.front.getNext();
            --this.size;
            //return the element of the new first node
            return tempElement;
        }
    }

    /**
     * Removes and returns the last element from this List.
     *
     * @return the last element from this List
     * @throws EmptyCollectionException
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (this.isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            T toReturn = last();
            this.rear.getPrev().setNext(null);
            this.rear = this.rear.getPrev();
            return toReturn;
        }
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
        if (this.isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            if (!this.contains(target)) throw new ElementNotFoundException("The element doesn't exist");
            else {
                if (this.size() == 1) {
                    this.front = null;
                    this.rear = null;
                    return first();
                } else if (this.last().equals(target)) return this.removeLast();
                else if (this.first().equals(target)) return this.removeFirst();
                else {
                    BasicIterator<T> basicIterator = (BasicIterator<T>) this.iterator();
                    boolean found = false;
                    while (basicIterator.hasNext() && found == false) {
                        if (basicIterator.next().equals(target)) {
                            found = true;
                        }
                    }
                    basicIterator.getCurrent().getPrev().setNext(basicIterator.getCurrent());
                }
                --this.size;
                return target;
            }
        }
    }

    /**
     * Returns a reference to the first element in this List.
     *
     * @return a reference to the first element in this List
     * @throws EmptyCollectionException
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            return this.front.getElement();
        }
    }

    /**
     * Returns a reference to the last element in this List.
     *
     * @return a reference to the last element in this List
     * @throws EmptyCollectionException
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            return this.rear.getElement();
        }
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
        if (this.isEmpty()) throw new EmptyCollectionException("Empty Double Linked List");
        else {
            BasicIterator<T> basicIterator = (BasicIterator<T>) this.iterator();
            while (basicIterator.hasNext()) {
                //if the current iterator element is equals to target
                if (basicIterator.next().equals(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns true if this List contains no elements.
     *
     * @return true if this List contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.front == null;
    }

    /**
     * Returns the number of elements in this List.
     *
     * @return the integer representation of number of elements in this List
     */
    @Override
    public int size() {
        return this.size;
    }

    public DoubleLinkedList<T> inverter() {
        //TODO this method
        return null;
    }

    /**
     * Returns an iterator for the elements in this List.
     *
     * @return an iterator over the elements in this List
     */
    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    public class BasicIterator<T> implements Iterator<T> {

        private BilinearNode<T> current;

        public BasicIterator() {
            this.current = (BilinearNode<T>) DoubleLinkedList.this.front;
        }

        public BilinearNode<T> getCurrent() {
            return current;
        }

        @Override
        public boolean hasNext() {
            return this.current.getNext() != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) throw new NoSuchElementException();
            else {
                //the previous will be the current and the current will be the old one next's
                T element = this.current.getElement();
                this.current = this.current.getNext();
                return element;
            }
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
            // try {
            //LinkedList.this.remove((T)this.next());
            //} catch (EmptyCollectionException ex) {
            //     System.out.println(ex.getMessage());
            // } catch (ElementNotFoundException e) {
            //   System.out.println(e.getMessage());
            //}
            // }
        }
    }
}
