/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import buscaminas.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author alexmorral
 */
public class CtrlPartida {
    private Partida p;
    private Nivell n;
    
    public CtrlPartida() {
        
    }
    
    public void createPartida(String nivell) {
        //cargarNivell amb nom nivell
        n = new Nivell("Principiant", 8, 8, 10);
        p = new Partida(1);
        p.setNivell(n);
    }
    
    public ArrayList<String> getNomNivells() {
        ArrayList<String> niv = new ArrayList<>();
        niv.add("Principiant");
        return niv;
    }
    
    public void marcarCasella(int i, int j) throws IOException{
        p.marcarCasella(i, j);
    }
    
    public void desmarcarCasella(int i, int j) throws IOException{
        p.desmarcarCasella(i, j);
    }
    
    public void descobrirCasella(int i, int j) throws IOException{
        p.descobrirCasella(i, j);
    }
    
    public void mostrarPartida() {
        p.mostrarPartida();
    }
}
