package game.gui.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.domain.Unit;

public class TestEnemyActor extends UnitActor {
    
    private static final int HEALTHBAR_WIDTH = 14;
    private static final int HEALTHBAR_HEIGHT = 2;
    private static final int HEALTHBAR_X_OFFSET = -7;
    private static final int HEALTHBAR_Y_OFFSET = -12;
    private static final int HEALTHBAR_TIME_VISIBLE = 5;
    
    private Texture texture;
    private HealthBar healthBar;

    public TestEnemyActor(Unit enemy) {
        super(enemy);
        this.texture = new Texture("assets/enemies/testenemy.png");
        this.healthBar = new HealthBar(HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT);
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, unit.getLocation().x - unit.getBounds().radius, unit.getLocation().y - unit.getBounds().radius);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
    }
    
    public void drawHealthBar(ShapeRenderer shapeRenderer) {
        if (unit.getTimeSinceDamageTaken() <= HEALTHBAR_TIME_VISIBLE) {
            healthBar.draw(shapeRenderer, (int) (unit.getLocation().x + HEALTHBAR_X_OFFSET), (int) (unit.getLocation().y + HEALTHBAR_Y_OFFSET), unit.getMaxHealth(), unit.getHealth());
        }
    }
}
