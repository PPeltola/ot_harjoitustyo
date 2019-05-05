package game.logic.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import game.domain.TestEnemy;
import game.domain.TestTower;
import game.domain.Unit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TurretAiTest {
    
    private TestTower t;
    
    @Before
    public void setUp() {
        t = new TestTower(new Vector2(100, 100));
    }
    
    @Test
    public void turretFindsTargetWhenInRange() {
        TestEnemy e = new TestEnemy(new Vector2(130, 130));
        Array<Unit> enemies = new Array<>();
        enemies.add(e);
        
        t.checkTarget(enemies);
        
        t.act(100);
        assertTrue(e.getHealth() != e.getMaxHealth());
    }
    
    @Test
    public void turretWontShootWhenNotInRange() {
        TestEnemy e = new TestEnemy(new Vector2(130, 130));
        Array<Unit> enemies = new Array<>();
        enemies.add(e);
        
        t.checkTarget(enemies);
        
        e.move(400, 400);
        
        t.checkTarget(enemies);
        
        t.act(100);
        assertFalse(e.getHealth() != e.getMaxHealth());
    }
}
