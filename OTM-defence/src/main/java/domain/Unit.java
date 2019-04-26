package domain;

import actors.HealthBar;
import ai.Ai;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    
    protected Ai ai;
    protected boolean alive;
    protected float timeSinceDamageTaken;
    protected HealthBar healthBar;
    protected int health;

    public Unit() {
        this.alive = true;
    }
    
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
    
    public abstract void takeDamage(int damage);
    
    public float getTimeSinceDamageTaken() {
        return timeSinceDamageTaken;
    }

    public abstract Vector2 getLocation();
    
    public abstract int getMaxHealth();

    public abstract int getSpeed();

    public abstract int getDamage();
    
    public abstract void move(int x, int y);
    
    public abstract void move(Vector2 amount);

    public abstract Circle getBounds();
    
    public abstract void setPath(Path path);
}
