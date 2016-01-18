package Collections.NonLinear.Network;

import Collections.Exception.ElementNotFoundException;
import Collections.NonLinear.Graph.matrix.adjMatrixUndGraph;
import Collections.NonLinear.Interfaces.NetworkADT;

/**
 * Created by ivo on 13-01-2016.
 */
public class UndNetworkAdjMatrix<T> extends adjMatrixUndGraph<T> implements NetworkADT<T> {

    private double[][] weights;

    public UndNetworkAdjMatrix() {
        super();
        weights = new double[this.getNumVertices()][this.getNumVertices()];
    }

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight
     *                Extamente igual a implentação de uma network nao direcionada
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        this.addEdge(vertex1, vertex2);
        try {
            this.weights[getIndex(vertex1)][getIndex(vertex2)] = weight;
            this.weights[getIndex(vertex2)][getIndex(vertex1)] = weight;
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {
        super.removeVertex(vertex);

        try {
            removeVertex(getIndex(vertex));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes a vertex from the network
     *
     * @param index, index of the vertex to be removed
     */
    public void removeVertex(int index) {

        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int j = index; j < this.getNumVertices(); j++) {
                weights[i][j] = weights[i + 1][j];
                weights[j][i] = weights[j + 1][i];
            }
        }

    }

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     */
    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        return 0;
    }
}
