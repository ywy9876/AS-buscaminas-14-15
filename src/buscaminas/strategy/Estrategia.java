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
public abstract class Estrategia {
    public abstract String getNom();
    public abstract int getPuntuacioPrincipiant(int param);
    public abstract int getPuntuacioExpert(int param);
    public abstract int getPuntuacioIntermedi(int param);
    
    
    
}
