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
     * @param criterios
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
     * Retorna trajeto mais curto
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the duration of the path
     */
    private Trajeto menorDistancia(int index1, int index2) {
        return Trajeto.trajetoMenorDistancia(weightAdjMatrix[index1][index2]);
    }

    /**
     * Retorna trajeto mais barato
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the duration of the path
     */
    private Trajeto maisBarato(int index1, int index2) {
        return Trajeto.trajetoMaisBarato(weightAdjMatrix[index1][index2]);
    }

    /**
     * Retorna o trajeto mais curta (tempo viagem)
     *
     * @param index1, index of the start vertex
     * @param index2, index of the target vertex
     * @return the duration of the path
     */
    private Trajeto menorTempoViagem(int index1, int index2) {

        return Trajeto.trajetoMenosTempoViagem(weightAdjMatrix[index1][index2]);
    }

}
