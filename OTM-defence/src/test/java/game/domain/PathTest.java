package game.domain;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathTest {

    private Path path;

    @Before
    public void setUp() {
        path = new Path(new Vector2(10, 10));
    }

    @Test
    public void pathInitializesCorrectly() {
        assertEquals(new Vector2(10, 10), path.getSpawningPosition());
        assertEquals(1, path.getPoints().size);
    }

    @Test
    public void pathCopiesCorrectly() {
        Path newPath = new Path(path);
        assertEquals(new Vector2(10, 10), newPath.getSpawningPosition());
        assertEquals(1, newPath.getPoints().size);
        assertTrue(!newPath.equals(path));

    }

    @Test
    public void pathAddsPointsCorrectly() {
        path.addPointToPath(new Vector2(20, 20));
        assertEquals(2, path.getPoints().size);
        assertEquals(new Vector2(20, 20), path.getNextPoint());
    }
    
    @Test
    public void pathEndsCorrectly() {
        assertTrue(path.ends());
    }
    
    @Test
    public void pathGivesIteratorCorrectly() {
        assertEquals(new Vector2(10, 10), path.getPointIterator().next());
    }
}
