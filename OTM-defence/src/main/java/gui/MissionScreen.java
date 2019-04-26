package gui;

import actors.BaseActor;
import actors.ObstacleActor;
import actors.UnitActor;
import map.Map;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import domain.Obstacle;
import domain.Path;
import java.util.Iterator;
import logic.MissionLogic;

public class MissionScreen implements Screen {
    
    private Game game;
    private MissionLogic logic;
    private Stage stage;
    private PolygonSpriteBatch polygonSpriteBatch;
    private ShapeRenderer shapeRenderer;

    public MissionScreen(Game game) {
        this.game = game;
        this.logic = new MissionLogic(this);
        this.stage = new Stage();
        this.polygonSpriteBatch = new PolygonSpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
    }
    
    public void addActorToStage(Actor actor) {
        stage.addActor(actor);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(66/255f, 66/255f, 66/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        
        if (!logic.isGameRunning()) {
            Gdx.app.exit();
        }
        
        logic.update(f);
        
        drawPaths();
        drawTerrain();
        
        stage.act();
        stage.draw();
        
        drawHealthBars();
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
    
    public void drawPaths() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0/255f, 255/255f, 255/255f, 32/255f);
        
        for (Path path : logic.getMap().getPaths()) {
            Iterator<Vector2> iterator = path.getPointIterator();
            Vector2 currentPoint = iterator.next();
            Vector2 nextPoint;
            
            while (iterator.hasNext()) {
                nextPoint = iterator.next();
                shapeRenderer.line(currentPoint, nextPoint);
                currentPoint = nextPoint;
            }
        }
        
        shapeRenderer.end();
    }
    
    public void drawTerrain() {
        polygonSpriteBatch.begin();
        
        for (ObstacleActor obstacleActor : logic.getMap().getObstacleActors()) {
            obstacleActor.draw(polygonSpriteBatch);
        }
        
        polygonSpriteBatch.end();
    }
    
    public void drawHealthBars() {
        logic.getBaseActor().drawHealthBar(shapeRenderer);
        
        for (UnitActor unitActor : logic.getUnitActors()) {
            unitActor.drawHealthBar(shapeRenderer);
        }
    }
}
