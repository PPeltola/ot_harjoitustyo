package game.gui.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import game.domain.Tower;

public abstract class TowerActor extends Actor {
    
    protected Tower tower;

    public TowerActor(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void act(float delta) {
        tower.act(delta);
    }

    public Tower getTower() {
        return tower;
    }
    
}
