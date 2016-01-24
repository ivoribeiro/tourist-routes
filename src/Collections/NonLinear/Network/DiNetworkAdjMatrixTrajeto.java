package Collections.NonLinear.Network;

import Collections.Exception.ElementNotFoundException;
import Collections.Exception.EmptyCollectionException;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.LinkedList;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import Collections.Linear.Stack.LinkedStack;
import Collections.NonLinear.Graph.matrix.adjMatrixDiGraph;
import TouristRoutes.Criterios;
import TouristRoutes.Option;

import TouristRoutes.Trajeto;
import TouristRoutes.Viagem;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by aluno on 1/19/16.
 *
 * @param <T>
 */
public class DiNetworkAdjMatrixTrajeto<T> extends adjMatrixDiGraph<T> {

    private LinkedUnorderedList<Trajeto>[][] weightAdjMatrix;

    public DiNetworkAdjMatrixTrajeto() {
        super();
        this.weightAdjMatrix = new LinkedUnorderedList[this.getCapacity()][this.getCapacity()];

    }

    public DiNetworkAdjMatrixTrajeto(int capacity) {
        super(capacity);
        this.weightAdjMatrix = new LinkedUnorderedList[this.getCapacity()][this.getCapacity()];

    }

    /**
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     */
    @Override
    protected void expandCapacity() {
        super.expandCapacity();
        LinkedUnorderedList<Trajeto>[][] largerWeightsMatrix = (new LinkedUnorderedList[this.getNumVertices() * 2][this.getNumVertices() * 2]);
        for (int i = 0; i < this.getNumVertices(); i++) {
            System.arraycopy(this.weightAdjMatrix[i], 0, largerWeightsMatrix[i], 0, getNumVertices());
        }
        this.weightAdjMatrix = largerWeightsMatrix;
    }

    /**
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     *
     * @param vertex the vertex to add to the graph
     */
    @Override
    public void addVertex(T vertex) {
        try {
            int vertexContains = super.getIndex(vertex);
        }//se não existir adiciona o vertice
        catch (ElementNotFoundException e) {
            super.addVertex(vertex);
            //Se estiver cheio , expande matriz de pesos
            if (this.getNumVertices() == this.getVertices().length) {
                this.expandCapacity();
            }
            for (int i = 0; i < this.getNumVertices(); i++) {
                this.weightAdjMatrix[this.getNumVertices() - 1][i] = new LinkedUnorderedList<>();
                this.weightAdjMatrix[i][this.getNumVertices() - 1] = new LinkedUnorderedList<>();
            }
        }
    }

    /**
     * Removes a vertex from the network
     *
     * @param vertex, the vertex to be removed
     */
    @Override
    public void removeVertex(T vertex) {
        try {
            this.removeVertex(this.getIndex(vertex));
            super.removeVertex(vertex);
        } catch (ElementNotFoundException e) {
        }

    }

