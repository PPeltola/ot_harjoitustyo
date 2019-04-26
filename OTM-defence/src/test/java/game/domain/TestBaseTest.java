package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import game.domain.TestBase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBaseTest {
    
    TestBase testBase;
    
    @Before
    public void setUp() {
        testBase = new TestBase(new Vector2(512, 384));
    }
    
    @Test
    public void baseSetsCorrectStartingHealth() {
        assertEquals(10000, testBase.getHealth());
    }

    @Test
    public void baseCanLoseHealth() {
        testBase.loseHealth(1000);
        assertEquals(9000, testBase.getHealth());
    }
    
    @Test
    public void baseHealthCantGoNegative() {
        testBase.loseHealth(10001);
        assertEquals(10000, testBase.getHealth());
    }
    
    @Test
    public void baseDiesWhenDamaged() {
        testBase.loseHealth(10000);
        assertEquals(false, testBase.isAlive());
    }
    
    @Test
    public void baseSetsAliveCorrectly() {
        assertEquals(true, testBase.isAlive());
    }
    
    @Test
    public void baseGivesCorrectCoordinates() {
        assertEquals(512, (int) testBase.getLocation().x);
        assertEquals(384, (int) testBase.getLocation().y);
    }
    
    @Test
    public void baseGivesCorrectBounds() {
        assertEquals(new Circle(512, 384, 64), testBase.getBounds());
    }
    
    @Test
    public void baseGivesCorrectMaximumHealth() {
        testBase.loseHealth(1000);
        assertEquals(10000, testBase.getMaxHealth());
    }
}
