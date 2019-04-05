package domain;

import domain.Base;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseTest {
    
    Base base;
    
    @Before
    public void setUp() {
        base = new Base(512, 384);
    }
    
    @Test
    public void baseSetsCorrectStartingHealth() {
        assertEquals(10000, base.getHealth());
    }

    @Test
    public void baseCanLoseHealth() {
        assertTrue(base.loseHealth(1000));
        assertEquals(9000, base.getHealth());
    }
    
    @Test
    public void baseHealthCantGoNegative() {
        assertTrue(base.loseHealth(9001));
        assertEquals(false, base.loseHealth(1000));
    }
}
