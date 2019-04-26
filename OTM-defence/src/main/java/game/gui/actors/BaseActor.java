package game.gui.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.domain.Base;

public abstract class BaseActor extends Actor {
    
    protected Base base;

    public BaseActor(Base base) {
        this.base = base;
    }

    public Base getBase() {
        return base;
    }
    
    public abstract void drawHealthBar(ShapeRenderer shapeRenderer);
}
