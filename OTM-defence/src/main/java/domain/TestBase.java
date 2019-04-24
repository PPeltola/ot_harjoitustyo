package domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestBase {
    
    private final int MAX_HEALTH = 10000;
    
    private Vector2 location;
    private Circle bounds;
    private int health;
    private int radius;

    public TestBase(int x, int y) {
        this.location = new Vector2(x, y);
        this.health = MAX_HEALTH;
        this.radius = 64;
        this.bounds = new Circle(location, radius);
    }
    
    public boolean loseHealth(int amount) {
        if (health <= amount) {
            // lose the game
            return false;
        } else {
            health -= amount;
            return true;
        }
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

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getRadius() {
        return radius;
    }
    
}
