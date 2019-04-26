package logic;

import actors.BaseActor;
import map.MapLoader;
import map.Map;
import actors.ObstacleActor;
import actors.TestEnemyActor;
import actors.UnitActor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import domain.Obstacle;
import domain.Path;
import domain.TestEnemy;
import gui.MissionScreen;
import domain.Unit;
import java.io.File;

public class MissionLogic {

    private MissionScreen screen;
    private MapLoader mapLoader;
    private Array<Unit> units;
    private Array<UnitActor> unitActors;
    private Map map;
    private CollisionChecker collision;
    private double startingTime;
    private double timeSinceLastSpawn;
    private boolean gameRunning;

    public MissionLogic(MissionScreen screen) {
        this.screen = screen;
        this.units = new Array<>();
        this.unitActors = new Array<>();
        this.map = new Map();
        this.mapLoader = new MapLoader(map);
        this.collision = new CollisionChecker();
        this.startingTime = 0;
        this.timeSinceLastSpawn = 0;
        this.gameRunning = true;
    }

    public void initStage(String mapName) {
        mapLoader.loadMap(new File("maps/" + mapName));
        addActor(map.getBaseActor());

        Path path = map.getPath(0);
        TestEnemy enemy = new TestEnemy(path.getSpawningPosition());
        enemy.setPath(path);
        addUnit(new TestEnemyActor(enemy));
    }

    public void addActor(Actor actor) {
        screen.addActorToStage(actor);
    }

    public void addUnit(UnitActor actor) {
        units.add(actor.getUnit());
        unitActors.add(actor);
        addActor(actor);
    }
    
    public void removeUnit (Unit unit) {
        units.removeValue(unit, true);
    }

    public void addObstacle(Obstacle obstacle) {
        map.addObstacle(obstacle);
    }

    public void update(float f) {
        startingTime += f;
        timeSinceLastSpawn += f;
        
        if (!map.getBaseActor().getBase().isAlive()) {
            gameRunning = false;
        }

        if (timeSinceLastSpawn >= 5) {
            Path path = map.getPath((int)startingTime % 3);
            TestEnemy enemy = new TestEnemy(path.getSpawningPosition());
            enemy.setPath(path);
            addUnit(new TestEnemyActor(enemy));
            timeSinceLastSpawn = 0;
        }
        
        for (int i = 0; i < units.size; i++) {
            Unit unit = units.get(i);
            
            if (collision.checkUnitCompleteOverlapWithBase(unit, map.getBaseActor().getBase())) {
                unit.overlap(map.getBaseActor().getBase());
            }

            for (ObstacleActor obstacleActor : map.getObstacleActors()) {
                if (collision.checkUnitCollisionWithObstacle(unit, obstacleActor.getObstacle())) {
                    unit.collide(obstacleActor.getObstacle());
                }
            }

            for (int j = i + 1; j < units.size; j++) {
                Unit other = units.get(j);

                if (other != unit && collision.checkUnitCollisionWithUnit(unit, other)) {
                    other.collide(unit);
                    unit.collide(other);
                }
            }
            
            if (!unit.isAlive()) {
                removeUnit(unit);
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public void drawHealthBars(ShapeRenderer shapeRenderer) {
        map.getBaseActor().drawHealthBar(shapeRenderer);
    }
    
    public boolean isGameRunning() {
        return gameRunning;
    }

    public Array<UnitActor> getUnitActors() {
        return unitActors;
    }
    
    public BaseActor getBaseActor() {
        return map.getBaseActor();
    }
}
