package Collections.NonLinear.Graph.matrix;

import Collections.Exception.EdgeNotFoundException;
import Collections.Exception.ElementNotFoundException;
import Collections.NonLinear.Interfaces.GraphADT;


/**
 * Created by ivo on 13-01-2016.
 */
public class adjMatrixUndGraph<T> extends adjMatrixDiGraph<T> implements GraphADT<T> {

    public adjMatrixUndGraph() {
    }

    public adjMatrixUndGraph(int capacity) {
        super(capacity);
    }

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     */
    @Override
    public void removeVertex(T vertex) {
        try {
            int indexToRemove = getIndex(vertex);
            this.decrementNumVertices();
            //remove o vertex
            for (int i = indexToRemove; i < this.getNumVertices(); i++) {
                this.getVertices()[i] = this.getVertices()[i + 1];
                System.arraycopy(this.getAdjMatrix()[i], 0, this.getAdjMatrix()[i + 1], 0, this.getNumVertices());
            }
            //remove da matriz de adjacencia
            for (int i = 0; i < this.getNumVertices(); i++) {
                for (int j = indexToRemove; j < this.getNumVertices(); j++) {
                    this.getAdjMatrix()[i][j] = this.getAdjMatrix()[i][j + 1];
                    this.getAdjMatrix()[j][i] = this.getAdjMatrix()[j][i + 1];
                }
            }

        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts an unidirectional edge between two vertices of the graph.
     *
     * @param vertex1, the first vertex
     * @param vertex2, the second vertex
     */
    public void addEdge(T vertex1, T vertex2) {
        try {
            addEdge(getIndex(vertex1), getIndex(vertex2));
            addEdge(getIndex(vertex2), getIndex(vertex1));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {

        try {
            boolean exists = this.getAdjMatrix()[getIndex(vertex1)][getIndex(vertex2)] && this.getAdjMatrix()[getIndex(vertex2)][getIndex(vertex1)];
            if (!exists) throw new EdgeNotFoundException("A aresta não existe");
            else {
                removeEdge(getIndex(vertex1), getIndex(vertex2));
                removeEdge(getIndex(vertex2), getIndex(vertex1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
