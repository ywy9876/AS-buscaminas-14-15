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
public class PuntuacioPerNombreDeTirades extends Estrategia{
	
	public PuntuacioPerNombreDeTirades(){}
	
	@Override
	public String getNom() {
		return "PuntuacioPerNombreDeTirades";
	}
    
	@Override
    public int getPuntuacioPrincipiant(int param) {
		return 140-param;
	}
    @Override
    public int getPuntuacioExpert(int param) {
    	return 500-param;
    }
    @Override
    public int getPuntuacioIntermedi(int param) {
    	return 300-param;
    }
}
