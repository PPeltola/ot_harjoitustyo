package game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class BulletTrail {

    private Vector2 from;
    private Vector2 to;
    private Color color;
    private float startingAlpha;
    private float timePassed;
    private float timeVisible;
    private boolean done;
    
    /**
     * Creates the BulletTrail and sets it's 'to' and 'from' vectors to the
     * given ones
     * 
     * @param from one end of the trail
     * @param to the other end of the trail
     *
     * @return the created BulletTrail
     */
    public BulletTrail(Vector2 from, Vector2 to) {
        this.from = new Vector2(from);
        this.to = new Vector2(to);
        this.color = Color.YELLOW;
        this.startingAlpha = color.a;
        this.timePassed = 0;
        this.timeVisible = 1;
        this.done = false;
    }
    
    /**
     * Draws the BulletTrail by setting it's alpha depending on how long it 
     * has been visible and calling the given shapeRenderer's drawLine 
     * command.
     * 
     * @param shapeRenderer the ShapeRenderer to use
     */
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color.r, color.g, color.b, startingAlpha * (timeVisible - timePassed));
        shapeRenderer.line(to, from);
    }
    
    /**
     * Updates the BulletTrail by adding the given delta time to timePassed
     * and sets the trail to be removed if enough time has passed.
     * 
     * @param f delta time
     */
    public void update(float f) {
        timePassed += f;
        if (timePassed >= timeVisible) {
            done = true;
        }
    }

    public boolean isDone() {
        return done;
    }
}
