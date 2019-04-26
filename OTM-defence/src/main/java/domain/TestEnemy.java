package domain;

import actors.HealthBar;
import ai.RunnerAi;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestEnemy extends Unit {
    
    private final int MAX_HEALTH = 800;
    
    private Vector2 location;
    private int radius;
    
    private int speed;
    private int damage;
    private Circle bounds;

    public TestEnemy(Vector2 spawnPoint) {
        this.location = spawnPoint;
        this.radius = 8;
        this.health = 1000;
        this.speed = 128;
        this.damage = 2000;
        this.bounds = new Circle(spawnPoint, radius);
        this.ai = new RunnerAi(this);
        this.healthBar = new HealthBar(16, 2);
        this.timeSinceDamageTaken = 0;
    }

    @Override
    public  Vector2 getLocation() {
        return location;
    }
    
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getDamage() {
        return damage;
    }
    
    public int getRadius() {
        return radius;
    }  

    @Override
    public void move(int x, int y) {
        location.add(x, y);
        bounds.setPosition(location.x, location.y);
    }
    
    @Override
    public void move(Vector2 amount) {
        location.add(amount);
        bounds.setPosition(location.x, location.y);
    }

    @Override
    public Circle getBounds() {
        return bounds;
    }

    @Override
    public void setPath(Path path) {
        ai.setPath(path);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public void takeDamage(int damage) {
        if (health > damage) {
            health -= damage;
            timeSinceDamageTaken = 0;
        } else {
            die();
        }
    }
}
