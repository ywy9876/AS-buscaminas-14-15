/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author albert
 */
public class PartidaTest {
    
    public PartidaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdPartida method, of class Partida.
     */
    @Test
    public void testGetIdPartida() {
        System.out.println("getIdPartida");
        Partida instance = new Partida(0);
        int expResult = 0;
        int result = instance.getIdPartida();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setIdPartida method, of class Partida.
     */
    @Test
    public void testSetIdPartida() {
        System.out.println("setIdPartida");
        int idPartida = 0;
        Partida instance = new Partida(0);
        instance.setIdPartida(idPartida);
        assertEquals(idPartida, instance.getIdPartida());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isEstaAcabada method, of class Partida.
     */
    @Test
    public void testIsEstaAcabada() {
        System.out.println("isEstaAcabada");
        Partida instance = new Partida(0);
        boolean expResult = false;
        boolean result = instance.isEstaAcabada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setEstaAcabada method, of class Partida.
     */
    @Test
    public void testSetEstaAcabada() {
        System.out.println("setEstaAcabada");
        boolean estaAcabada = false;
        Partida instance = new Partida(0);
        instance.setEstaAcabada(estaAcabada);
        assertEquals(estaAcabada, instance.isEstaAcabada());
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of isEstaGuanyada method, of class Partida.
     */
    @Test
    public void testIsEstaGuanyada() {
        System.out.println("isEstaGuanyada");
        Partida instance = new Partida(0);
        boolean expResult = false;
        boolean result = instance.isEstaGuanyada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setEstaGuanyada method, of class Partida.
     */
    @Test
    public void testSetEstaGuanyada() {
        System.out.println("setEstaGuanyada");
        boolean estaGuanyada = false;
        Partida instance = new Partida(0);
        instance.setEstaGuanyada(estaGuanyada);
         assertEquals(estaGuanyada, instance.isEstaGuanyada());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreTirades method, of class Partida.
     */
    @Test
    public void testGetNombreTirades() {
        System.out.println("getNombreTirades");
        Partida instance = new Partida(0);
        int expResult = 0;
        int result = instance.getNombreTirades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreTirades method, of class Partida.
     */
    @Test
    public void testSetNombreTirades() {
        System.out.println("setNombreTirades");
        int nombreTirades = 0;
        Partida instance = new Partida(0);
        instance.setNombreTirades(nombreTirades);
        assertEquals(nombreTirades, instance.getNombreTirades());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
