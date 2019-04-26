package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEnemyTest {
    
    TestEnemy testEnemy;
    
    @Before
    public void setUp() {
        testEnemy = new TestEnemy(new Vector2(100, 200));
    }
    
    @Test
    public void enemySetsCorrectStartingHealth() {
        assertEquals(800, testEnemy.getHealth());
    }
    
    @Test
    public void enemySetsCorrectMaxHealth() {
        assertEquals(800, testEnemy.getMaxHealth());
    }
    
    @Test
    public void enemySetsCorrectStartingSpeed() {
        assertEquals(64, testEnemy.getSpeed());
    }
    
    @Test
    public void enemySetsCorrectStartingDamage() {
        assertEquals(2000, testEnemy.getDamage());
    }
    
    @Test
    public void enemySetsCorrectStartingBounds() {
        assertEquals(new Circle(100, 200, 8), testEnemy.getBounds());
    }
    
    @Test
    public void enemyCanLoseHealth() {
        testEnemy.takeDamage(100);
        assertEquals(700, testEnemy.getHealth());
    }
    
    @Test
    public void enemyHealthCantGoNegative() {
        testEnemy.takeDamage(1000);
        assertEquals(800, testEnemy.getHealth());
    }
    
    @Test
    public void enemyDiesCorrectly() {
        testEnemy.takeDamage(150);
        assertEquals(true, testEnemy.isAlive());
        testEnemy.takeDamage(900);
        assertEquals(false, testEnemy.isAlive());
    }
    
    @Test
    public void enemySetsCorrectMaxSpeed() {
        assertEquals(64, testEnemy.getMaxSpeed());
    }
    
    @Test
    public void enemySetsCorrectLocation() {
        assertEquals(new Vector2(100, 200), testEnemy.getLocation());
    }
    
    @Test
    public void enemyMovesCorrectlyUsingInt() {
        testEnemy.move(-40, 1);
        assertEquals(new Vector2(60, 201), testEnemy.getLocation());
    }
    
    @Test
    public void enemyMovesCorrectlyUsingVector() {
        testEnemy.move(new Vector2(4, -101));
        assertEquals(new Vector2(104, 99), testEnemy.getLocation());
    }
}
