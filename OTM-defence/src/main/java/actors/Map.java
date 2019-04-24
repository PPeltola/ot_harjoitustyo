package actors;

import actors.ObstacleActor;
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
    
    public void drawTerrain(PolygonSpriteBatch batch) {
        batch.begin();
        
        for (ObstacleActor obstacleActor : obstacleActors) {
            obstacleActor.draw(batch);
        }
        
        batch.end();
    }
    
    public void drawPaths(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.CYAN);
        
        for (Path path : paths) {
            Iterator<Vector2> iterator = path.getPointIterator();
            Vector2 currentPoint = iterator.next();
            Vector2 nextPoint;
            
            while (iterator.hasNext()) {
                nextPoint = iterator.next();
                shapeRenderer.line(currentPoint, nextPoint);
                currentPoint = nextPoint;
            }
        }
        
        shapeRenderer.end();
    }
    
    public void addObstacle(Obstacle obstacle) {
        obstacleActors.add(new ObstacleActor(obstacle, TAN));
    }
    
    public void addPath(Path path) {
        paths.add(path);
    }
    
    public Path getPath(int i) {
        return new Path(paths.get(i));
    }
    
    public void setBase(TestBase base) {
        this.base = new TestBaseActor(base);
    }

    public TestBaseActor getBase() {
        return base;
    }

    public Array<ObstacleActor> getObstacleActors() {
        return obstacleActors;
    }
    
    
    
}
