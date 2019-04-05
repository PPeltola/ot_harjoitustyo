package gui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import domain.Base;
import domain.TestEnemy;

public class MissionGUI implements ApplicationListener {

    private SpriteBatch batch;
    private Stage stage;
    
    @Override
    public void create() {
        stage = new Stage();
        
        BaseActor base = new BaseActor(new Base(512, 394));
        EnemyActor enemy = new EnemyActor(new TestEnemy(0, 0));
        
        stage.addActor(base);
        stage.addActor(enemy);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
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

}
