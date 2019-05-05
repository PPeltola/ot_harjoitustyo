package game.gui.actors;

import game.gui.HealthBar;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.domain.TestBase;

public class TestBaseActor extends BaseActor {
    
    private static final int HEALTHBAR_WIDTH = 96;
    private static final int HEALTHBAR_HEIGHT = 8;
    private static final int HEALTHBAR_X_OFFSET = -48;
    private static final int HEALTHBAR_Y_OFFSET = -76;

    private Texture texture;
    private HealthBar healthBar;

    public TestBaseActor(TestBase base) {
        super(base);
        this.texture = new Texture("assets/bases/testbase.png");
        this.healthBar = new HealthBar(HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, base.getLocation().x - base.getBounds().radius, base.getLocation().y - base.getBounds().radius);
    }
    
    @Override
    public void drawHealthBar(ShapeRenderer shapeRenderer) {
        healthBar.draw(shapeRenderer, (int) (base.getLocation().x + HEALTHBAR_X_OFFSET), (int) (base.getLocation().y + HEALTHBAR_Y_OFFSET), base.getMaxHealth(), base.getHealth());
    }
}
