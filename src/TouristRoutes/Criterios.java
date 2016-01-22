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

    private boolean viagemMenorDistancia;
    private boolean vigemMenorTempoViagem;
    private boolean viagemMaisBarata;

    private int duracaoMaxima;
    private int tempoEsperaTotalMaximo;
    private int tempoEsperaMaximoParagem;
    private int precoTotalMaximo;
    private int precoMaximoTroco;

    public Criterios() {
        this.viagemMaisBarata = false;
        this.viagemMenorDistancia = false;
        this.viagemMenorDistancia = false;
    }
    
    

    public Criterios(boolean viagemMenorDistancia, boolean vigemMenorTempoViagem, boolean viagemMaisBarata, int duracaoMaxima, int tempoEsperaTotalMaximo, int tempoEsperaMaximoParagem, int precoTotalMaximo, int precoMaximoTroco) {
        this.viagemMenorDistancia = viagemMenorDistancia;
        this.vigemMenorTempoViagem = vigemMenorTempoViagem;
        this.viagemMaisBarata = viagemMaisBarata;
        this.duracaoMaxima = duracaoMaxima;
        this.tempoEsperaTotalMaximo = tempoEsperaTotalMaximo;
        this.tempoEsperaMaximoParagem = tempoEsperaMaximoParagem;
        this.precoTotalMaximo = precoTotalMaximo;
        this.precoMaximoTroco = precoMaximoTroco;
    }

    public Criterios(int duracaoMaxima, int tempoEsperaTotalMaximo, int tempoEsperaMaximoParagem, int precoTotalMaximo, int precoMaximoTroco) {
        this.viagemMaisBarata = false;
        this.viagemMenorDistancia = false;
        this.viagemMenorDistancia = false;
        this.duracaoMaxima = duracaoMaxima;
        this.tempoEsperaTotalMaximo = tempoEsperaTotalMaximo;
        this.tempoEsperaMaximoParagem = tempoEsperaMaximoParagem;
        this.precoTotalMaximo = precoTotalMaximo;
        this.precoMaximoTroco = precoMaximoTroco;
    }

    public boolean isViagemMenorDistancia() {
        return viagemMenorDistancia;
    }

    public boolean isVigemMenorTempoViagem() {
        return vigemMenorTempoViagem;
    }

    public boolean isViagemMaisBarata() {
        return viagemMaisBarata;
    }

    public void setViagemMenorDistancia(boolean viagemMenorDistancia) {
        this.viagemMenorDistancia = viagemMenorDistancia;
    }

    public void setVigemMenorTempoViagem(boolean vigemMenorTempoViagem) {
        this.vigemMenorTempoViagem = vigemMenorTempoViagem;
    }

    public void setViagemMaisBarata(boolean viagemMaisBarata) {
        this.viagemMaisBarata = viagemMaisBarata;
    }
    

    public int getDuracaoMaxima() {
        return duracaoMaxima;
    }

    public int getTempoEsperaTotalMaximo() {
        return tempoEsperaTotalMaximo;
    }

    public int getTempoEsperaMaximoParagem() {
        return tempoEsperaMaximoParagem;
    }

    public int getPrecoTotalMaximo() {
        return precoTotalMaximo;
    }

    public int getPrecoMaximoTroco() {
        return precoMaximoTroco;
    }
    
    
    
    
    
    

}
