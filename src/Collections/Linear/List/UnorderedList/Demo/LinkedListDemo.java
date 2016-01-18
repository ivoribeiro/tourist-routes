package Collections.Linear.List.UnorderedList.Demo;

import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

/**
 * Created by ivo on 14-11-2015.
 * This class is a Demo class for linked list implementation
 */
public final class LinkedListDemo {
    public static void main() {
        UnorderedListADT<Integer> integerList = new LinkedUnorderedList();
        System.out.println("Size:" + ' ' + integerList.size());
        System.out.println("Is empty?" + ' ' + integerList.isEmpty());
        //Testar first() and last()
        try {
            System.out.println("--------Mostrar primeiro elemento da lista--------");
            System.out.println("Primeiro elemento da lista:" + integerList.first());

        } catch (EmptyCollectionException e) {
            System.out.println("A lista não tem o primeiro elemento porque está vazia");
            System.out.println("---------------------------------------------------");

        }

        try {
            System.out.println("--------Mostrar ultimo elemento da lista--------");
            System.out.println("Primeiro elemento da lista:" + integerList.last());

        } catch (EmptyCollectionException e) {
            System.out.println("A lista não tem o ultimo elemento porque está vazia");
            System.out.println("---------------------------------------------------");

        }
        //Testar removeFirst() removeLast()
        try {
            System.out.println("--------Remover primeiro elemento da lista--------");
            System.out.println("Elemento removido:" + integerList.removeFirst());

        } catch (EmptyCollectionException e) {
            System.out.println("Impossivel remover ultimo elemento porque a lista está vazia");
            System.out.println("---------------------------------------------------");

        }
        try {
            System.out.println("--------Remover ultimo elemento da lista--------");
            System.out.println("Elemento removido:" + integerList.removeLast());

        } catch (EmptyCollectionException e) {
            System.out.println("Impossivel remover ultimo elemento porque a lista está vazia");
            System.out.println("---------------------------------------------------");

        }

        integerList.addToFront(10);
        integerList.addToFront(20);
        integerList.addToFront(30);
        integerList.addToRear(5);
        integerList.addToRear(2);
        integerList.addToRear(1);

        System.out.println("------- Remover primeiro e o ultimo elemento da lista -------");
        try {
            integerList.removeFirst();
            System.out.println(integerList.first());
            integerList.removeLast();
            System.out.println(integerList.last());
        } catch (EmptyCollectionException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(integerList.contains(5));
        } catch (EmptyCollectionException e) {
            e.printStackTrace();
        }


    }
}
