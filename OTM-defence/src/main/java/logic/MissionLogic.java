package logic;

import actors.TestBaseActor;
import actors.Map;
import actors.ObstacleActor;
import actors.TestEnemyActor;
import actors.UnitActor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import domain.TestBase;
import domain.Obstacle;
import domain.Path;
import domain.TestEnemy;
import gui.MissionScreen;
import domain.Unit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MissionLogic {

    private MissionScreen screen;
    private MapLoader mapLoader;
    private Array<Unit> units;
    private Map map;
    private CollisionChecker collision;
    private double startingTime;
    private double timeSinceLastSpawn;

    public MissionLogic(MissionScreen screen) {
        this.screen = screen;
        this.units = new Array<>();
        this.map = new Map();
        this.mapLoader = new MapLoader(map);
        this.collision = new CollisionChecker();
        this.startingTime = 0;
        this.timeSinceLastSpawn = 0;
    }

    public void initStage(String mapName) {
        mapLoader.loadMap(new File("maps/" + mapName));
        addActor(map.getBase());

        Path path = map.getPath(2);
        TestEnemy enemy = new TestEnemy(path.getSpawningPosition());
        enemy.setPath(path);
        addUnit(new TestEnemyActor(enemy));
    }

    public void addActor(Actor actor) {
        screen.addActorToStage(actor);
    }

    public void addUnit(UnitActor actor) {
        units.add(actor.getUnit());
        addActor(actor);
    }

    public void addObstacle(Obstacle obstacle) {
        map.addObstacle(obstacle);
    }

    public void update(float f) {
        startingTime += f;
        timeSinceLastSpawn += f;

//        if (timeSinceLastSpawn >= 5) {
//            Path path = map.getPath((int)startingTime % 3);
//            TestEnemy enemy = new TestEnemy(path.getSpawningPosition());
//            enemy.setPath(path);
//            addUnit(new TestEnemyActor(enemy));
//            timeSinceLastSpawn = 0;
//        }
        for (int i = 0; i < units.size; i++) {
            Unit unit = units.get(i);

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

//            for (Unit other : units) {
//                if (other != unit && collision.checkUnitCollisionWithUnit(unit, other)) {
//                    unit.collide(other);
//                }
//            }
        }
    }

    public Map getMap() {
        return map;
    }
}
