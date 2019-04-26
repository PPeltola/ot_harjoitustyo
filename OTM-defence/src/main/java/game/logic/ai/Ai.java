package game.logic.ai;

import game.domain.Base;
import game.domain.Obstacle;
import game.domain.Path;
import game.domain.Unit;

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
