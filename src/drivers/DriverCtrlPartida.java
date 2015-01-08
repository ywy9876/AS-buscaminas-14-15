/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivers;

import domain.CtrlPartida;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author alexmorral
 */
public class DriverCtrlPartida {
    
    
    private static void showOptions(){
        System.out.println("**** Opcions ****");
        System.out.println("1 - Mostrar Nivells");
        System.out.println("2 - Crear Partida");
        System.out.println("3 - Mostrar Partida");
        System.out.println("4 - Descobrir Casella");
        System.out.println("0 - Sortir");
    }
    
    public static void main(String[] args) {
        CtrlPartida ctrlPartida = new CtrlPartida();
        int opt;
        String niv;
        Scanner sc = new Scanner(System.in);
        showOptions();
        opt = sc.nextInt();
        while(opt != 0) {
            switch(opt) {
                case 1 :
                    for(String n : ctrlPartida.getNomNivells()) {
                        System.out.println(n);
                    }                    
                    break;
                case 2 :
                    System.out.println("Quin nivell vols jugar?");
                    niv = sc.next();
                    try {
                        ctrlPartida.createPartida(niv);
                    } catch (IOException ex) { System.out.println(ex); }
                    System.out.println("Partida Creada");
                    break;
                case 3 :
                    try {
                        ctrlPartida.mostrarPartida();
                    } catch (IOException ex) { System.out.println(ex); }
                    break;
                case 4 :
                    System.out.println("Introdueix la fila i la columna");
                    int i, j;
                    i = sc.nextInt();
                    j = sc.nextInt();
                    try {
                        ctrlPartida.descobrirCasella(i,j);
                    } catch (IOException ex) { System.out.println(ex); }
                    break;
            }
            showOptions();
            opt = sc.nextInt();
        }
    }
}
