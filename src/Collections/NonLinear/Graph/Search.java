/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collections.NonLinear.Graph;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import Collections.NonLinear.Graph.matrix.adjMatrixDiGraph;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search {

    private static final String START = "B";
    private static final String END = "E";

    public static void main(String[] args) {
        // this graph is directional
        adjMatrixDiGraph<String> graph = new adjMatrixDiGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "A");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E"); // this is the only one-way connection
        graph.addEdge("B", "F");
        graph.addEdge("C", "A");
        graph.addEdge("C", "E");
        graph.addEdge("C", "F");
        graph.addEdge("D", "B");
        graph.addEdge("E", "C");
        graph.addEdge("E", "F");
        graph.addEdge("F", "B");
        graph.addEdge("F", "C");
        graph.addEdge("F", "E");
        LinkedUnorderedList<String> visited = new LinkedUnorderedList();
        visited.addToRear(START);
        new Search().breadthFirst(graph, visited);
    }

    private void breadthFirst(adjMatrixDiGraph<String> graph, LinkedUnorderedList<String> visited) {

        try {
            //lista de nos
            LinkedUnorderedList<String> nodes = null;
            try {
                //lista de nos adjacentes á raiz actual
                nodes = (LinkedUnorderedList<String>) graph.adjacentNodes(visited.last());
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
            // examine adjacent nodes
            for (String node : nodes) {
                //se o no adjacente ja foi visitado continua
                if (visited.contains(node)) {
                    continue;
                }
                if (node.equals(END)) {
                    visited.addToRear(node);
                    printPath(visited);
                    visited.removeLast();
                    break;
                }
            }
            // in breadth-first, recursion needs to come after visiting adjacent nodes
            for (String node : nodes) {
                if (visited.contains(node) || node.equals(END)) {
                    continue;
                }
                visited.addToRear(node);
                breadthFirst(graph, visited);
                visited.removeLast();
            }
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printPath(LinkedUnorderedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }
}
