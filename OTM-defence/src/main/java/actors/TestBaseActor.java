package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.TestBase;

public class TestBaseActor extends BaseActor {

    private Texture texture;
    private HealthBar healthBar;

    public TestBaseActor(TestBase base) {
        super(base);
        this.texture = new Texture("assets/bases/testbase.png");
        this.healthBar = new HealthBar(64, 8);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, base.getLocation().x - base.getBounds().radius, base.getLocation().y - base.getBounds().radius);
    }
    
    @Override
    public void drawHealthBar(ShapeRenderer shapeRenderer) {
        healthBar.draw(shapeRenderer, (int) (base.getLocation().x - base.getBounds().radius / 2), (int) (base.getLocation().y - base.getBounds().radius - 8), base.getMaxHealth(), base.getHealth());
    }
}
