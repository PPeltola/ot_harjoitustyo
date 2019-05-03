package game.logic;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import game.domain.Base;
import game.domain.Obstacle;
import game.domain.Unit;

public class CollisionChecker {
    
    private Vector2 center = new Vector2();
    private Vector2 vecA = new Vector2();
    private Vector2 vecB = new Vector2();
    
    private boolean edgesOverlap(Polygon polygon, Circle circle) {
        float[] vertices = polygon.getTransformedVertices();
        center.set(circle.x, circle.y);
        float squareRadius = circle.radius * circle.radius;
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(vecA.set(vertices[vertices.length - 2], vertices[vertices.length - 1]), vecB.set(vertices[i], vertices[i + 1]), center, squareRadius)) {
                    return true;
                }
            } else {
                if (Intersector.intersectSegmentCircle(vecA.set(vertices[i - 2], vertices[i - 1]), vecB.set(vertices[i], vertices[i + 1]), center, squareRadius)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkUnitCollisionWithObstacle(Unit unit, Obstacle obstacle) {
        return (!edgesOverlap(obstacle.getPolygon(), unit.getBounds()) && obstacle.getPolygon().contains(new Vector2(unit.getBounds().x, unit.getBounds().y)));
    }

    public boolean checkUnitCollisionWithUnit(Unit unit, Unit collidingWith) {
        return unit.getBounds().overlaps(collidingWith.getBounds());
    }

    public boolean checkUnitCompleteOverlapWithBase(Unit unit, Base base) {
        return base.getBounds().contains(unit.getBounds());
    }

    public boolean polygonContainsCircle(Polygon polygon, Circle circle) {
        return (!edgesOverlap(polygon, circle) && polygon.contains(new Vector2(circle.x, circle.y)));
    }
}
