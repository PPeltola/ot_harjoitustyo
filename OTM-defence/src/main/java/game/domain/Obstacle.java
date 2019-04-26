package game.domain;

import com.badlogic.gdx.math.Polygon;

public class Obstacle {

    Polygon polygon;
    
    /**
     * Constructs a new Obstacle and initializes it's polygon's vertices to 
     * the given ones.
     *
     * @param vertices the polygon vertices
     * 
     * @return the new Obstacle
     */
    public Obstacle(float[] vertices) {
        this.polygon = new Polygon(vertices);
    }

    public Polygon getPolygon() {
        return polygon;
    }

}
