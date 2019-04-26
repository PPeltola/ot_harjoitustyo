package game.domain;

import game.logic.ai.Ai;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {

    protected Ai ai;
    protected boolean alive;
    protected float timeSinceDamageTaken;
    protected int health;
    protected Vector2 location;
    protected int speed;
    protected int damage;
    protected Circle bounds;

    /**
     * Creates a new Unit and initializes the location to the given value.
     *
     * @param spawnPoint the starting location
     *
     * @return the new Unit
     */
    public Unit(Vector2 spawnPoint) {
        this.location = spawnPoint;
        this.alive = true;
        this.timeSinceDamageTaken = 0;
    }

    /**
     * Kills the unit by setting the alive value to false so it will get removed
     * from the stage.
     */
    public void die() {
        alive = false;
    }

    public void collide(Obstacle obstacle) {
        ai.collide(obstacle);
    }

    public void collide(Unit unit) {
        ai.collide(unit);
    }

    public void act(float delta) {
        timeSinceDamageTaken += delta;
        ai.act(delta);
    }

    public void overlap(Base base) {
        ai.overlap(base);
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * Subtracts the given damage from health if there is enough health left,
     * otherwise kills the Unit.
     *
     * @param damage the amount of damage taken
     */
    public void takeDamage(int damage) {
        if (health > damage) {
            health -= damage;
            timeSinceDamageTaken = 0;
        } else {
            die();
        }
    }

    public float getTimeSinceDamageTaken() {
        return timeSinceDamageTaken;
    }

    public Vector2 getLocation() {
        return location;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void move(int x, int y) {
        location.add(x, y);
        bounds.setPosition(location.x, location.y);
    }

    public void move(Vector2 amount) {
        location.add(amount);
        bounds.setPosition(location.x, location.y);
    }

    public Circle getBounds() {
        return bounds;
    }

    public void setPath(Path path) {
        ai.setPath(path);
    }

    public abstract int getMaxHealth();

    public abstract int getMaxSpeed();
}
