package views;

import java.io.IOException;

import domain.*;
import exception.PwdIncorrecteException;
import exception.UsernameNoExisteixException;

import java.util.ArrayList;




import model.Nivell;
public class CtrlJugarPartidaPresentacio {
	
	private JugarPartidaView jpv;
	private CtrlJugarPartida jpuc;
	
	/**
	 * constructora per defecte
	 */
	public CtrlJugarPartidaPresentacio () {
		jpuc = new CtrlJugarPartida();
		jpv = new JugarPartidaView(this);		
	}
	
	/**
	 *arranca la interficie grafica 
	 */
	public void initialize() {
		jpv.setVisible(true);	
	}
				
	
	public boolean PrLogin(String username,String pass){
		boolean logged = false;
		try {
			logged = jpuc.authenticate(username, pass);
		} catch (UsernameNoExisteixException e) {
			jpv.showMessage(e.toString(), 0);
		} catch (PwdIncorrecteException e) {
			jpv.showMessage(e.toString(), 0);
		} 
		
		ArrayList<String> nivells = jpuc.getNomNivells();
		jpv.loadNivells(nivells);
		if (nivells.isEmpty()) {
			jpv.showMessage("No hi ha Nivells", 1);
		}
		if(!jpuc.isJugador()) jpv.showMessage("Usuari no jugador", 1);
		else jpv.nivellPanelMessages.setText("");
		return logged;
	}
	
	
	
	public void PrStartMatch(String nivell) throws Exception{
	
		Nivell n = jpuc.createPartida(nivell);
		jpv.buildBoard(jpuc.getCaselles(), n);
		
		System.out.println("Partida creada!");
		
		jpuc.mostrarPartida();
		
	}
	
	public void PrLoadPartida() throws Exception{
		jpuc.loadPartida();
		jpv.buildBoard(jpuc.getCaselles(), jpuc.getNivell());
		jpv.updateBoard(jpuc.getCaselles());
		jpv.temps.setText(String.valueOf(jpuc.getTemps()));
		jpuc.mostrarPartida();
	}
	
	public boolean jugadorTePartida() {
		return jpuc.isJugador() && jpuc.tePartida();
	}
	
	
	public void PrStopMatch() throws Exception{
		jpuc.updatePartida();
		System.out.println("Partida Aturada");
	}
	
	
	public void PrCheck(int i, int j, int opt) throws IOException, Exception {
		
		if(opt == 1) jpuc.descobrirCasella(i, j);
		else  jpuc.marcarCasella(i, j);
		if(jpuc.getIsPartidaAcabada()){
			int punt = jpuc.getPuntuacio();
			jpv.finishMatch(jpuc.getIsPartidaGuanyada(), punt);
			
		} 
		
		jpv.updateBoard(jpuc.getCaselles());
		jpuc.updateCaselles();
		jpv.actualitzaTirades(jpuc.getNombreTirades());
		
		
	}
	
	public int checkCasella(int i, int j) {
		return jpuc.checkCasella(i,j);
	}
    
	public void setTemps(int t) {
		jpuc.setTemps(t);
	}
		
	public void enviarEmail() throws Exception{
		if(jpuc.getIsPartidaGuanyada()) {
			jpuc.sendMail();
		}
	}
	public void PrFinishGame(){
		System.exit(-1);
	}

}
