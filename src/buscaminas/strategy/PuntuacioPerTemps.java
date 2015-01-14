/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas.strategy;

/**
 *
 * @author albert
 */
public class PuntuacioPerTemps extends Estrategia{
	
	public PuntuacioPerTemps(){}
	
	@Override
	public String getNom() {
		return "PuntuacioPerTemps";
	}
	
	@Override
    public int getPuntuacioPrincipiant(int param) {
		return 200-param;
	}
    @Override
    public int getPuntuacioExpert(int param) {
    	return 600-param;
    }
    @Override
    public int getPuntuacioIntermedi(int param) {
    	return 400-param;
    }
    
}
