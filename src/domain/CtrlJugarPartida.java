/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import exception.NoHiHaNivellsException;
import exception.PwdIncorrecteException;
import exception.UsernameNoExisteixException;
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
    private boolean[][] mark;
    private int casellesDescobertes;
    private int casellesADescobrir;
    
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
        casellesDescobertes = 0;
        casellesADescobrir = (n.getNombreCasellesxFila()*n.getNombreCasellesxColumna())-n.getNombreMines();
        return n;
    }
    
    public boolean authenticate(String username, String pass) throws UsernameNoExisteixException, PwdIncorrecteException {
    	boolean login = true;
		this.username = username;
		PostgresFactory pFactory = PostgresFactory.getInstance();
    	PostgresUsuariRegistrat pur = pFactory.getPostgresUsuariRegistrat();
		UsuariRegistrat ur = pur.getUsuari(username);
		if(!ur.getPassword().equals(pass)) {
			login = false;
			throw new PwdIncorrecteException("Contrasenya no válida");
		}
		return login;
	}
    
    public ArrayList<String> getNomNivells() {
    	PostgresFactory pFactory = PostgresFactory.getInstance();
    	PostgresNivell pn = pFactory.getPostgresNivell();
    	ArrayList<String> nomNivells = new ArrayList<String>();
    	try {
    		ArrayList<Nivell> nivs = pn.getAll();
	    	for(Nivell n : nivs) {
	    		nomNivells.add(n.getNom());
	    	}
    	} catch (NoHiHaNivellsException ex) {
    		nomNivells.add(ex.toString());
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
    
    public void descobrirCasellaRec(int i, int j) {
    	Casella c = p.getCasella(i, j);
    	mark[i][j] = true;
    	if(!c.getTeMina() && c.getNumMines()==0) {
    		c.setEstaDescoberta(true);
    		++casellesDescobertes;
    		int nF = n.getNombreCasellesxFila();
    		int nC = n.getNombreCasellesxColumna();
    		
    		if(i+1<nF && j+1<nC && !mark[i+1][j+1]) descobrirCasellaRec(i+1,j+1);
            if(i+1<nF && !mark[i+1][j]) descobrirCasellaRec(i+1,j);
            if(i+1<nF && j-1>-1 && !mark[i+1][j-1]) descobrirCasellaRec(i+1,j-1);
            if(j+1<nC && !mark[i][j+1]) descobrirCasellaRec(i,j+1);
            if(j-1>-1 && !mark[i][j-1]) descobrirCasellaRec(i,j-1);
            if(i-1>-1 && j+1<nC && !mark[i-1][j+1]) descobrirCasellaRec(i-1,j+1);
            if(i-1>-1 && !mark[i-1][j]) descobrirCasellaRec(i-1,j);
            if(i-1>-1 && j-1>-1 && !mark[i-1][j-1]) descobrirCasellaRec(i-1,j-1);
    	}
    }
    
    public void descobrirCasella(int i, int j) throws IOException{
    	Casella c = p.getCasella(i, j);
    	if(c.getEstaDescoberta()) throw new IOException("Casella ja descoberta");
    	if(c.getEstaMarcada()) throw new IOException("Casella ja marcada");
    	int numMines = c.getNumMines();
    	boolean teMina = c.getTeMina();
    	if(numMines==0 && !teMina) {
    		mark = new boolean[n.getNombreCasellesxFila()][n.getNombreCasellesxColumna()];
    		for(boolean[] b : mark) Arrays.fill(b, false);
    		descobrirCasellaRec(i,j);
    		--casellesDescobertes;
    	}
    	else {
    		c.setEstaDescoberta(true);
    	}
    	if(teMina) p.setEstaAcabada(true);
    	else {
    		++casellesDescobertes;
    		System.out.println("CasellesDescobertes: "+casellesDescobertes+ " / CasellesADescobrir: "+casellesADescobrir);
    		if(casellesDescobertes >= casellesADescobrir) {
    			p.setEstaAcabada(true);
    			p.setEstaGuanyada(true);
    		}
    	}
    	p.setNombreTirades(p.getNombreTirades()+1);
    }
    
    public int checkCasella(int i, int j) {
    	Casella[][] cas = p.getCaselles();
    	if(cas[i][j].getEstaMarcada()) return -2;
    	else {
    		if(cas[i][j].getEstaDescoberta() && !cas[i][j].getTeMina()) return cas[i][j].getNumMines();
    		else if(cas[i][j].getEstaDescoberta() && cas[i][j].getTeMina()) return -1;
    		else return -3;
    	}
    	
    }
    
    public boolean getIsPartidaAcabada(){
    	return p.isEstaAcabada();
    }
    
    public boolean getIsPartidaGuanyada() {
    	return p.isEstaGuanyada();
    }
    
    public void mostrarPartida() {
        p.mostrarPartida();
    }
    
    public Casella[][] getCaselles() {
    	return p.getCaselles();
    }
    
    public int getNombreTirades() {
    	return p.getNombreTirades();
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
