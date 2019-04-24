package domain;

import com.badlogic.gdx.math.Polygon;

public class Obstacle {

    Polygon polygon;

    public Obstacle(float[] vertices) {
        this.polygon = new Polygon(vertices);
    }

    public Polygon getPolygon() {
        return polygon;
    }

}
