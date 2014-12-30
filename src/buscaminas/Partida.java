/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author albert
 */
@Entity
@Table(name="Partida")

public class Partida {
    @Id private int idPartida;
    private boolean estaAcabada;
    private boolean estaGuanyada;
    private int nombreTirades;
    
    @OneToMany private Nivell nivell;
    

    public Partida(int idPartida) {
        this.idPartida=idPartida;
        this.estaAcabada=false;
        this.estaGuanyada=false;
        this.nombreTirades=0;
    }

    
    /**
     * @return the idPartida
     */
    public int getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @return the estaAcabada
     */
    public boolean isEstaAcabada() {
        return estaAcabada;
    }

    /**
     * @param estaAcabada the estaAcabada to set
     */
    public void setEstaAcabada(boolean estaAcabada) {
        this.estaAcabada = estaAcabada;
    }

    /**
     * @return the estaGuanyada
     */
    public boolean isEstaGuanyada() {
        return estaGuanyada;
    }

    /**
     * @param estaGuanyada the estaGuanyada to set
     */
    public void setEstaGuanyada(boolean estaGuanyada) {
        this.estaGuanyada = estaGuanyada;
    }

    /**
     * @return the nombreTirades
     */
    public int getNombreTirades() {
        return nombreTirades;
    }

    /**
     * @param nombreTirades the nombreTirades to set
     */
    public void setNombreTirades(int nombreTirades) {
        this.nombreTirades = nombreTirades;
    }
    
    
}
