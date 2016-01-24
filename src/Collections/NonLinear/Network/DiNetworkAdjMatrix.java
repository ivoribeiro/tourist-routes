package Collections.NonLinear.Network;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.List.UnorderedList.ArrayUnorderedList;
import Collections.Linear.Stack.LinkedStack;
import Collections.NonLinear.Graph.matrix.adjMatrixDiGraph;
import Collections.NonLinear.Interfaces.NetworkADT;
import Collections.NonLinear.Tree.LinkedHeap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ivo on 13-01-2016.
 *
 * @param <T>
 */
public class DiNetworkAdjMatrix<T> extends adjMatrixDiGraph<T> implements NetworkADT<T> {

    private double[][] weights;

    public DiNetworkAdjMatrix() {
        super();
        weights = new double[this.getNumVertices()][this.getNumVertices()];
    }

    public DiNetworkAdjMatrix(int capacity) {
        super(capacity);
        weights = new double[this.getNumVertices()][this.getNumVertices()];
    }

    /**
     * Creates new arrays to store the contents of the Network with twice the
     * capacity.
     */
    @Override
    protected void expandCapacity() {
        super.expandCapacity();
        double[][] largerWeightsMatrix = (new double[this.getNumVertices() * 2][this.getNumVertices() * 2]);
        for (int i = 0; i < this.getNumVertices(); i++) {
            System.arraycopy(this.weights[i], 0, largerWeightsMatrix[i], 0, getNumVertices());
        }
        this.weights = largerWeightsMatrix;
    }

    /**
     * Adds a vertex to the Network, expanding the capacity of the Network if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the Network
     */
    @Override
    public void addVertex(T vertex) {

        super.addVertex(vertex);

        this.getVertices()[this.getNumVertices()] = vertex;
        for (int i = 0; i <= this.getNumVertices(); i++) {
            weights[this.getNumVertices()][i] = -1;
            weights[i][this.getNumVertices()] = -1;
        }
        this.incrementNumVertices();
    }

    /**
     * Removes a vertex from the network
     *
     * @param vertex, the vertex to be removed
     */
    @Override
    public void removeVertex(T vertex) {
        super.removeVertex(vertex);
        try {
            this.removeVertex(this.getIndex(vertex));
        } catch (ElementNotFoundException e) {
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
            }
        }

    }

    /**
     * Inserts an edge between two vertices of this Network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight Extamente igual a implentação de uma network nao
     * direcionada
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        super.addEdge(vertex1, vertex2);
        try {
            this.weights[getIndex(vertex1)][getIndex(vertex2)] = weight;
        } catch (ElementNotFoundException e) {
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        super.removeEdge(vertex1, vertex2);
        try {
            this.weights[this.getIndex(vertex1)][this.getIndex(vertex2)] = -1;
        } catch (ElementNotFoundException e) {
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
        try {
            return shortestPathWeight(this.getIndex(vertex1), this.getIndex(vertex2));
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            Logger.getLogger(DiNetworkAdjMatrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param startIndex the index of the first vertex
     * @param targetIndex the index of the second vertex
     * @return the weight of the shortest path in this network
     */
    private double shortestPathWeight(int startIndex, int targetIndex) throws EmptyCollectionException {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = this.iteratorShortestPath(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = (it.next());
        } else {
            return Double.POSITIVE_INFINITY;
        }
        while (it.hasNext()) {
            index2 = (it.next());
            result += weights[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * Returns the index of the the vertex that that is adjacent to the vertex
     * with the given index and also has a pathWeight equal to weight.
     *
     * @param visited the boolean array with vertex visited
     * @param pathWeight the double array with path Weight
     * @param weight the Weight wanted
     * @return
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < this.getNumVertices(); i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < this.getNumVertices(); j++) {
                    if ((weights[i][j] < Double.POSITIVE_INFINITY)
                            && visited[j]) {
                        return i;
                    }
                }
            }
        }
        return -1;  // should never get to here
    }

    /**
     *
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.
     *
     * @param startIndex the index of the start vertex
     * @param targetIndex the index of the target vertex
     * @return the iterator with shortest path
     * @throws EmptyCollectionException
     */
    @Override
    protected Iterator iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException {
        int index;
        double weight;
        int[] predecessor = new int[this.getNumVertices()];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        double[] pathWeight = new double[this.getNumVertices()];
        for (int i = 0; i < this.getNumVertices(); i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[this.getNumVertices()];
        for (int i = 0; i < this.getNumVertices(); i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < this.getNumVertices(); i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + this.weights[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = (traversalMinHeap.removeMin());
            for (int j = 0; j < traversalMinHeap.size(); j++) {
                traversalMinHeap.removeMin();
            }

            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            /**
             * Update the pathWeight for each vertex that has has not been
             * visited and is adjacent to the last vertex that was visited.
             * Also, add each unvisited vertex to the heap.
             */
            for (int i = 0; i < this.getNumVertices(); i++) {
                if (!visited[i]) {
                    if ((this.weights[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + this.weights[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + this.weights[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * Returns a minimum spanning tree of the network.
     *
     * @return a minimum spanning tree of the network
     */
    /*
     public NetworkADT mstNetwork() {
     int x, y;
     int index;
     double weight;
     int[] edge = new int[2];
     Heap<Double> minHeap = new Heap<Double>();
     DiNetworkAdjMatrix<T> resultGraph = new DiNetworkAdjMatrix<T>(this.getNumVertices());
     if (isEmpty() || !isConnected())
     return resultGraph;
     resultGraph.weights = new double[this.getNumVertices()][this.getNumVertices()];
     for (int i = 0; i < this.getNumVertices(); i++)
     for (int j = 0; j < this.getNumVertices(); j++)
     resultGraph.weights[i][j] = Double.POSITIVE_INFINITY;
     boolean[] visited = new boolean[this.getNumVertices()];
     for (int i = 0; i < this.getNumVertices(); i++)
     visited[i] = false;

     edge[0] = 0;
     resultGraph.addVertex(this.getVertices()[0]);
     visited[0] = true;
     /** Add all edges, which are adjacent to the starting vertex,
     to the heap */
    /*
     for(
     int i = 0;
     i<this.

     getNumVertices();

     i++)
     minHeap.addElement(new

     Double(this.weights[0][i])

     );
     while((resultGraph.size()<this.

     size()

     )&&!minHeap.isEmpty())

     {
     /** Get the edge with the smallest weight that has exactly
     one vertex already in the resultGraph */
    /*
     do {
     weight = (minHeap.removeMin()).doubleValue();
     edge = getEdgeWithWeightOf(weight, visited);
     } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

     }

     x=edge[0];
     y=edge[1];
     if(!visited[x])
     index=x;
     else
     index=y;
     /** Add the new edge and vertex to the resultGraph */
    /*
     resultGraph.getVertices()[index]=this.

     getVertices()[index

     ];
     visited[index]=true;
     resultGraph.incrementNumVertices();
     resultGraph.weights[x][y]=this.weights[x][y];
     resultGraph.weights[y][x]=this.weights[y][x];

     /** Add all edges, that are adjacent to the newly added vertex,
     to the heap */
    /*
     for(
     int i = 0;
     i<this.

     getNumVertices();

     i++)

     {
     if (!visited[i] && (this.weights[i][index] < Double.POSITIVE_INFINITY)) {
     edge[0] = index;
     edge[1] = I;
     minHeap.addElement(new Double(this.weights[index][i]));
     }
     }


     return resultGraph;
     }
     */
}
