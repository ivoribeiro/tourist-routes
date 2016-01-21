/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.Stack;


import Collections.Linear.Interfaces.StackADT;
import Collections.Node.LinearNode;

import java.util.EmptyStackException;

/**
 * @param <T>
 * @author aluno
 */
public class LinkedStack<T> implements StackADT<T> {

    private LinearNode<T> top;
    private int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        LinearNode node = new LinearNode(element);
        if (!this.isEmpty()) {
            node.setNext(this.top);
        }
        this.top = node;
        this.size++;
    }

    @Override
    public T pop() throws EmptyStackException {

        if (this.isEmpty()) {
            throw new EmptyStackException();
        } else {
            LinearNode tempNode = this.top;
            this.top = this.top.getNext();
            return (T) tempNode.getElement();

        }

    }

    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return (T) this.top.getElement();
        }

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {

        return this.size;

    }

    public void printStack() {
        LinearNode temp = this.top;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }

    }

}
