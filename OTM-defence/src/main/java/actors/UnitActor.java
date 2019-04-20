package actors;

import ai.Ai;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.Obstacle;
import domain.Unit;

public abstract class UnitActor extends Actor {

    protected Unit unit;
    

    public UnitActor(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    //public abstract Circle getBounds();
}
