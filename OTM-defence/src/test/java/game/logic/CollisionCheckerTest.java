package game.logic;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import game.domain.Obstacle;
import game.domain.TestBase;
import game.domain.TestEnemy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionCheckerTest {

    private CollisionChecker cc;

    @Before
    public void setUp() {
        cc = new CollisionChecker();
    }

    @Test
    public void unitCollidesWithObstacleCorrectly() {
        TestEnemy a = new TestEnemy(new Vector2(100, 100));
        TestEnemy b = new TestEnemy(new Vector2(300, 300));
        Obstacle o = new Obstacle(new float[]{100, 100, 150, 100, 150, 150});
        assertTrue(cc.checkUnitCollisionWithObstacle(a, o));
        assertFalse(cc.checkUnitCollisionWithObstacle(b, o));
    }
    
    @Test
    public void unitCollidesWithUnitCorrectly() {
        TestEnemy a = new TestEnemy(new Vector2(100, 100));
        TestEnemy b = new TestEnemy(new Vector2(104, 104));
        TestEnemy c = new TestEnemy(new Vector2(300, 300));
        assertTrue(cc.checkUnitCollisionWithUnit(a, b));
        assertFalse(cc.checkUnitCollisionWithUnit(a, c));
    }
    
    @Test
    public void unitOverlapsWithBaseCorrectly() {
        TestEnemy a = new TestEnemy(new Vector2(100, 100));
        TestBase b = new TestBase(new Vector2(100, 100));
        TestEnemy c = new TestEnemy(new Vector2(300, 300));
        assertTrue(cc.checkUnitCompleteOverlapWithBase(a, b));
        assertFalse(cc.checkUnitCompleteOverlapWithBase(c, b));
    }
    
    @Test
    public void polygonContainsCircleCorrectly() {
        Polygon p = new Polygon(new float[]{100, 100, 150, 100, 150, 150});
        Circle a = new Circle(125, 111, 5);
        Circle b = new Circle(125, 150, 5);
        assertTrue(cc.polygonContainsCircle(p, a));
        assertFalse(cc.polygonContainsCircle(p, b));
    }
}
