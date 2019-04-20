package actors;

import actors.ObstacleActor;
import com.badlogic.gdx.graphics.Color;
import static com.badlogic.gdx.graphics.Color.WHITE;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import domain.Obstacle;

public class Map extends Actor {
    
    private Array<ObstacleActor> obstacles;

    public Map() {
        this.obstacles = new Array<>();
    }
    
    public void draw() {
        PolygonSpriteBatch batch = new PolygonSpriteBatch();
        
        batch.begin();
        
        for (ObstacleActor obstacle : obstacles) {
            obstacle.draw(batch);
        }
        
        batch.end();
    }
    
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(new ObstacleActor(obstacle, WHITE));
    }

    public Array<ObstacleActor> getObstacles() {
        return obstacles;
    }
    
    
    
}
