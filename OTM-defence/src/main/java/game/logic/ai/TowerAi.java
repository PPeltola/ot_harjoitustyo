package game.logic.ai;

import com.badlogic.gdx.utils.Array;
import game.domain.Tower;
import game.domain.Unit;

public abstract class TowerAi {
    
    protected Tower tower;
    protected float timePassedSinceLastAction;
    
    public abstract void act(float f);

    public abstract void checkTarget(Array<Unit> units);
    
}
