/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author albert
 */
public abstract class UsuariRegistrat {
    private String nom;
    private String cognom;
    private String username;
    private String password;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public abstract boolean isUsuariJugador();
    
}
