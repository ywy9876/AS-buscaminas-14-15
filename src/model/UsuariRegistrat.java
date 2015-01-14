/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author alexmorral
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="usuariregistrat")

public class UsuariRegistrat implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String nom;
	
	@Column(nullable=false)
	private String cognom;
    
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
    
    public String getNom() {
    	return nom;
    }
    
}
