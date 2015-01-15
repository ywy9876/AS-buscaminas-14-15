/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import buscaminas.strategy.EstrategiaFactory;
import exception.NoHiHaNivellsException;
import exception.PwdIncorrecteException;
import exception.UsernameNoExisteixException;
import model.*;
import postgres.*;
import service.*;
/**
 *
 * @author alexmorral
 */

public class CtrlJugarPartida {
    private UsuariRegistrat usuari;
    private Jugador jugador;
    private Partida p;
    private Nivell n;
    private boolean[][] mark;
    private int casellesDescobertes;
    private int casellesADescobrir;
    private boolean isJugador;
    
    public CtrlJugarPartida() {
        p = null;
        n = null;
    }
    
    public Nivell createPartida(String nivell) throws Exception{
    	PostgresFactory pFact = PostgresFactory.getInstance();
    	PostgresNivell pn  = pFact.getPostgresNivell();
    	PostgresBuscaminas pb = pFact.getPostgresBuscaminas();
    	PostgresJugador pj = pFact.getPostgresJugador();
    	Buscaminas b = pb.getBuscaminas();
    	int idPartida = b.getIdPartida();
    	n = new Nivell(pn.getNivell(nivell));
        p = new Partida(idPartida, jugador);
        
        b.setIdPartida(idPartida+1);
        jugador.assignarPartida(p);
        pb.update(b);
       
        p.setNivell(n);
        
        String estrategia = EstrategiaFactory.getEstrategiaRandom().getNom();
        p.setEstrategia(estrategia);
        p.initPartida();
        pj.update(jugador);
        casellesDescobertes = 0;
        casellesADescobrir = (n.getNombreCasellesxFila()*n.getNombreCasellesxColumna())-n.getNombreMines();
        return n;
    }
    
    
    public void loadPartida() {
    	p = jugador.getPartida();
    	n = p.getNivell();
    	casellesADescobrir = (n.getNombreCasellesxFila()*n.getNombreCasellesxColumna())-n.getNombreMines();
    	casellesDescobertes = p.getCasellesDescobertes();
    }
    
    
    public boolean authenticate(String username, String pass) throws UsernameNoExisteixException, PwdIncorrecteException {
    	boolean login = true;
		PostgresFactory pFactory = PostgresFactory.getInstance();
    	PostgresUsuariRegistrat pur = pFactory.getPostgresUsuariRegistrat();
		UsuariRegistrat ur = pur.getUsuari(username);
		PostgresJugador pj = pFactory.getPostgresJugador();
		isJugador = pj.exists(username);
		if(isJugador) {
			jugador = pj.getJugador(username);
			if(jugador.tePartida()) System.out.println("SI TE PARTIDA");
			else System.out.println("NO TE PARTIDA");
		}
		usuari = ur;
		if(!ur.getPassword().equals(pass)) {
			login = false;
			throw new PwdIncorrecteException("Contrasenya no válida");
		}
		return login;
	}
    
    public boolean tePartida() {
    	return jugador.tePartida();
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
    	c.setEstaDescoberta(true);
		++casellesDescobertes;
    	if(!c.getTeMina() && c.getNumMines()==0) {
    		int nF = n.getNombreCasellesxFila();
    		int nC = n.getNombreCasellesxColumna();
    		//++casellesDescobertes;
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
    
    public void descobrirCasella(int i, int j) throws IOException, Exception{
    	Casella c = p.getCasella(i, j);
    	if(c.getEstaDescoberta()) throw new IOException("Casella ja descoberta");
    	if(c.getEstaMarcada()) throw new IOException("Casella ja marcada");
    	int numMines = c.getNumMines();
    	boolean teMina = c.getTeMina();
    	boolean sumat = false;
    	if(numMines==0 && !teMina) {
    		mark = new boolean[n.getNombreCasellesxFila()][n.getNombreCasellesxColumna()];
    		for(boolean[] b : mark) Arrays.fill(b, false);
    		descobrirCasellaRec(i,j);
    		sumat = true;
    	}
    	else {
    		c.setEstaDescoberta(true);
    	}
    	if(teMina) p.setEstaAcabada(true);
    	else {
    		System.out.println("Caselles Descobertes: "+casellesDescobertes + " / Caselles a descobrir: "+casellesADescobrir);
    		if(!sumat) ++casellesDescobertes;
    		if(casellesDescobertes >= casellesADescobrir) {
    			p.setEstaAcabada(true);
    			p.setEstaGuanyada(true);
    		}
    	}
    	p.setNombreTirades(p.getNombreTirades()+1);
    	PostgresFactory pFact = PostgresFactory.getInstance();
    	PostgresPartida pp = pFact.getPostgresPartida();
    	pp.update(p);
    	if(p.isEstaAcabada()) {
    		PostgresJugador pj = pFact.getPostgresJugador();
    		jugador.assignarPartida(null);
    		pj.update(jugador);
    	}
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
    
    public int getPuntuacio() {
    	return p.getPuntuacio();
    }
    
    public void setTemps(int t) {
    	p.setTemps(t);
    }
    
    public boolean isJugador() {
    	return isJugador;
    }
    
    public Nivell getNivell() {
    	return n;
    }
    
    public int getTemps() {
    	return p.getTemps();
    }
    
    public void sendMail() throws Exception{
    	EmailService emailService = null;
		emailService = (EmailService) ServiceLocator.getInstance().find("EmailService");
		if(emailService != null) {
			String messageBody = "Enhorabona, has guanyat la partida amb puntuacio: " + p.getPuntuacio();
			String subject = "Informacio Partida Guanyada Buscamines";
			emailService.sendMail(jugador.getNom(), jugador.getEmail(), subject, messageBody);
		}
    }
    public void updatePartida() throws Exception{
    	PostgresFactory pFact = PostgresFactory.getInstance();
    	PostgresPartida pp = pFact.getPostgresPartida();
    	pp.update(p);
    }
    
    public void updateCaselles() throws Exception{
    	PostgresFactory pFact = PostgresFactory.getInstance();
    	PostgresPartida pp = pFact.getPostgresPartida();
    	pp.update(p.getCaselles());
    }
}
