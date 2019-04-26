package game.logic;


import game.domain.Base;
import game.domain.Obstacle;
import game.utils.PolygonCollisionUtil;
import game.domain.Unit;

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
