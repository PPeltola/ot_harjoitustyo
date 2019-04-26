package logic;

import map.Map;
import actors.ObstacleActor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.Base;
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
    
    public boolean checkUnitCompleteOverlapWithBase(Unit unit, Base base) {
        return base.getBounds().contains(unit.getBounds());
    }    
}
