package game.gui.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.domain.TestTower;

public class TestTowerActor extends TowerActor {
    
    private static final int TURRET_X_OFFSET = 4;
    private static final int TURRET_Y_OFFSET = 4;

    private Texture baseTexture;
    private Texture turretTexture;

    public TestTowerActor(TestTower tower) {
        super(tower);
        this.baseTexture = new Texture("src/main/resources/assets/towers/testtower.png");
        this.turretTexture = new Texture("src/main/resources/assets/towers/testtowerturret.png");
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(baseTexture, tower.getLocation().x - tower.getBounds().radius, tower.getLocation().y - tower.getBounds().radius);
        batch.draw(new TextureRegion(turretTexture), tower.getLocation().x - TURRET_X_OFFSET, tower.getLocation().y - TURRET_Y_OFFSET, TURRET_X_OFFSET, TURRET_Y_OFFSET, 8, 24, 1, 1, tower.getTurretAngle());
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
