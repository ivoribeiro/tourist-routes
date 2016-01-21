package Collections.Linear.List.OrderedList;

import Collections.Linear.Interfaces.OrderedListADT;
import Collections.Linear.List.LinkedList;
import Collections.Node.LinearNode;

/**
 * Created by ivo on 22-11-2015.
 * This class is the implementation of a Linked List , moore in concrete , a Linked Ordered List
 */
public class LinkedOrderedList<T extends Comparable<T>> extends LinkedList<T> implements OrderedListADT<T> {

    public LinkedOrderedList() {
        super();
    }

    public LinkedOrderedList(T element) {
        super(element);
    }

    /**
     * Adds the specified element to this List at the proper location
     *
     * @param element the element to be added to this List
     */

    @Override
    public void add(T element) {
        if (isEmpty()) {
            LinearNode<T> node = new LinearNode<>(element);
            this.front = node;
            this.rear = node;
        }
        LinearNode<T> prev;
        boolean found = false;
        BasicIterator<T> iterator = (BasicIterator<T>) this.iterator();
        while (iterator.hasNext() && found == false) {
            prev = iterator.getCurrent();
            int compare = element.compareTo(iterator.next());
            switch (compare) {
                case -1:
                case 0:
                    found = true;
                    LinearNode<T> node = new LinearNode<>(element);
                    node.setNext(iterator.getCurrent());
                    prev.setNext(node);
                    break;
            }
        }

    }
}
