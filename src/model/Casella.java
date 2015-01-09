/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alexmorral
 */
public class Casella {
    private final int numFila;
    private final int numColumna;
    private boolean estaDescoberta;
    private boolean estaMarcada;
    private final boolean teMina;
    private int numMines;
    
    public Casella(int nF, int nC, boolean desc, boolean marc, boolean mina) {
        numFila = nF;
        numColumna = nC;
        estaDescoberta = desc;
        estaMarcada = marc;
        teMina = mina;
        numMines = -1;
    }
    
    /**
     * @return the estaDescoberta
     */
    public boolean getEstaDescoberta() {
        return estaDescoberta;
    }
    
    /**
     * @return the estaMarcada
     */
    public boolean getEstaMarcada() {
        return estaMarcada;
    }
    
    /**
     * @param b the estaMarcada to set
     */
    public void setEstaMarcada(boolean b) {
        estaMarcada = b;
    }
    
    /**
     * @param b the estaDescoberta to set
     */
    public void setEstaDescoberta(boolean b) {
        estaDescoberta = b;
    }
    
    /**
     * @return the teMina
     */
    public boolean getTeMina() {
        return teMina;
    }
    
    /**
     * @param numMines the numMines
     */
    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }
    
    /**
     * @return the numMines
     */
    public int getNumMines() {
        return numMines;
    }
}
