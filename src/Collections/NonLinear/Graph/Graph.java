package Collections.NonLinear.Graph;

import Collections.Exception.ElementNotFoundException;
import Collections.NonLinear.Interfaces.GraphADT;

import java.util.Iterator;

/**
 * Created by ivo on 16-01-2016.
 */
public abstract class Graph<T> implements GraphADT<T> {

    private final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int numVertices; // number of vertices in the graph
    private T[] vertices; // values of vertices

    public Graph() {
        this.capacity = this.DEFAULT_CAPACITY;
        numVertices = 0;
        this.vertices = (T[]) (new Object[this.capacity]);
    }

    public Graph(int capacity) {
        this.capacity = capacity;
        numVertices = 0;
        this.vertices = (T[]) (new Object[this.capacity]);
    }


    protected int getCapacity() {
        return capacity;
    }

    public int getNumVertices() {
        return numVertices;
    }

    protected void incrementNumVertices() {
        this.numVertices++;
    }

    protected void decrementNumVertices() {
        this.numVertices--;
    }

    protected T[] getVertices() {
        return vertices;
    }

    protected int getIndex(T vertex) throws ElementNotFoundException {
        if (this.numVertices > 0) {
            for (int i=0;i<this.getNumVertices();i++) {
                T vertice = this.vertices[i];
                if (vertice.equals(vertex)) {
                    return i;
                }
            }
        }
        throw new ElementNotFoundException("Vertice não existe");
    }

    protected boolean indexIsValid(int index) {
        return (index < this.numVertices && index >= 0);
    }

    /**
     * Creates new arrays to store the contents of the graph with
     * twice the capacity.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        for (int i = 0; i < numVertices; i++) {
            largerVertices[i] = vertices[i];
        }
        vertices = largerVertices;
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = iteratorBFS(this.vertices[0]);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    /**
     * Returns a breadth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a breadth first iterator beginning at the given
     * <p>
     * vertex
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        try {
            return iteratorBFS(getIndex(startVertex));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Iterator<T> iteratorBFS(int startIndex);

    /**
     * Returns a depth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a depth first iterator starting at the given vertex
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        try {
            return iteratorDFS(getIndex(startVertex));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Iterator<T> iteratorDFS(int startIndex);


    /**
     * Returns an iterator that contains the shortest path between
     * the two vertices.
     *
     * @param startVertex  the starting vertex
     * @param targetVertex the ending vertex
     * @return an iterator that contains the shortest path
     * <p>
     * between the two vertices
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(startVertex, targetVertex);
    }

    public abstract Iterator<T> iteratorShortestPath(int index1, int index2 );


}
