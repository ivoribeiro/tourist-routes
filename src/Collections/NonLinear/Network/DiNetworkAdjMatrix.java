package Collections.NonLinear.Network;

import Collections.Exception.ElementNotFoundException;
import Collections.NonLinear.Graph.matrix.adjMatrixDiGraph;
import Collections.NonLinear.Interfaces.NetworkADT; 

/**
 * Created by ivo on 13-01-2016.
 */
public class DiNetworkAdjMatrix<T> extends adjMatrixDiGraph<T> implements NetworkADT<T> {

    private Transporte<T> [][] weights;

    public DiNetworkAdjMatrix() {
        super();
        weights = new Transporte[this.getNumVertices()][this.getNumVertices()];
    }

    public DiNetworkAdjMatrix(int capacity) {
        super(capacity);
        weights = new Transporte[this.getNumVertices()][this.getNumVertices()];
    }

    /**
     * Creates new arrays to store the contents of the graph with
     * twice the capacity.
     */
    @Override
    protected void expandCapacity() {
        super.expandCapacity();
        double[][] largerWeightsMatrix = (new double[this.getNumVertices() * 2][this.getNumVertices() * 2]);
        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int j = 0; j < getNumVertices(); j++) {
                largerWeightsMatrix[i][j] = this.weights[i][j];
            }
        }
        this.weights = largerWeightsMatrix;
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph
     * if necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
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
            }
        }

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
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        super.removeEdge(vertex1, vertex2);
        try {
            this.weights[this.getIndex(vertex1)][this.getIndex(vertex1)] = -1;
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
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
