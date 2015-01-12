/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author albert
 */

@Entity
@Table(name="usuariregistrat")

public class UsuariRegistrat implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id protected String nom;
	protected String cognom;
	protected String username;
	protected String password;
    
	public UsuariRegistrat() {
    }
	
	public UsuariRegistrat(String n, String c, String u, String p) {
    	nom = n;
    	cognom = c;
    	username = u;
    	password = p;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public boolean isUsuariJugador() {
    	return false;
    }
    
}
