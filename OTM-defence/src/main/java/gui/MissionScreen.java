package gui;

import actors.Map;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import domain.Obstacle;
import logic.MissionLogic;

public class MissionScreen implements Screen {
    
    private Game game;
    private MissionLogic logic;
    private Stage stage;
    

    public MissionScreen(Game game) {
        this.game = game;
        this.logic = new MissionLogic(this);
        this.stage = new Stage();
    }
    
    public void addActorToStage(Actor actor) {
        stage.addActor(actor);
    }

    @Override
    public void render(float f) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        logic.update(f);
        
        

        stage.act();
        
        logic.getMap().draw();
        stage.draw();
    }

    @Override
    public void show() {
        logic.initStage();
    }

    @Override
    public void resize(int i, int i1) {
        }

    @Override
    public void pause() {
        }

    @Override
    public void resume() {
        }

    @Override
    public void hide() {
        }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
