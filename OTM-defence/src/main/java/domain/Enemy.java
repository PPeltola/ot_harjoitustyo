package domain;

public interface Enemy {
    
    public int getXCoordinate();
    
    public int getYCoordinate();
    
    public int getHealth();
    
    public int getSpeed();
    
    public int getDamage();
    
    public int getRadius();
    
    public void move(int x, int y);
}
