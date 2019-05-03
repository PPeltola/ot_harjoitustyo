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

    public BulletTrail(Vector2 from, Vector2 to) {
        this.from = new Vector2(from);
        this.to = new Vector2(to);
        this.color = Color.YELLOW;
        this.startingAlpha = color.a;
        this.timePassed = 0;
        this.timeVisible = 1;
        this.done = false;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color.r, color.g, color.b, startingAlpha * (timeVisible - timePassed));
        shapeRenderer.line(to, from);
    }

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
