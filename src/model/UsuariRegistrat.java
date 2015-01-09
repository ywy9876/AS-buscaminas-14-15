/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

import javax.persistence.Entity;
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
	@Id private String nom;
    private String cognom;
    private String username;
    private String password;

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
