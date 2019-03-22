package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void alustuksetOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaastiSyominenPalauttaaOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }

    @Test
    public void edullisestiSyominenPalauttaaOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void maukkaastiSyominenKasvattaaSaldoa() {
        kassa.syoMaukkaasti(1000);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void edullisestiSyominenKasvattaaSaldoa() {
        kassa.syoEdullisesti(1000);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void maukkaastiSyominenKasvattaaMaaraa() {
        kassa.syoMaukkaasti(987);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisestiSyominenKasvattaaMaaraa() {
        kassa.syoEdullisesti(987);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisestiSyominenEiToimiRahatta() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiSyominenEiToimiRahatta() {
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisestiKortillaSyominenVeloittaaOikein() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void maukkaastiKortillaSyominenVeloittaaOikein() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullisestiKortillaSyominenKasvattaaMaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiKortillaSyominenKasvattaaMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisestiKortillaSyominenEiToimiSaldotta() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        
        assertFalse(kassa.syoEdullisesti(kortti));
        
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaastiKortillaSyominenEiToimiSaldotta() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        
        assertFalse(kassa.syoMaukkaasti(kortti));
        
        assertEquals(200, kortti.saldo());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiKasvaKortillaSyotaessa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaRahaSiirtyy() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
