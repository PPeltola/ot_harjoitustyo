package gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.Base;

public class BaseActor extends Actor {
    
    private Base base;
    private Texture texture;

    public BaseActor(Base base) {
        this.base = base;
        this.texture = new Texture("assets/bases/testbase.png");
    }

    public BaseActor(Base base, Texture texture) {
        this.base = base;
        this.texture = texture;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, base.getXCoordinate() - base.getRadius(), base.getYCoordinate() - base.getRadius());
    }
    
}
