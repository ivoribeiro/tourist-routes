package Collections.NonLinear.Graph.matrix;

import Collections.Exception.EdgeNotFoundException;
import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyQueueException;
import Collections.Linear.List.UnorderedList.ArrayUnorderedList;
import Collections.Linear.Queue.LinkedQueue;
import Collections.Linear.Stack.LinkedStack;
import Collections.NonLinear.Graph.Graph;
import Collections.NonLinear.Interfaces.GraphADT;

import java.util.Iterator;

public class adjMatrixDiGraph<T> extends Graph<T> implements GraphADT<T> {

    private boolean[][] adjMatrix; // adjacency matrix

    protected boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    public adjMatrixDiGraph() {
        super();
        this.adjMatrix = (new boolean[this.getCapacity()][this.getCapacity()]);
    }

    public adjMatrixDiGraph(int capacity) {
        super(capacity);
        this.adjMatrix = (new boolean[this.getCapacity()][this.getCapacity()]);
    }

    protected boolean edgeExists(T vertex1, T vertex2) {
        try {
            return adjMatrix[this.getIndex(vertex1)][this.getIndex(vertex2)];
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     */
    protected void expandCapacity() {
        super.expandCapacity();
        boolean[][] largerAdjMatrix = (new boolean[this.getNumVertices() * 2][this.getNumVertices() * 2]);
        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int j = 0; j < getNumVertices(); j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = largerAdjMatrix;
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        try {
            addEdge(getIndex(vertex1), getIndex(vertex2));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1 the first index
     * @param index2 the second index
     */
    protected void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {

        try {
            boolean[][] adjMatrix = new boolean[0][];
            boolean exists = adjMatrix[getIndex(vertex1)][getIndex(vertex2)];
            if (!exists) {
                throw new EdgeNotFoundException("A aresta não existe");
            } else {
                removeEdge(getIndex(vertex1), getIndex(vertex2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes a unidirectional edge between two vertices
     *
     * @param index1, the index of first vertex of the edge
     * @param index2, the index of second vertex of the edge
     * @throws Exception
     */
    protected void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
        }
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        if (this.getNumVertices() == this.getVertices().length) {
            expandCapacity();
        }
        this.getVertices()[this.getNumVertices()] = vertex;
        for (int i = 0; i <= this.getNumVertices(); i++) {
            adjMatrix[this.getNumVertices()][i] = false;
            adjMatrix[i][this.getNumVertices()] = false;
        }
        this.incrementNumVertices();
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
            for (int i = indexToRemove; i < this.getNumVertices(); i++) {
                this.getVertices()[i] = this.getVertices()[i + 1];
                System.arraycopy(this.adjMatrix[i + 1], 0, this.adjMatrix[i], 0, this.getNumVertices());
            }

            for (int i = 0; i < this.getNumVertices(); i++) {
                for (int j = indexToRemove; j < this.getNumVertices(); j++) {
                    this.adjMatrix[i][j] = this.adjMatrix[i][j + 1];
                }
            }

        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        boolean[] visited = new boolean[this.getNumVertices()];
        for (int i = 0; i < this.getNumVertices(); i++) {
            visited[i] = false;
        }
        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty()) {
            try {
                x = traversalQueue.dequeue();
                resultList.addToRear(this.getVertices()[x.intValue()]);
                for (int i = 0; i < this.getNumVertices(); i++) {
                    if ((adjMatrix[x.intValue()][i] && !visited[i])) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a depth-first search traversal starting
     * at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth-first traversal
     */
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[this.getNumVertices()];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < this.getNumVertices(); i++) {
            visited[i] = false;
        }
        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(this.getVertices()[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;
            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < this.getNumVertices()) && !found; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(this.getVertices()[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(int index1, int index2) {
        return null;
    }

}
