package game.gui.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.domain.Unit;

public abstract class UnitActor extends Actor {

    protected Unit unit;
    
    public UnitActor(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void act(float delta) {
        if (!unit.isAlive()) {
            this.remove();
        } else {
            unit.act(delta);
        }
    }
    
    public Unit getUnit() {
        return unit;
    }
    
    public abstract void drawHealthBar(ShapeRenderer shapeRenderer);
}
