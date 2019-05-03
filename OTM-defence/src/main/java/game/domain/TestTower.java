package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import game.logic.ai.TurretAi;

public class TestTower extends Tower {
    
    private static final int STARTING_DAMAGE = 200;
    private static final float STARTING_FIRE_RATE = 2;
    private static final int RADIUS = 16;
    private static final int STARTING_ATTACK_RANGE = 128;
    private static final int BASE_COST = 50;
    
    public TestTower(Vector2 location) {
        super(location);
        this.damage =  STARTING_DAMAGE;
        this.fireRate = STARTING_FIRE_RATE;
        this.bounds = new Circle(location, RADIUS);
        this.range = new Circle(location, STARTING_ATTACK_RANGE);
        this.cost = BASE_COST;
        this.ai = new TurretAi(this);
    }

    public static int getStartingAttackRange() {
        return STARTING_ATTACK_RANGE;
    }
    
    public static int getRadius() {
        return RADIUS;
    }

    public static int getBaseCost() {
        return BASE_COST;
    }
}
