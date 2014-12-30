/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivers;

import buscaminas.*;
import java.util.Scanner;

/**
 *
 * @author alexmorral
 */
public class DriverPartida {
    
    
    private static void showOptions(){
        System.out.println("**** Opcions ****");
        System.out.println("1 - Crear Nivell");
        System.out.println("2 - Crear Partida");
        System.out.println("3 - Mostrar Partida");
        System.out.println("0 - Sortir");
    }
    
    public static void main(String[] args) {
        int filas, columnas, minas , opt;
        Scanner sc = new Scanner(System.in);
        showOptions();
        opt = sc.nextInt();
        Nivell n = null;
        Partida p = null;
        while(opt != 0) {
            switch(opt) {
                case 1 :
                    System.out.println("Introdueix el nombre de files");
                    filas = sc.nextInt();
                    System.out.println("Introdueix el nombre de columnes");
                    columnas = sc.nextInt();
                    System.out.println("Introdeix el nombre de mines");
                    minas = sc.nextInt();
                    n = new Nivell("nivell1", filas, columnas, minas);
                    System.out.println("Nivell creat");
                    break;
                case 2 :
                    if(n == null) System.out.println("Primer has de crear un nivell");
                    else {
                        p = new Partida(1);
                        p.setNivell(n);
                        p.initPartida();
                        System.out.println("Partida Creada");
                    }
                    break;
                case 3 :
                    if(p == null) System.out.println("Primer has de crear una partida");
                    else p.mostrarPartida();
                    break;
            }
            showOptions();
            opt = sc.nextInt();
        }
    }
}
