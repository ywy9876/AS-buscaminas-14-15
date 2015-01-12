/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author albert
 */
public class Administrador extends UsuariRegistrat{
    private String telefon;
    
    public Administrador(String n, String c, String u, String p) {
    	super(n,c,u,p);
    }
    
    @Override
    public boolean isUsuariJugador() {
        return false;
    }
}
