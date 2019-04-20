package logic;

import actors.BaseActor;
import actors.Map;
import actors.ObstacleActor;
import actors.TestEnemyActor;
import actors.UnitActor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import domain.Base;
import domain.Obstacle;
import domain.TestEnemy;
import gui.MissionScreen;
import domain.Unit;

public class MissionLogic {

    private MissionScreen screen;
    //private Array<Actor> actors;
    private Array<Unit> units;
    private Map map;
    private CollisionChecker collision;
    private double startingTime;
    private double timeSinceLastSpawn;

    public MissionLogic(MissionScreen screen) {
        this.screen = screen;
        this.units = new Array<>();
        //this.actors = new Array<>();
        this.map = new Map();
        this.collision = new CollisionChecker();
        this.startingTime = 0;
        this.timeSinceLastSpawn = 0;
    }

    public void initStage() {
        addActor(new BaseActor(new Base(512, 394)));
        addObstacle(new Obstacle());
        addUnit(new TestEnemyActor(new TestEnemy(0, 0)));
    }

    public void addActor(Actor actor) {
        //actors.add(actor);
        screen.addActorToStage(actor);
    }

    public void addUnit(UnitActor actor) {
        units.add(actor.getUnit());
        screen.addActorToStage(actor);
    }

    public void addObstacle(Obstacle obstacle) {
        map.addObstacle(obstacle);
    }

    public void update(float f) {
        startingTime += f;
        timeSinceLastSpawn += f;
        
        

        if (timeSinceLastSpawn >= 5) {
            addUnit(new TestEnemyActor(new TestEnemy(0, 0)));
            timeSinceLastSpawn = 0;
            System.out.println("hello");
        }

        for (int i = 0; i < units.size; i++) {
            Unit unit = units.get(i);
            
            for (ObstacleActor obstacleActor : map.getObstacles()) {
                if (collision.checkUnitCollisionWithObstacle(unit, obstacleActor.getObstacle())) {
                    unit.collide(obstacleActor.getObstacle());
                }
            }
            
            for (int j = i + 1; j < units.size; j++) {
                Unit other = units.get(j);
                
                if (other != unit && collision.checkUnitCollisionWithUnit(unit, other)) {
                    other.collide(unit);
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
