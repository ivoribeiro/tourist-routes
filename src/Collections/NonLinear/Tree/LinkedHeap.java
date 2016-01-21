package Collections.NonLinear.Tree;

import Collections.NonLinear.Tree.Interfaces.HeapADT;

/**
 * Created by aluno on 1/5/16.
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {

    public LinkedHeap(T element) {
        super(element);
    }

    @Override
    public void addElement(T obj) {

    }

    @Override
    public T removeMin() {
        return null;
    }

    @Override
    public T findMin() {
        return null;
    }
}
