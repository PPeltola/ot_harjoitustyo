package game.logic.ai;

import com.badlogic.gdx.math.Vector2;
import game.domain.Path;
import game.domain.TestEnemy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RunnerAiTest {
    
    private TestEnemy e;
    
    @Before
    public void setUp() {
        e = new TestEnemy(new Vector2(0, 0));
    }
    
    @Test
    public void runnerActsByRunning() {
        Path p = new Path(new Vector2(0, 0));
        p.addPointToPath(new Vector2(100, 100));
        e.setPath(p);
        e.act(10);
        assertFalse(e.getLocation().equals(new Vector2(0, 0)));
    }
    
    @Test
    public void runnerStopsWhenDone() {
        Path p = new Path(new Vector2(0, 0));
        p.addPointToPath(new Vector2(10, 10));
        e.setPath(p);
        e.act(10);
        assertTrue(e.getLocation().equals(new Vector2(10, 10)));
    }
    
}
