package Collections.NonLinear.Graph.list;

import Collections.Linear.Interfaces.OrderedListADT;
import Collections.NonLinear.Graph.Graph;
import Collections.NonLinear.Interfaces.GraphADT;

import java.util.Iterator;

/**
 * Created by ivo on 16-01-2016.
 */
public class AdjListDiGraph<T> extends Graph<T> implements GraphADT<T> {

    protected OrderedListADT<Boolean> adjList[]; // adjacency list

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex the vertex to be added to this graph
     */
    @Override
    public void addVertex(T vertex) {

    }

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {

    }

    /**
     * Inserts an edge between two vertices of this graph
     * .
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {

    }

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {

    }

    @Override
    public Iterator<T> iteratorBFS(int startIndex) {
        return null;
    }

    @Override
    public Iterator<T> iteratorDFS(int startIndex) {
        return null;
    }

    @Override
    public Iterator<T> iteratorShortestPath(int index1, int index2) {
        return null;
    }

}
