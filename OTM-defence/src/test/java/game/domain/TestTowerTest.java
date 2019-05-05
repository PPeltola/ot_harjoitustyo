package game.domain;

import com.badlogic.gdx.math.Vector2;
import game.gui.BulletTrail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTowerTest {
    
    private TestTower tt;
    
    @Before
    public void setUp() {
        tt = new TestTower(new Vector2(100, 100));
    }
    
    @Test
    public void towerInitializesCorrectly() {
        assertEquals(new Vector2(100, 100), tt.getLocation());
        assertEquals(2, (int)tt.getFireRate());
        assertEquals(200, tt.getDamage());
        assertEquals(TestTower.getBaseCost(), tt.getCost());
        assertEquals((int)TestTower.getRadius(), (int)tt.getBounds().radius);
        assertEquals(TestTower.getStartingAttackRange(), (int)tt.getRange().radius);
    }
    
    @Test
    public void trailsAddCorrectly() {
        tt.addTrail(new BulletTrail(new Vector2(0, 0), new Vector2(10, 10)));
        assertEquals(1, tt.getTrails().size);
    }
    
    @Test
    public void trailsUpdateCorrectly() {
        tt.addTrail(new BulletTrail(new Vector2(0, 0), new Vector2(10, 10)));
        tt.updateTrails(0.5f);
        tt.updateTrails(0);
        assertEquals(1, tt.getTrails().size);
        tt.updateTrails(1f);
        tt.updateTrails(0);
        assertEquals(0, tt.getTrails().size);
    }
    
    @Test
    public void setTurretAngleCorrectly() {
        tt.setTurretAngle(10);
        assertEquals(10, (int)tt.getTurretAngle());
    }
}
