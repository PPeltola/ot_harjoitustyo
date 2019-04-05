package gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import domain.Enemy;

public class EnemyActor extends Actor {
    
    private Enemy enemy;
    private Texture texture;
    private float timePassed;

    public EnemyActor(Enemy e) {
        this.enemy = e;
        this.texture = new Texture("assets/enemies/testenemy.png");
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, enemy.getXCoordinate() - enemy.getRadius(), enemy.getYCoordinate() - enemy.getRadius());
    }
    
    @Override
    public void act(float delta) {
        timePassed += delta;
        
        if (timePassed >= 1/enemy.getSpeed()) {
            enemy.move(1, 1);
            timePassed -= 1/enemy.getSpeed();
        }
    }
    
}
