/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;

import model.*;
import postgres.*;
/**
 *
 * @author alexmorral
 */

public class CtrlJugarPartida {
    private String username;
    private Partida p;
    private Nivell n;
    
    public CtrlJugarPartida() {
        p = null;
        n = null;
    }
    
    public void createPartida(String nivell) throws IOException{
        //cargarNivell amb nom nivell, si no existeix throw exception
        n = new Nivell("Principiant", 8, 8, 10);
        p = new Partida(1);
        p.setNivell(n);
        p.initPartida();
    }
    
    public boolean authenticate(String username, String pass) throws IOException {
    	boolean login = true;
		this.username = username;
		PostgresUsuariRegistrat pur = new PostgresUsuariRegistrat();
		UsuariRegistrat ur = pur.getUsuari(username);
		if(!ur.getPassword().equals(pass)) {
			login = false;
			throw new IOException ("Contrasenya inválida");
		}
		return login;
	}
    
    public ArrayList<String> getNomNivells() {
    	PostgresNivell pn = new PostgresNivell();
    	ArrayList<Nivell> nivs = pn.getAll();
    	ArrayList<String> nomNivells = new ArrayList<String>();
    	for(Nivell n : nivs) {
    		nomNivells.add(n.getNom());
    	}
        return nomNivells;
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
    
    public void mostrarPartida() throws IOException{
        if(p == null) throw new IOException("No hi ha una partida creada");
        if(p.isEstaAcabada()) throw new IOException("La partida ha acabat!");
        else p.mostrarPartida();
    }
    
    
    
    /**
	 * 
	 * @return all the catgories of the system
	 */
	/*public ArrayList<String> fetchCategories() {
		Log.debug(TAG, "fetch categories");
		FetchCategoriesTransaction fetchCategories = new FetchCategoriesTransaction();
		return fetchCategories.execute();
	}*/
}
