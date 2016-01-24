/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TouristRoutes;

/**
 *
 * @author ivo
 */
public class Criterios {
//criterios melhor caminho

    private boolean viagemMenorDistancia;
    private boolean viagemMenorTempoViagem;
    private boolean viagemMaisBarata;

    //criterios por comparacao
    private boolean comparacaoViagemMenorDistancia;
    private boolean comparacaoVigemMenorTempoViagem;
    private boolean comparacaoViagemMaisBarata;

    private Integer duracaoMaxima;
    private Integer tempoEsperaTotalMaximo;
    private Integer tempoEsperaMaximoParagem;
    private Double precoTotalMaximo;
    private Double precoMaximoTroco;

    public Criterios() {
        this.viagemMaisBarata = false;
        this.viagemMenorDistancia = false;
        this.viagemMenorTempoViagem = false;
        this.comparacaoViagemMaisBarata = false;
        this.comparacaoViagemMenorDistancia = false;
        this.comparacaoVigemMenorTempoViagem = false;
        this.duracaoMaxima = null;
        this.tempoEsperaTotalMaximo = null;
        this.tempoEsperaMaximoParagem = null;
        this.precoTotalMaximo = null;
        this.precoMaximoTroco = null;
    }

    public Criterios(Integer duracaoMaxima, Integer tempoEsperaTotalMaximo, Integer tempoEsperaMaximoParagem, Double precoTotalMaximo, Double precoMaximoTroco) {
        this.viagemMaisBarata = false;
        this.viagemMenorDistancia = false;
        this.viagemMenorTempoViagem = false;
        this.comparacaoViagemMaisBarata = false;
        this.comparacaoViagemMenorDistancia = false;
        this.comparacaoVigemMenorTempoViagem = false;
        this.duracaoMaxima = null;
        this.tempoEsperaTotalMaximo = null;
        this.tempoEsperaMaximoParagem = null;
        this.precoTotalMaximo = null;
        this.precoMaximoTroco = null;
        this.duracaoMaxima = duracaoMaxima;
        this.tempoEsperaTotalMaximo = tempoEsperaTotalMaximo;
        this.tempoEsperaMaximoParagem = tempoEsperaMaximoParagem;
        this.precoTotalMaximo = precoTotalMaximo;
        this.precoMaximoTroco = precoMaximoTroco;
    }

    public boolean isViagemMenorDistancia() {
        return viagemMenorDistancia;
    }

    public boolean isViagemMenorTempoViagem() {
        return viagemMenorTempoViagem;
    }

    public boolean isViagemMaisBarata() {
        return viagemMaisBarata;
    }

    public void setViagemMenorDistancia(boolean viagemMenorDistancia) {
        this.viagemMenorDistancia = viagemMenorDistancia;
    }

    public void setVigemMenorTempoViagem(boolean vigemMenorTempoViagem) {
        this.viagemMenorTempoViagem = vigemMenorTempoViagem;
    }

    public void setViagemMaisBarata(boolean viagemMaisBarata) {
        this.viagemMaisBarata = viagemMaisBarata;
    }

    public Integer getDuracaoMaxima() {
        return duracaoMaxima;
    }

    public Integer getTempoEsperaTotalMaximo() {
        return tempoEsperaTotalMaximo;
    }

    public Integer getTempoEsperaMaximoParagem() {
        return tempoEsperaMaximoParagem;
    }

    public Double getPrecoTotalMaximo() {
        return precoTotalMaximo;
    }

    public Double getPrecoMaximoTroco() {
        return precoMaximoTroco;
    }

    public void setComparacaoViagemMenorDistancia(boolean comparacaoViagemMenorDistancia) {
        this.comparacaoViagemMenorDistancia = comparacaoViagemMenorDistancia;
    }

    public void setComparacaoVigemMenorTempoViagem(boolean comparacaoVigemMenorTempoViagem) {
        this.comparacaoVigemMenorTempoViagem = comparacaoVigemMenorTempoViagem;
    }

    public void setComparacaoViagemMaisBarata(boolean comparacaoViagemMaisBarata) {
        this.comparacaoViagemMaisBarata = comparacaoViagemMaisBarata;
    }
    

    //por comparação
    public boolean isComparacaoViagemMenorDistancia() {
        return comparacaoViagemMenorDistancia;
    }

    public boolean isComparacaoVigemMenorTempoViagem() {
        return comparacaoVigemMenorTempoViagem;
    }

    public boolean isComparacaoViagemMaisBarata() {
        return comparacaoViagemMaisBarata;
    }

}
