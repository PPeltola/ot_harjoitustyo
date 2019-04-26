package map;

import actors.ObstacleActor;
import actors.ObstacleActor;
import actors.TestBaseActor;
import com.badlogic.gdx.graphics.Color;
import static com.badlogic.gdx.graphics.Color.TAN;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import domain.TestBase;
import domain.Obstacle;
import domain.Path;
import java.util.Iterator;

public class Map {
    
    private Array<ObstacleActor> obstacleActors;
    private TestBaseActor base;
    private Array<Path> paths;

    public Map() {
        this.obstacleActors = new Array<>(false, 32);
        this.paths = new Array<>(true, 8);
    }
    
    public void addObstacle(Obstacle obstacle) {
        obstacleActors.add(new ObstacleActor(obstacle, TAN));
    }
    
    public void addPath(Path path) {
        paths.add(path);
    }

    public Array<Path> getPaths() {
        return paths;
    }
    
    public Path getPath(int i) {
        return new Path(paths.get(i));
    }
    
    public void setBaseActor(TestBase base) {
        this.base = new TestBaseActor(base);
    }

    public TestBaseActor getBaseActor() {
        return base;
    }

    public Array<ObstacleActor> getObstacleActors() {
        return obstacleActors;
    }
    
    
    
}
