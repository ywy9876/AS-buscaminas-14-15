/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Set;

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
    private Casella[][] caselles;

    public Partida(int idPartida) {
        this.idPartida=idPartida;
        this.estaAcabada=false;
        this.estaGuanyada=false;
        this.nombreTirades=0;
    }
    
    
    /**
     * @return ArrayList<String> amb posicio de les mines
     */
    private ArrayList<String> generarMinas() {
        int nombreMines = nivell.getNombreMines();
        ArrayList<Integer> f = new ArrayList<>();
        for(int i = 0; i < nivell.getNombreCasellesxFila(); ++i) f.add(i);
        ArrayList<Integer> c = new ArrayList<>();
        for(int i = 0; i < nivell.getNombreCasellesxColumna(); ++i) c.add(i);
        int count = 0;
        ArrayList<String> minas = new ArrayList<>();
        while(count < nombreMines) {
            Collections.shuffle(f);
            Collections.shuffle(c);
            if(!minas.contains(f.get(0)+" "+c.get(0))) minas.add(f.get(0)+" "+c.get(0));
            ++count;
        }
        return minas;
    }
    
    
    public void initPartida() {    
        this.caselles = new Casella[nivell.getNombreCasellesxFila()][nivell.getNombreCasellesxColumna()];
        ArrayList<String> minas = generarMinas();
        for(int i = 0; i < nivell.getNombreCasellesxFila(); ++i) {
            for(int j = 0; j < nivell.getNombreCasellesxColumna(); ++j) {
                boolean mina = false;
                for(String st : minas) {
                    String[] parts = st.split(" ");
                    if(Integer.parseInt(parts[0])==i && Integer.parseInt(parts[1])==j) mina = true;
                }
                caselles[i][j] = new Casella(i, j, false, false, mina);
            }
        }
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
    
    /**
     * @param nivell the nivell to set
     */
    public void setNivell(Nivell nivell) {
        this.nivell = nivell;
    }
    
    
    public void mostrarPartida() {
        for(int i = 0; i < nivell.getNombreCasellesxFila(); ++i) {
            for(int j = 0; j < nivell.getNombreCasellesxColumna(); ++j) {
                if(caselles[i][j].getTeMina()) System.out.printf("* ");
                else System.out.printf(". ");
            }
            System.out.println("");
        }
    }
    
}
