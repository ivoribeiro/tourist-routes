package Collections.NonLinear.Graph.list;


import Collections.NonLinear.Interfaces.GraphADT;

/**
 * Created by ivo on 16-01-2016.
 */
public class AdjListUndGraph<T> extends AdjListDiGraph<T> implements GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph
     * .
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        super.addEdge(vertex1, vertex2);
    }

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {
        super.removeVertex(vertex);
    }

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        super.removeEdge(vertex1, vertex2);
    }
}
