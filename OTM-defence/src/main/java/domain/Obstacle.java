package domain;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Obstacle {
    
    Polygon polygon;
    
    public Obstacle(float[] vertices) {
        this.polygon = new Polygon(vertices);
    }

    public Obstacle() {
        this.polygon = new Polygon(new float [] {100, 150, 200, 100, 150, 200});
    }

    public Polygon getPolygon() {
        return polygon;
    }
    
}
