package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alkusaldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(990);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenVahentaaSaldoa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void rahaaEiOtetaJosSitaOnLiianVahan() {
        kortti.otaRahaa(100);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenPalauttaaOikein() {
        assertFalse(kortti.otaRahaa(100));
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void kortinMerkkijonoesitysToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
