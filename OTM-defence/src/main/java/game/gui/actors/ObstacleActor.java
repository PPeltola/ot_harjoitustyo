package game.gui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ShortArray;
import game.domain.Obstacle;

public class ObstacleActor extends Actor {

    private Obstacle obstacle;
    private PolygonRegion region;

    public ObstacleActor(Obstacle obstacle, Color color) {
        this.obstacle = obstacle;

        Pixmap fillColor = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        fillColor.setColor(color);
        fillColor.fill();

        Texture texture = new Texture(fillColor);
        TextureRegion textureRegion = new TextureRegion(texture);
        
        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray indices = triangulator.computeTriangles(obstacle.getPolygon().getTransformedVertices());

        region = new PolygonRegion(textureRegion, obstacle.getPolygon().getTransformedVertices(), indices.toArray());
    }

    public void draw(PolygonSpriteBatch batch) {
        batch.draw(region, obstacle.getPolygon().getX(), obstacle.getPolygon().getY());
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
    
    

}
