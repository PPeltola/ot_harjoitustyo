package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Base {

    protected int health;
    protected Vector2 location;
    protected Circle bounds;
    protected boolean alive;
    
    /**
     * Creates a new Base and initializes the location to the given value.
     *
     * @param spawnPoint the base location
     *
     * @return the new Unit
     */
    public Base(Vector2 spawnPoint) {
        this.location = spawnPoint;
        this.alive = true;
    }
    
    /**
     * Subtracts the given damage from health if there is enough health left,
     * otherwise kills the Base, which will lead to losing the game.
     *
     * @param amount the amount of damage taken
     */
    public void loseHealth(int amount) {
        if (health <= amount) {
            die();
        } else {
            health -= amount;
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
