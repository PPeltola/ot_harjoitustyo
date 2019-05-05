package game.logic;

import game.gui.actors.BaseActor;
import game.map.MapLoader;
import game.map.Map;
import game.gui.actors.ObstacleActor;
import game.gui.actors.TestEnemyActor;
import game.gui.actors.UnitActor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import game.domain.Obstacle;
import game.domain.Path;
import game.domain.TestEnemy;
import game.domain.Tower;
import game.gui.MissionScreen;
import game.domain.Unit;
import game.gui.actors.TowerActor;

public class MissionLogic {
    
    private static final int STARTING_MONEY = 200;
    private static final int SPAWNRATE = 5;
    private static final int SPAWNRATE_HALF_LIFE = 20;
    
    private MissionScreen screen;
    private MapLoader mapLoader;
    private Array<Unit> units;
    private Array<Tower> towers;
    private Array<UnitActor> unitActors;
    private Map map;
    private CollisionChecker collision;
    private double elapsedTime;
    private double timeSinceLastSpawn;
    private boolean gameRunning;
    private int money;

    public MissionLogic(MissionScreen screen) {
        this.screen = screen;
        this.units = new Array<>();
        this.unitActors = new Array<>();
        this.towers = new Array<>();
        this.map = new Map();
        this.mapLoader = new MapLoader(map);
        this.collision = new CollisionChecker();
        this.elapsedTime = 0;
        this.timeSinceLastSpawn = 0;
        this.gameRunning = true;
        this.money = STARTING_MONEY;
    }

    public void initStage(String mapName) {
        mapLoader.loadMap(getClass().getResourceAsStream("/assets/maps/" + mapName));
        addActor(map.getBaseActor());
    }

    private void addActor(Actor actor) {
        screen.addActorToStage(actor);
    }
    
    public void loseMoney(int amount) {
        if (money >= amount) {
            money -= amount;
        }
    }

    public void addUnit(UnitActor actor) {
        units.add(actor.getUnit());
        unitActors.add(actor);
        addActor(actor);
    }
    
    public void placeTower(TowerActor towerActor) {
        if (terrainContainsCircle(towerActor.getTower().getBounds()) && !circleOverlapsTowers(towerActor.getTower().getBounds()) && canAfford(towerActor.getTower().getCost())) {
            towers.add(towerActor.getTower());
            loseMoney(towerActor.getTower().getCost());
            screen.addActorToStage(towerActor);
        }
    }
    
    public void removeUnit(UnitActor unitActor) {
        units.removeValue(unitActor.getUnit(), true);
        unitActors.removeValue(unitActor, true);
    }

    public void addObstacle(Obstacle obstacle) {
        map.addObstacle(obstacle);
    }

    public void update(float f) {
        elapsedTime += f;
        timeSinceLastSpawn += f;
        
        checkGameOver();

        if (timeSinceLastSpawn >= SPAWNRATE / ((2 * elapsedTime) / SPAWNRATE_HALF_LIFE)) {
            spawnEnemy();
        }
        
        updateTowers(f);
        
        for (int i = 0; i < unitActors.size; i++) {
            Unit unit = unitActors.get(i).getUnit();
            
            checkMapCollision(unit);

            checkUnitCollision(i, unit);
            
            checkUnitAlive(unit, i);
        }
    }

    private void updateTowers(float f) {
        for (Tower tower : towers) {
            tower.checkTarget(units);
            tower.updateTrails(f);
        }
    }

    private void checkGameOver() {
        if (!map.getBaseActor().getBase().isAlive()) {
            gameRunning = false;
        }
    }

    private void checkUnitAlive(Unit unit, int i) {
        if (!unit.isAlive()) {
            gainMoney(unit.getMoneyOnKill());
            removeUnit(unitActors.get(i));
        }
    }

    private void checkUnitCollision(int i, Unit unit) {
        for (int j = i + 1; j < units.size; j++) {
            Unit other = units.get(j);
            
            if (other != unit && collision.checkUnitCollisionWithUnit(unit, other)) {
                other.collide(unit);
                unit.collide(other);
            }
        }
    }

    private void checkMapCollision(Unit unit) {
        if (collision.checkUnitCompleteOverlapWithBase(unit, map.getBaseActor().getBase())) {
            unit.overlap(map.getBaseActor().getBase());
        }
        
        for (ObstacleActor obstacleActor : map.getObstacleActors()) {
            if (collision.checkUnitCollisionWithObstacle(unit, obstacleActor.getObstacle())) {
                unit.collide(obstacleActor.getObstacle());
            }
        }
    }

    private void spawnEnemy() {
        Path path = map.getPath((int) elapsedTime % 3);
        TestEnemy enemy = new TestEnemy(path.getSpawningPosition());
        enemy.setPath(path);
        addUnit(new TestEnemyActor(enemy));
        timeSinceLastSpawn = 0;
    }
    
    public boolean canAfford(int cost) {
        return money - cost >= 0;
    }

    public Map getMap() {
        return map;
    }

    public Array<Tower> getTowers() {
        return towers;
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
    
    public boolean terrainContainsCircle(Circle circle) {
        for (ObstacleActor obstacleActor : map.getObstacleActors()) {
            if (collision.polygonContainsCircle(obstacleActor.getObstacle().getPolygon(), circle)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean circleOverlapsTowers(Circle circle) {
        for (Tower tower : towers) {
            if (circle.overlaps(tower.getBounds())) {
                return true;
            }
        }
        return false;
    }
    
    public void gainMoney(int amount) {
        if (amount > 0) {
            this.money += amount;
        } 
    }

    public int getMoney() {
        return money;
    }
    
    
}
