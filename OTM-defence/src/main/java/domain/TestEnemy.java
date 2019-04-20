package domain;

import ai.RunnerAi;
import com.badlogic.gdx.math.Circle;

public class TestEnemy extends Unit {
    
    private int xCoordinate;
    private int yCoordinate;
    private int radius;
    private int health;
    private int speed;
    private int damage;
    private Circle bounds;

    public TestEnemy(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.radius = 8;
        this.health = 1000;
        this.speed = 8;
        this.damage = 200;
        this.bounds = new Circle(x, y, radius);
        this.ai = new RunnerAi(this);
    }

    @Override
    public int getXCoordinate() {
        return xCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return yCoordinate;
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
        xCoordinate += x;
        yCoordinate += y;
        bounds.setPosition(xCoordinate + radius, yCoordinate + radius);
    }

    @Override
    public Circle getBounds() {
        return bounds;
    }
}
