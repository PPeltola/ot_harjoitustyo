package game.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObstacleTest {
    
    private Obstacle obstacle;
    private float[] test;
    
    @Before
    public void setUp() {
        test = new float[]{100, 100, 150, 100, 150, 150};
        obstacle = new Obstacle(test);
    }
    
    @Test
    public void obstacleInitializesCorrectly() {
        assertEquals(test, obstacle.getPolygon().getVertices());
    }
    
}
