package logic;

import actors.Map;
import actors.ObstacleActor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.Obstacle;
import utils.PolygonCollisionUtil;
import domain.Unit;

public class CollisionChecker {
    
    public boolean checkUnitCollisionWithObstacle(Unit unit, Obstacle obstacle) {
        return PolygonCollisionUtil.overlaps(obstacle.getPolygon(), unit.getBounds());
    }
    
    public boolean checkUnitCollisionWithUnit(Unit unit, Unit collidingWith) {
        return unit.getBounds().overlaps(collidingWith.getBounds());
    }
    
//    public boolean overlaps(float[] vertices, Circle circle) {
//        return false;
//    }
    
}
