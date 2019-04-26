package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import domain.Unit;

public class TestEnemyActor extends UnitActor {
    
    private Texture texture;
    private HealthBar healthBar;

    public TestEnemyActor(Unit enemy) {
        super(enemy);
        this.texture = new Texture("assets/enemies/testenemy.png");
        this.healthBar = new HealthBar(14, 2);
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
        if (unit.getTimeSinceDamageTaken() <= 5) {
            healthBar.draw(shapeRenderer, (int) (unit.getLocation().x - 8), (int) (unit.getLocation().y - unit.getBounds().radius - 4), unit.getMaxHealth(), unit.getHealth());
        }
    }
}
