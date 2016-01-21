/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.Linear.Queue;

import Collections.Exception.EmptyQueueException;
import Collections.Linear.Interfaces.QueueADT;

/**
 *
 * @author aluno
 * @param <T>
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private T[] array;
    private int front;
    private int rear;
    private int lenght;
    private int count;

    public CircularArrayQueue(int lenght) {
        this.array = (T[]) new Object[lenght];
        this.front = 0;
        this.rear = 0;
        this.lenght = lenght;
        this.count = 0;

    }

    @Override
    public void enqueue(T element) {
        if (this.count == this.lenght) {
            this.expand();
        }

        this.array[this.rear] = element;
        rear = rear + 1 % this.lenght;
        ++this.count;

    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.isEmpty()) {
            throw new EmptyQueueException("Queue Vazia");
        } else {
            T temp = this.array[this.front];
            this.front = this.front + 1 % this.lenght;
            --count;
            return temp;
        }
    }

    @Override
    public T first() throws EmptyQueueException {
        if (this.isEmpty()) {
            throw new EmptyQueueException("Queue Vazia");
        } else {
            return this.array[this.front];
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    private void expand() {
        T[] temp = (T[]) new Object[this.lenght * 2];
        int newCount = 0;
        for (int i = this.front; i < this.rear; i++) {
            temp[newCount] = this.array[i];
            ++newCount;
        }
        this.array = temp;
        this.lenght = this.lenght * 2;
        this.front = 0;
        this.rear = this.count - 1;
    }

    private void expand2() throws EmptyQueueException {
        int newsize = this.lenght * 2;
        CircularArrayQueue tempQueue = new CircularArrayQueue(newsize);
        while (this.count != 0) {
            enqueue(dequeue());
        }
        this.lenght = newsize;
        this.front = tempQueue.front;
        this.rear = tempQueue.rear;
        this.array = (T[]) tempQueue.array;

    }

}
