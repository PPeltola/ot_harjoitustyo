package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import domain.Unit;

public class TestEnemyActor extends UnitActor {
    
    private Texture texture;

    public TestEnemyActor(Unit enemy) {
        super(enemy);
        this.texture = new Texture("assets/enemies/testenemy.png");
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, unit.getXCoordinate(), unit.getYCoordinate());
    }
    
    @Override
    public void act(float delta) {
        unit.act(delta);
    }    
}
