package gui;

import actors.Map;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import domain.Obstacle;
import logic.MissionLogic;

public class MissionScreen implements Screen {
    
    private Game game;
    private MissionLogic logic;
    private Stage stage;
    private PolygonSpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public MissionScreen(Game game) {
        this.game = game;
        this.logic = new MissionLogic(this);
        this.stage = new Stage();
        this.batch = new PolygonSpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
    }
    
    public void addActorToStage(Actor actor) {
        stage.addActor(actor);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(66/255f, 66/255f, 66/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        logic.update(f);

        stage.act();
        
        logic.getMap().drawPaths(shapeRenderer);
        logic.getMap().drawTerrain(batch);
        
        stage.draw();
    }

    @Override
    public void show() {
        logic.initStage("test_map.tdmap");
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
