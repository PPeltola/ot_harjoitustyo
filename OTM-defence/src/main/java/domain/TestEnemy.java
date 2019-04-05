package domain;

public class TestEnemy implements Enemy {
    
    private int xCoordinate;
    private int yCoordinate;
    private int radius;
    private int health;
    private int speed;
    private int damage;

    public TestEnemy(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.radius = 8;
        this.health = 1000;
        this.speed = 8;
        this.damage = 200;
    }

    @Override
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }
    
    public int getRadius() {
        return this.radius;
    }  

    @Override
    public void move(int x, int y) {
        this.xCoordinate += x;
        this.yCoordinate += y;
    }
}
