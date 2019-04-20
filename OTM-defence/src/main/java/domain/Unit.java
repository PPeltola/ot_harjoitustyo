package domain;

import ai.Ai;
import com.badlogic.gdx.math.Circle;

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

    public abstract int getXCoordinate();

    public abstract int getYCoordinate();

    public abstract int getHealth();

    public abstract int getSpeed();

    public abstract int getDamage();
    
    public abstract void move(int x, int y);

    public abstract Circle getBounds();
}
