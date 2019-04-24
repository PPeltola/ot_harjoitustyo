package domain;

import ai.Ai;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    
    protected Ai ai;
    
    public void collide(Obstacle obstacle) {
        ai.collide(obstacle);
    }
    
    public void collide(Unit unit) {
        ai.collide(unit);
    }
    
    public void act(float delta) {
        ai.act(delta);
    }

    public abstract Vector2 getLocation();

    public abstract int getHealth();

    public abstract int getSpeed();

    public abstract int getDamage();
    
    public abstract void move(int x, int y);
    
    public abstract void move(Vector2 amount);

    public abstract Circle getBounds();
    
    public abstract void setPath(Path path);
}
