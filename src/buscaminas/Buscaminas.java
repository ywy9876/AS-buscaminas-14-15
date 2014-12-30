/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.security.AccessControlContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author albert
 */
public class Buscaminas {

    /**
     * @param args the command line arguments
     */
    
    private static Buscaminas instance=null;
    private int idPartida;

    private Buscaminas() {
    }
    
    public Buscaminas getInstance(){
        if(instance==null){
            instance = new Buscaminas();
        }
        return instance;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
      
    }

    /**
     * @return the idPartida
     */
    public int getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    
}
