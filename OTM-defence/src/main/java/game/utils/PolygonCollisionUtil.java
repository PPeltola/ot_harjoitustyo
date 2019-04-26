package game.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class PolygonCollisionUtil {

    static final Vector2 CENTER = new Vector2();
    static final Vector2 VECTOR_A = new Vector2();
    static final Vector2 VECTOR_B = new Vector2();

    public static boolean overlaps(Polygon polygon, Circle circle) {
        float[] vertices = polygon.getTransformedVertices();
        CENTER.set(circle.x, circle.y);
        float squareRadius = circle.radius * circle.radius;
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(VECTOR_A.set(vertices[vertices.length - 2], vertices[vertices.length - 1]), VECTOR_B.set(vertices[i], vertices[i + 1]), CENTER, squareRadius)) {
                    return true;
                }
            } else {
                if (Intersector.intersectSegmentCircle(VECTOR_A.set(vertices[i - 2], vertices[i - 1]), VECTOR_B.set(vertices[i], vertices[i + 1]), CENTER, squareRadius)) {
                    return true;
                }
            }
        }
        
        return polygon.contains(circle.x, circle.y);
    }
}
