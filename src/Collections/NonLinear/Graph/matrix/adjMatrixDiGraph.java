package Collections.NonLinear.Graph.matrix;

import Collections.Exception.EdgeNotFoundException;
import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Exception.EmptyQueueException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.LinkedList;
import Collections.Linear.List.UnorderedList.ArrayUnorderedList;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import Collections.Linear.Queue.LinkedQueue;
import Collections.Linear.Stack.LinkedStack;
import Collections.NonLinear.Graph.Graph;
import Collections.NonLinear.Interfaces.GraphADT;
import Collections.NonLinear.Tree.LinkedHeap;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        }
        return false;
    }

    /**
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     */
    @Override
    protected void expandCapacity() {
        super.expandCapacity();
        boolean[][] largerAdjMatrix = (new boolean[this.getNumVertices() * 2][this.getNumVertices() * 2]);
        for (int i = 0; i < this.getNumVertices(); i++) {
            System.arraycopy(adjMatrix[i], 0, largerAdjMatrix[i], 0, getNumVertices());
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
        } catch (ElementNotFoundException | EdgeNotFoundException e) {
        }

    }

    /**
     * Removes a unidirectional edge between two vertices
     *
     * @param index1, the index of first vertex of the edge
     * @param index2, the index of second vertex of the edge
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
        }
    }

    @Override
    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        LinkedUnorderedList<T> resultList = new LinkedUnorderedList<>();
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
                resultList.addToRear(this.getVertices()[x]);
                for (int i = 0; i < this.getNumVertices(); i++) {
                    if ((adjMatrix[x][i] && !visited[i])) {
                        traversalQueue.enqueue(new Integer(i));
                        visited[i] = true;
                    }
                }
            } catch (EmptyQueueException e) {
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
    protected Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
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
                if (adjMatrix[x][i] && !visited[i]) {
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
    protected Iterator<T> iteratorShortestPath(int index1, int index2) throws EmptyCollectionException {
        int index = index1;
        int[] pathLength = new int[this.getNumVertices()];
        int[] predecessor = new int[this.getNumVertices()];
        boolean[] visited = new boolean[this.getNumVertices()];

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedListADT<Integer> resultList = new LinkedUnorderedList<>();

        if (!indexIsValid(index1) || !indexIsValid(index2)
                || (index1 == index2)) {
            return (Iterator<T>) resultList.iterator();
        }

        for (int i = 0; i < this.getNumVertices(); i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(index1);
        visited[index1] = true;
        pathLength[index1] = 0;
        predecessor[index1] = -1;

        while (!traversalQueue.isEmpty() && (index != index2)) {
            try {
                index = (traversalQueue.dequeue());

                /**
                 * Update the pathLength for each unvisited vertex adjacent to
                 * the vertex at the current index.
                 */
                for (int i = 0; i < this.getNumVertices(); i++) {
                    if (adjMatrix[index][i] && !visited[i]) {
                        pathLength[i] = pathLength[index] + 1;
                        predecessor[i] = index;
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            } catch (EmptyQueueException ex) {
                Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (index != index2) // no path must have been found
        {
            return (Iterator<T>) resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = index2;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != index1);

        while (!stack.isEmpty()) {
            resultList.addToRear(stack.pop());
        }

        return (Iterator<T>) resultList.iterator();
    }

    public UnorderedListADT<T> adjacentNodes(T vertex) throws ElementNotFoundException {
        UnorderedListADT<T> adjacentNodesIndex = new LinkedUnorderedList<>();
        int vertexIndex = this.getIndex(vertex);
        int index = 0;
        for (boolean connect : this.adjMatrix[vertexIndex]) {

            if (connect) {
                adjacentNodesIndex.addToRear(this.getVertice(index));
            }

            index++;
        }
        if (adjacentNodesIndex.isEmpty()) {
            return null;
        }
        return adjacentNodesIndex;
    }

    /**
     *
     * @param startVertex
     * @param endVertex
     * @param visited
     * @return 
     */
    protected void breadthFirstTravesal(LinkedUnorderedList<T> visited, T startVertex, T endVertex) {

        //lista de nos
        LinkedUnorderedList<T> nodes = null;
        UnorderedListADT< UnorderedListADT<T>> paths = new LinkedUnorderedList<>();

        try {
            try {
                //lista de nos adjacentes á raiz actual
                nodes = (LinkedUnorderedList<T>) this.adjacentNodes(visited.last());
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

        // examine adjacent nodes
        for (T node : nodes) {
            try {
                //se o no adjacente ja foi visitado continua
                if (visited.contains(node)) {
                    continue;
                }
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (node.equals(endVertex)) {
                visited.addToRear(node);
                paths.addToRear(visited);

                try {
                    visited.removeLast();
                } catch (EmptyCollectionException ex) {
                    Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        // in breadth-first, recursion needs to come after visiting adjacent nodes
        for (T node : nodes) {
            try {
                if (visited.contains(node) || node.equals(endVertex)) {
                    continue;
                }
                visited.addToRear(node);
                breadthFirstTravesal(visited, startVertex, endVertex);
                visited.removeLast();
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(adjMatrixDiGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(paths.toString());
    }

}
