/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TouristRoutes;

import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

/**
 *
 * @author ivo
 */
public class Option {

    //Weights matrix
    private final LinkedUnorderedList<Trajeto>[][] weightAdjMatrix;

    /**
     * Create a new option
     *
     * @param weights, the weights matrix
     */
    public Option(LinkedUnorderedList<Trajeto>[][] weights) {
        this.weightAdjMatrix = weights;
    }

    /**
     * Returns the disered method
     *
     * @param index1, index of the start vertex
     * @param criterios criteria for the choice of method
     * @param index2, index of the target vertex
     * @return
     */
    public Trajeto weightValue(int index1, int index2, Criterios criterios) {

        if (criterios.isViagemMenorDistancia()) {
            return menorDistancia(index1, index2);
        }
        if (criterios.isViagemMaisBarata()) {
            return maisBarato(index1, index2);
        }
        if (criterios.isViagemMenorTempoViagem()) {
            return menorTempoViagem(index1, index2);
        } else {
            return null;
        }
    }
    
  
    /**
     * return the shortest path
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the route of the path
     */
    private Trajeto menorDistancia(int index1, int index2) {
        return Trajeto.trajetoMenorDistancia(weightAdjMatrix[index1][index2]);
    }

    /**
     * return the cheapest path
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the route of the path
     */
    private Trajeto maisBarato(int index1, int index2) {
        return Trajeto.trajetoMaisBarato(weightAdjMatrix[index1][index2]);
    }

    /**
     * Return the route with the shortest travel time
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the route of the path
     */
    private Trajeto menorTempoViagem(int index1, int index2) {

        return Trajeto.trajetoMenosTempoViagem(weightAdjMatrix[index1][index2]);
    }

}
