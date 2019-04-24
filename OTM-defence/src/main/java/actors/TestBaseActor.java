package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.TestBase;

public class TestBaseActor extends Actor {
    
    private TestBase base;
    private Texture texture;

    public TestBaseActor(TestBase base) {
        this.base = base;
        this.texture = new Texture("assets/bases/testbase.png");
    }

    public TestBaseActor(TestBase base, Texture texture) {
        this.base = base;
        this.texture = texture;
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, base.getLocation().x - base.getBounds().radius, base.getLocation().y - base.getBounds().radius);
    }
    
}
