/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author albert
 */

@Entity
@Table(name="jugador")
public class Jugador extends UsuariRegistrat implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @Column(nullable=false)
    private String email;
    
    @OneToOne
    private Partida idPartida;
    
    /*@OneToMany(mappedBy="idjugador", fetch=FetchType.EAGER)
    private ArrayList<Partida> partidesJugades;
    */
    public Jugador() {}
    public Jugador(String n, String c, String u, String p) {
    	super(n,c,u,p);
    }
    
    @Override
    public boolean isUsuariJugador() {
       return true;
    }
    
    public void assignarPartida(Partida p){
        
    }
    
}
