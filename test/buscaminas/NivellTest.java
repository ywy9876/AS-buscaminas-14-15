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
public class NivellTest {
    
    public NivellTest() {
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
     * Test of getNom method, of class Nivell.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Nivell instance = new Nivell("",0,0,0);
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNom method, of class Nivell.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        Nivell instance = new Nivell("",0,0,0);
        instance.setNom(nom);
        assertEquals(nom, instance.getNom());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreCasellesxFila method, of class Nivell.
     */
    @Test
    public void testGetNombreCasellesxFila() {
        System.out.println("getNombreCasellesxFila");
        Nivell instance = new Nivell("",0,0,0);
        int expResult = 0;
        int result = instance.getNombreCasellesxFila();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreCasellesxFila method, of class Nivell.
     */
    @Test
    public void testSetNombreCasellesxFila() {
        System.out.println("setNombreCasellesxFila");
        int nombreCasellesxFila = 0;
        Nivell instance = new Nivell("",0,0,0);
        instance.setNombreCasellesxFila(nombreCasellesxFila);
        assertEquals(nombreCasellesxFila, instance.getNombreCasellesxFila());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreCasellesxColumna method, of class Nivell.
     */
    @Test
    public void testGetNombreCasellesxColumna() {
        System.out.println("getNombreCasellesxColumna");
        Nivell instance = new Nivell("",0,0,0);
        int expResult = 0;
        int result = instance.getNombreCasellesxColumna();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreCasellesxColumna method, of class Nivell.
     */
    @Test
    public void testSetNombreCasellesxColumna() {
        System.out.println("setNombreCasellesxColumna");
        int nombreCasellesxColumna = 0;
        Nivell instance = new Nivell("",0,0,0);
        instance.setNombreCasellesxColumna(nombreCasellesxColumna);
        assertEquals(nombreCasellesxColumna, instance.getNombreCasellesxColumna());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreMines method, of class Nivell.
     */
    @Test
    public void testGetNombreMines() {
        System.out.println("getNombreMines");
        Nivell instance = new Nivell("",0,0,0);
        int expResult = 0;
        int result = instance.getNombreMines();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreMines method, of class Nivell.
     */
    @Test
    public void testSetNombreMines() {
        System.out.println("setNombreMines");
        int nombreMines = 0;
        Nivell instance = new Nivell("",0,0,0);
        instance.setNombreMines(nombreMines);
        assertEquals(nombreMines, instance.getNombreMines());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
