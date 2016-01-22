package Collections.NonLinear.Tree;

import Collections.Exception.ElementNotFoundException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import Collections.Linear.Queue.LinkedQueue;
import Collections.NonLinear.Tree.Interfaces.BinaryTreeADT;

import java.util.Iterator;

/**
 * Created by Ivo on 11/24/15.
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        this.count=0;
    }
    

    public LinkedBinaryTree(T element) {

        this.root = new BinaryTreeNode<>(element);
        this.count++;
    }

    /**
     * Returns a reference to the root element
     *
     * @return a reference to the root
     */
    @Override
    public T getRoot() {
        return this.root.getElement();
    }

    /**
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the integer number of elements in this tree
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns true if the binary tree contains an element that
     * matches the specified element and false otherwise.
     *
     * @param targetElement the element being sought in the tree
     * @return true if the tree contains the target element
     */
    @Override
    public boolean contains(T targetElement) throws UnsupportedOperationException {
        if (targetElement instanceof Comparable) {
            Iterator<T> iterator = this.iteratorInOrder();
            while (iterator.hasNext()) {
                iterator.next();
                if (targetElement.equals(targetElement)) {
                    return true;
                }
            }
            return false;
        } else throw new UnsupportedOperationException("The element is not comparable");

    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.  Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found
     *                                  exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, this.root);
        if (current == null)
            throw new ElementNotFoundException("binary tree");
        return (current.getElement());
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement,
                                        BinaryTreeNode<T> next) {
        if (next == null)
            return null;
        if (next.getElement().equals(targetElement))
            return next;
        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());
        if (temp == null)
            temp = findAgain(targetElement, next.getRight());
        return temp;
    }


    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return an in order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<T>();
        inorder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a preorder traversal on this binary tree by calling an
     * overloaded, recursive preorder method that starts
     * with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();
        preorder(this.root, tempList);
        return tempList.iterator();
    }

    private void preorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a postorder traversal on this binary tree by
     * calling an overloaded, recursive postorder
     * method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        LinkedUnorderedList<T> tempList = new LinkedUnorderedList<>();
        preorder(this.root, tempList);
        return tempList.iterator();
    }

    private void postorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    /**
     * Performs a levelorder traversal on the binary tree,
     * using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {

        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>(this.root);
        UnorderedListADT<T> results = new LinkedUnorderedList<>();


        return null;
    }


}
