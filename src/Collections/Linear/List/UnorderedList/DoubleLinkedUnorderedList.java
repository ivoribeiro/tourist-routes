package Collections.Linear.List.UnorderedList;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.DoubleLinkedList;
import Collections.Node.BilinearNode;

/**
 * Created by ivo on 22-11-2015.
 */
public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    /**
     * Add a new element to the front of the list
     *
     * @param element anything
     */
    @Override
    public void addToFront(T element) {

        BilinearNode<T> temp = new BilinearNode(element);
        if (this.isEmpty()) {
            this.rear = temp;
        } else {
            this.front.setPrev(temp);
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

        BilinearNode<T> temp = new BilinearNode(element);
        if (this.isEmpty()) {
            this.front = temp;
        } else {
            temp.setPrev(this.rear);
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
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        try {
            if (this.contains(target)) {
                BasicIterator<T> basicIterator = (BasicIterator<T>) this.iterator();
                boolean found = false;
                BilinearNode<T> prev = this.front;
                while (basicIterator.hasNext() && found != true) {
                    prev = basicIterator.getCurrent();
                    if (basicIterator.next().equals(target)) {
                        found = true;
                    }
                }
                BilinearNode<T> newNode = new BilinearNode(element);
                //The target next's will be the new one next's
                newNode.setNext(prev.getNext());
                //The target next's will be the newNode
                prev.setNext(newNode);
            } else throw new ElementNotFoundException("The target doesn't exist");
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
        }

    }
}

