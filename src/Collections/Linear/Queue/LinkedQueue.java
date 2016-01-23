/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.Queue;

import Collections.Exception.EmptyQueueException;
import Collections.Linear.Interfaces.QueueADT;
import Collections.Node.LinearNode;

/**
 * @param <T>
 * @author aluno
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> front;
    private LinearNode<T> rear;
    private int size = 0;

    public LinkedQueue() {

        this.front = null;
        this.rear = null;
    }

    public LinkedQueue(T element) {
        LinearNode<T> temp = new LinearNode(element);
        this.front = temp;
        this.rear = temp;
        ++this.size;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> temp = new LinearNode(element);

        if (this.isEmpty()) {
            this.front = temp;
        } else {
            this.rear.setNext(temp);
        }
        this.rear = temp;
        ++this.size;

    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.isEmpty()) {
            throw new EmptyQueueException("Queue Vazia");
        } else {
            T temp = this.front.getElement();
            this.front = this.front.getNext();
            --this.size;
            return temp;
        }
    }

    @Override
    public T first() throws EmptyQueueException {
        if (this.isEmpty()) {
            return this.front.getElement();
        } else {
            throw new EmptyQueueException("Queue Vazia");
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
    
    public String toString(){
        String temp = "\n";
        LinearNode<T> frontTemp = front;

        while (frontTemp != null) {
            temp += frontTemp.getElement().toString() + "\t";
            frontTemp = frontTemp.getNext();
        }
        return temp;
    }
}
