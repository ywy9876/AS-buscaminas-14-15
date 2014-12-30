/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author albert
 */

@Entity
@Table(name="Nivell")

public class Nivell {
    @Id private String nom;
    private int nombreCasellesxFila;
    private int nombreCasellesxColumna;
    private int nombreMines;
    
    @ManyToOne private Collection<Partida> partidas;

    public Nivell(String nom, int nombreCasellesxFila, int nombreCasellesxColumna, int nombreMines ) {
        this.nom = nom;
        this.nombreCasellesxFila = nombreCasellesxFila;
        this.nombreCasellesxColumna = nombreCasellesxColumna;
        this.nombreMines = nombreMines;        
    }
    
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the nombreCasellesxFila
     */
    public int getNombreCasellesxFila() {
        return nombreCasellesxFila;
    }

    /**
     * @param nombreCasellesxFila the nombreCasellesxFila to set
     */
    public void setNombreCasellesxFila(int nombreCasellesxFila) {
        this.nombreCasellesxFila = nombreCasellesxFila;
    }

    /**
     * @return the nombreCasellesxColumna
     */
    public int getNombreCasellesxColumna() {
        return nombreCasellesxColumna;
    }

    /**
     * @param nombreCasellesxColumna the nombreCasellesxColumna to set
     */
    public void setNombreCasellesxColumna(int nombreCasellesxColumna) {
        this.nombreCasellesxColumna = nombreCasellesxColumna;
    }

    /**
     * @return the nombreMines
     */
    public int getNombreMines() {
        return nombreMines;
    }

    /**
     * @param nombreMines the nombreMines to set
     */
    public void setNombreMines(int nombreMines) {
        this.nombreMines = nombreMines;
    }
}
