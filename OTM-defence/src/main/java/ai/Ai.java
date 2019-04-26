package ai;

import domain.Base;
import domain.Obstacle;
import domain.Path;
import domain.Unit;

public abstract class Ai {
    
    protected Unit enemy;
    
    public Ai(Unit enemy) {
        this.enemy = enemy;
    }
    
    public abstract void setPath(Path path);
    
    public abstract void act(float f);
    
    public abstract void collide(Obstacle obstacle);
    
    public abstract void collide(Unit unit);

    public abstract void overlap(Base base);
}
