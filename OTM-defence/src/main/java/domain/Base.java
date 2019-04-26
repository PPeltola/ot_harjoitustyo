package domain;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Base {
    
    protected int health;
    protected Vector2 location;
    protected Circle bounds;
    protected int radius;
    protected boolean alive;
    
    public Base(int x, int y) {
        this.location = new Vector2(x, y);
        this.radius = 64;
        this.bounds = new Circle(location, radius);
        this.alive = true;
    }
    
    public boolean loseHealth(int amount) {
        if (health <= amount) {
            die();
            return false;
        } else {
            health -= amount;
            return true;
        }
    }
    
    public void die() {
        alive = false;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public Vector2 getLocation() {
        return location;
    }

    public Circle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }
    
    public abstract int getMaxHealth();
}
