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
//        this.polygon = new Polygon(new float[]{100, 100, 100, 300, 300, 300, 300, 100});
    }

    public Polygon getPolygon() {
        return polygon;
    }

}