    /**
     * Removes a vertex from the network
     *
     * @param index, index of the vertex to be removed
     */
    private void removeVertex(int index) {

        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int j = index; j < this.getNumVertices(); j++) {
                this.weightAdjMatrix[i][j] = this.weightAdjMatrix[i][j + 1];
            }
        }

        for (int i = index; i < this.getNumVertices(); i++) {
            System.arraycopy(this.weightAdjMatrix[i + 1], 0, this.weightAdjMatrix[i], 0, this.getNumVertices());
        }

    }

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight Extamente igual a implentação de uma network nao
     * direcionada
     */
    public void addEdge(T vertex1, T vertex2, Trajeto weight) {
        //so adiciona a edge caso ela nao exista
        if (!super.edgeExists(vertex1, vertex2)) {
            super.addEdge(vertex1, vertex2);
        }
        try {
            this.weightAdjMatrix[this.getIndex(vertex1)][this.getIndex(vertex2)].addToFront(weight);
        } catch (ElementNotFoundException e) {
        }
    }

    /**
     * Removes a directional edge between two vertices
     *
     * @param vertex1, the first vertex of the edge
     * @param vertex2, the second vertex of the edge
     * @param target
     */
    public void removeEdge(T vertex1, T vertex2, Trajeto target) {

        try {
            try {
                this.weightAdjMatrix[this.getIndex(vertex1)][this.getIndex(vertex2)].remove(target);
            } catch (EmptyCollectionException ex) {
                Logger.getLogger(DiNetworkAdjMatrixTrajeto.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (this.weightAdjMatrix[this.getIndex(vertex1)][this.getIndex(vertex2)].isEmpty()) {
                super.removeEdge(vertex1, vertex2);
            }
        } catch (ElementNotFoundException e) {
        }

    }

    /**
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * @param vertex1, the start vertex
     * @param vertex2, the target vertex
     * @param opt
     * @param criterios
     * @return the weight of the least weight path in the network or positive
     * infinity if no path is found.
     */
    public UnorderedListADT<Trajeto> shortestPathWeight(T vertex1, T vertex2, Criterios criterios) {

        try {
            try {
                return shortestPathWeight(getIndex(vertex1),
                        getIndex(vertex2), criterios);
            } catch (ElementNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (EmptyCollectionException ex) {
            throw new NullPointerException("Collection is empty.");
        }
        return null;

    }

    /**
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * @param startIndex, the index of the start vertex
     * @param targetIndex, the index of the target vertex
     * @return the weight of the least weight path in the network or positive
     * infinity if no path is found.
     * @throws EmptyCollectionException, if collection is empty
     */
    private UnorderedListADT<Trajeto> shortestPathWeight(int startIndex, int targetIndex, Criterios criterios) throws EmptyCollectionException {
        Option option = new Option(weightAdjMatrix);
        UnorderedListADT<Trajeto> result = new LinkedUnorderedList<>();
        //verifica se os indexes existem
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return null;
        }

        Integer index1 = null, index2 = null;
        //obtem o caminho onde passa em menos vertices
        Iterator<Integer> it = (Iterator<Integer>) super.iteratorShortestPath(startIndex,
                targetIndex);
        if (it.hasNext()) {
            index1 = (it.next());
        } else {
            return null;
        }

        while (it.hasNext()) {
            index2 = (it.next());
            result.addToRear(option.weightValue(index1, index2, criterios));
            index1 = index2;

        }

        return result;
    }

    /**
     * Retorna uma lista de todos as viagens possiveis entre dois vertices
     *
     * @param startVertex
     * @param endVertex
     * @return
     */
    public UnorderedListADT<LinkedUnorderedList<Trajeto>> wightedBreadthFirstTravesal(T startVertex, T endVertex) {
        UnorderedListADT<LinkedUnorderedList<T>> paths = null;
        paths = (LinkedUnorderedList<LinkedUnorderedList<T>>) this.breadthFirstTravesal(startVertex, endVertex);
        UnorderedListADT<LinkedUnorderedList<Trajeto>> result = new LinkedUnorderedList<>();
        LinkedUnorderedList<Trajeto> path = new LinkedUnorderedList<>();
        LinkedStack<LinkedUnorderedList<Trajeto>> stack = new LinkedStack<>();
        Integer index1 = null, index2 = null;
        System.out.println(paths);
        System.out.println("Viagens:");
        //percorre os paths
        Iterator<LinkedUnorderedList<T>> it = (Iterator<LinkedUnorderedList<T>>) paths.iterator();
        while (it.hasNext()) {
            LinkedUnorderedList<T> vertexList = it.next();
            //verificar numero de arestas 
            int edgeNum = (vertexList.size()) - 1;
            Iterator<T> vertexIt = vertexList.iterator();

            while (vertexIt.hasNext()) {
                if (vertexIt.hasNext()) {
                    try {
                        index1 = getIndex(vertexIt.next());
                    } catch (ElementNotFoundException ex) {
                        Logger.getLogger(DiNetworkAdjMatrixTrajeto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                while (vertexIt.hasNext()) {
                    try {
                        index2 = getIndex(vertexIt.next());
                        LinkedUnorderedList<Trajeto> trajetosAresta = weightAdjMatrix[index1][index2];
                        Iterator<Trajeto> IteradorTrajetosAresta = trajetosAresta.iterator();

                        while (IteradorTrajetosAresta.hasNext()) {
                            Trajeto trajeto = IteradorTrajetosAresta.next();

                            if (edgeNum == 1) {

                                path = new LinkedUnorderedList<>();
                                path.addToRear(trajeto);
                                result.addToRear(path);
                            } else if (!trajeto.getCidadeDestino().equals(endVertex)) {
                                path = new LinkedUnorderedList<>();
                                path.addToRear(trajeto);
                                // se a stack estiver vazia adiciona path novo para cada trajeto
                                if (stack.isEmpty()) {
                                    for (Trajeto trajetoAresta : trajetosAresta) {
                                        path = new LinkedUnorderedList<>();
                                        path.addToRear(trajetoAresta);
                                        stack.push(path);
                                    }
                                } else {
                                    LinkedStack<LinkedUnorderedList<Trajeto>> tempStack = new LinkedStack<>();
                                    //itera o topo da stack , adiciona o novo trajeto e push para a nova stack
                                    for (int j = 0; j < stack.size(); j++) {
                                        LinkedUnorderedList<Trajeto> tempPath = stack.pop();
                                        try {
                                            //se a cidade destino do ultimo trajeto do elemnto da stack nao for igual ao do trajeto actual , adiciona ao path
                                            if (!trajeto.getCidadeDestino().equals(tempPath.last().getCidadeDestino())) {
                                                tempPath.addToRear(trajeto);
                                            }
                                            tempStack.push(tempPath);
                                        } catch (EmptyCollectionException ex) {
                                            Logger.getLogger(DiNetworkAdjMatrixTrajeto.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    stack = tempStack;

                                }
                            } else {

                                //Adicionar trajeto ao fim de cada path
                                //System.out.println(trajeto);
                                LinkedStack<LinkedUnorderedList<Trajeto>> tempStack = new LinkedStack<>();
                                for (int j = 0; j < stack.size(); j++) {
                                    LinkedUnorderedList<Trajeto> tempPath = stack.pop();
                                    LinkedUnorderedList<Trajeto> trajetosAresta2 = weightAdjMatrix[index1][index2];
                                    Iterator<Trajeto> IteradorTrajetosAresta2 = trajetosAresta.iterator();
                                    while (IteradorTrajetosAresta2.hasNext()) {
                                        Trajeto trajetoAresta = IteradorTrajetosAresta2.next();
                                        LinkedUnorderedList<Trajeto> newPath = new LinkedUnorderedList<>();
                                        newPath = tempPath;
                                        if (trajetoAresta.getCidadeDestino().equals(tempPath.last().getCidadeDestino())) {
                                            newPath.removeLast();
                                        }
                                        newPath.addToRear(trajetoAresta);
                                        tempStack.push(newPath);
                                    }

                                }
                                stack = tempStack;

                            }
                        }
                        //result.addToRear(path);

                    } catch (EmptyCollectionException | ElementNotFoundException ex) {

                        Logger.getLogger(DiNetworkAdjMatrixTrajeto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //result.addToRear(option.weightValue(index1, index2, criterios));
                    index1 = index2;

                }

             }
        }
        System.out.println(stack.toString());
        return null;
    }

    /**
     * Retorna lista de viagens segundo criterios
     *
     * @param vertex1
     * @param vertex2
     * @param criterios
     * @return
     */
    public UnorderedListADT<LinkedUnorderedList<Trajeto>> criterialPath(T vertex1, T vertex2, Criterios criterios) {
        UnorderedListADT<LinkedUnorderedList<Trajeto>> tempList = null;
        Criterios tempCriteiros = new Criterios();
        //shortest paths weights , retorna o melhor path possivel
        if (criterios.isViagemMaisBarata() || criterios.isViagemMenorDistancia() || criterios.isViagemMenorTempoViagem()) {
            if (criterios.isViagemMaisBarata()) {
                tempCriteiros.setViagemMaisBarata(true);
                tempList.addToRear((LinkedUnorderedList<Trajeto>) shortestPathWeight(vertex1, vertex2, tempCriteiros));
            }
            if (criterios.isViagemMenorDistancia()) {
                tempCriteiros.setViagemMaisBarata(false);
                tempCriteiros.setViagemMenorDistancia(true);
                tempList.addToRear((LinkedUnorderedList<Trajeto>) shortestPathWeight(vertex1, vertex2, tempCriteiros));
            }
            if (criterios.isViagemMenorTempoViagem()) {
                tempCriteiros.setViagemMaisBarata(false);
                tempCriteiros.setViagemMenorDistancia(false);
                tempCriteiros.setVigemMenorTempoViagem(true);
                tempList.addToRear((LinkedUnorderedList<Trajeto>) shortestPathWeight(vertex1, vertex2, tempCriteiros));
            }
            return tempList;
        } //requisitos avançados (formulário pesquisa)
        else {
            UnorderedListADT<LinkedUnorderedList<Trajeto>> todasViagens = this.wightedBreadthFirstTravesal(vertex1, vertex2);
            tempList = todasViagens;
            if (criterios.getDuracaoMaxima() != null) {
                tempList = Viagem.getViagensByDistanciaMax(tempList, criterios.getDuracaoMaxima());
            }
            if (criterios.getPrecoMaximoTroco() != null) {
                // tempList = Viagem.getViagensByPrecoTrajetoMax(tempList, criterios.get);
            }
            if (criterios.getPrecoMaximoTroco() != null) {
                tempList = Viagem.getViagensByPrecoTrajetoMax(tempList, criterios.getPrecoMaximoTroco());
            }
            if (criterios.getPrecoTotalMaximo() != null) {

                tempList = Viagem.getViagensByPrecoMax(tempList, criterios.getPrecoTotalMaximo());
            }
            if (criterios.getTempoEsperaMaximoParagem() != null) {
                //tempList = Viagem.(tempList, criterios.getTempoEsperaMaximoParagem());
            }
            if (criterios.getTempoEsperaTotalMaximo() != null) {
                //
            }
            if (criterios.isComparacaoViagemMaisBarata() || criterios.isComparacaoViagemMenorDistancia() || criterios.isComparacaoVigemMenorTempoViagem()) {
                UnorderedListADT<LinkedUnorderedList<Trajeto>> newTempList = new LinkedUnorderedList<>();
                if (criterios.isComparacaoViagemMaisBarata()) {

                    newTempList.addToRear(Viagem.getViagemMaisbarata(tempList));

                }
                if (criterios.isComparacaoViagemMenorDistancia()) {
                    newTempList.addToRear(Viagem.getViagemMenosComprida(tempList));

                }
                if (criterios.isComparacaoVigemMenorTempoViagem()) {
                    newTempList.addToRear(Viagem.getViagemMaisbarata(tempList));

                }
                return newTempList;
            }

        }
        return tempList;
    }

    @Override
    public String toString() {
        String result = "";

        /**
         * Print the adjacency Matrix
         */
        result += "Adjacency Matrix"
                + "\n----------------------------------\n"
                + "index\t";

        for (int i = 0; i < this.getNumVertices(); i++) {
            result += i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < this.getNumVertices(); i++) {
            result += i + "\t";

            for (int j = 0; j < this.getNumVertices(); j++) {
                if (!this.weightAdjMatrix[i][j].isEmpty()) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        /**
         * Print the vertex values
         */
        result += "\n\nVertex Values"
                + "\n----------------------------------\n"
                + "index\tvalue\n\n";

        for (int i = 0; i < this.getNumVertices(); i++) {
            result += " " + i + "\t" + this.getVertices()[i].toString() + "\n";
        }

        /**
         * Print the weights of the edges
         */
        result += "\n\nWeights of Edges";

        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int j = 0; j < this.getNumVertices() - 1; j++) {
                if (!this.weightAdjMatrix[i][j].isEmpty()) {
                    result += "\n----------------------------------\n"
                            + this.getVertices()[i].toString() + " para "
                            + this.getVertices()[j].toString() + ": "
                            + this.weightAdjMatrix[i][j].toString() + "\n";

                }
            }
        }

        return result;
    }

    @Override
    public T[] getVertices() {
        return super.getVertices(); //To change body of generated methods, choose Tools | Templates.
    }

    public int verificarCidade(T vertex) throws ElementNotFoundException {
        return super.getIndex(vertex);
    }

}
