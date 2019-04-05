package domain;

public class Base {
    
    private final int MAX_HEALTH = 10000;
    
    private int xCoordinate;
    private int yCoordinate;
    private int health;
    private int radius;

    public Base(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.health = MAX_HEALTH;
        this.radius = 64;
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
    
    public int getXCoordinate() {
        return xCoordinate;
    }
    
    public int getYCoordinate() {
        return yCoordinate;
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
