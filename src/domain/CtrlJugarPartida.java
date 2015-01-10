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
    
    public Nivell createPartida(String nivell) {
        //cargarNivell amb nom nivell, si no existeix throw exception
    	PostgresNivell pn = new PostgresNivell();
    	n = new Nivell(pn.getNivell(nivell));
        p = new Partida(1);
        p.setNivell(n);
        p.initPartida();
        return n;
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
    
    public String marcarCasella(int i, int j) {
    	String ret;
    	if(p.getMarcada(i,j)) {
    		p.desmarcarCasella(i, j);
    		ret = "D";
    	}
    	else {
    		p.marcarCasella(i, j);
    		ret = "M";
    	}
    	return ret;
    }
    
    public void desmarcarCasella(int i, int j) throws IOException{
        p.desmarcarCasella(i, j);
    }
    
    public String descobrirCasella(int i, int j) throws IOException{
        return p.descobrirCasella(i, j);
    }
    
    public void mostrarPartida() {
        p.mostrarPartida();
    }
    
    public Casella[][] getCaselles() {
    	return p.getCaselles();
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
