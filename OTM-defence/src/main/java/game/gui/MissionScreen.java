package game.gui;

import game.gui.actors.ObstacleActor;
import game.gui.actors.UnitActor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.domain.Path;
import game.domain.TestTower;
import game.domain.Tower;
import game.gui.actors.TestTowerActor;
import java.util.Iterator;
import game.logic.MissionLogic;

public class MissionScreen implements Screen {

    private Game game;
    private MissionLogic logic;
    private Stage stage;
    private PolygonSpriteBatch polygonSpriteBatch;
    private ShapeRenderer shapeRenderer;
    private Image constructTowerButton;
    private boolean placingTower;
    private boolean canPlaceTower;
    private int towerRadius;
    private int towerRange;
    private Circle currentTowerOutline;
    private BitmapFont font;

    public MissionScreen(Game game) {
        this.game = game;
        this.logic = new MissionLogic(this);
        this.stage = new Stage();
        this.polygonSpriteBatch = new PolygonSpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.placingTower = false;
    }

    public void addActorToStage(Actor actor) {
        stage.addActor(actor);
    }

    public void initUI() {
        font = new BitmapFont();
        font.setColor(255 / 255f, 225 / 255f, 50 / 255f, 255 / 255f);
        
        constructTowerButton = new Image(new Texture("assets/buttons/buildturret.png"));
        constructTowerButton.setBounds(20, 688, 128, 64);

        constructTowerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (logic.canAfford(TestTower.getBaseCost())) {
                    placingTower = true;
                    towerRadius = TestTower.getRadius();
                    towerRange = TestTower.getStartingAttackRange();

                    if (placingTower) {
                        Gdx.input.setInputProcessor(new InputAdapter() {
                            public boolean touchDown(int x, int y, int pointer, int button) {
                                if (button == Input.Buttons.LEFT) {
                                    if (!(new Rectangle(20, 688, 128, 64).contains(x, 768 - y)) && placingTower && canPlaceTower) {
                                        logic.placeTower(new TestTowerActor(new TestTower(new Vector2(Gdx.input.getX(), 768 - Gdx.input.getY()))));
                                        
                                    }
                                    placingTower = false;
                                    Gdx.input.setInputProcessor(stage);
                                    return true;
                                }
                                return false;
                            }
                        });
                    }
                }
            }
        });
        addActorToStage(constructTowerButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(66 / 255f, 66 / 255f, 66 / 255f, 1);
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

        drawBulletTrails();
        drawHealthBars();

        if (placingTower) {
            drawTowerOutline();
        }
        
        drawMoneyLeft();
    }

    @Override
    public void show() {
        logic.initStage("test_map.tdmap");
        initUI();
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

    public void drawTowerOutline() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(100 / 255f, 100 / 255f, 100 / 255f, 64 / 255f);
        shapeRenderer.circle(Gdx.input.getX(), 768 - Gdx.input.getY(), towerRange);

        currentTowerOutline = new Circle(Gdx.input.getX(), 768 - Gdx.input.getY(), towerRadius);

        if (logic.terrainContainsCircle(currentTowerOutline) && !logic.circleOverlapsTowers(currentTowerOutline)) {
            shapeRenderer.setColor(0, 1, 0, 64 / 255f);
            canPlaceTower = true;
        } else {
            shapeRenderer.setColor(1, 0, 0, 64 / 255f);
            canPlaceTower = false;
        }

        shapeRenderer.circle(Gdx.input.getX(), 768 - Gdx.input.getY(), towerRadius);
        shapeRenderer.end();
    }
    
    public void drawBulletTrails() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Tower tower : logic.getTowers()) {
            for (BulletTrail trail : tower.getTrails()) {
                trail.draw(shapeRenderer);
            }
        }
        shapeRenderer.end();
    }

    public void drawPaths() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0 / 255f, 255 / 255f, 255 / 255f, 32 / 255f);

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
    
    public void drawMoneyLeft() {
        polygonSpriteBatch.begin();
        font.draw(polygonSpriteBatch, "$" + logic.getMoney(), 20, 30);
        polygonSpriteBatch.end();
    }
}
