package gui;

import actors.TestEnemyActor;
import actors.BaseActor;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import domain.Base;
import domain.Obstacle;
import domain.TestEnemy;
import actors.Map;

public class MissionGUI implements ApplicationListener {

    private Stage stage;
    private Map map;
    
    public void addActor(Actor actor) {
        stage.addActor(actor);
    }
    
    public void addObstacle (Obstacle obstacle) {
        map.addObstacle(obstacle);
    }

    @Override
    public void create() {
        map = new Map();
        map.addObstacle(new Obstacle());
        
        stage = new Stage();

        BaseActor base = new BaseActor(new Base(512, 394));
        TestEnemyActor enemy = new TestEnemyActor(new TestEnemy(0, 0));

        stage.addActor(base);
        stage.addActor(enemy);
    }

    @Override
    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        map.draw();

        stage.act();
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int i, int i1) {
    }

}
