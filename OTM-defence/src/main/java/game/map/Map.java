package game.map;

import game.gui.actors.ObstacleActor;
import game.gui.actors.ObstacleActor;
import game.gui.actors.TestBaseActor;
import static com.badlogic.gdx.graphics.Color.TAN;
import com.badlogic.gdx.utils.Array;
import game.domain.TestBase;
import game.domain.Obstacle;
import game.domain.Path;

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
