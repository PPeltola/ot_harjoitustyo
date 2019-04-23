package domain;

import ai.RunnerAi;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestEnemy extends Unit {
    
    private Vector2 location;
    private int radius;
    private int health;
    private int speed;
    private int damage;
    private Circle bounds;

    public TestEnemy(int x, int y) {
        this.location = new Vector2(x, y);
        this.radius = 8;
        this.health = 1000;
        this.speed = 8;
        this.damage = 200;
        this.bounds = new Circle(x, y, radius);
        this.ai = new RunnerAi(this);
    }

    @Override
    public  Vector2 getLocation() {
        return location;
    }

    @Override
    public int getHealth() {
        return health;
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
        System.out.println(bounds);
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
}
