/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TouristRoutes;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.List.LinkedList;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import Collections.NonLinear.Graph.Graph;
import Collections.NonLinear.Graph.matrix.adjMatrixDiGraph;
import Collections.NonLinear.Interfaces.GraphADT;

/**
 *
 * @author luis_
 */
public class search {
    
    private static final String START = "B";
    private static final String END = "E";

    public static void main(String[] args) throws ElementNotFoundException, EmptyCollectionException {
        // this graph is directional
        adjMatrixDiGraph<String> graph = new adjMatrixDiGraph<>();
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
        LinkedUnorderedList<String> visited = new LinkedUnorderedList<>();
        
        graph.breadthFirstTravesal(visited,START, END);
    }

    
}
