package game.domain;

import game.logic.ai.RunnerAi;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestEnemy extends Unit {

    private static final int MAX_HEALTH = 600;
    private static final int MAX_SPEED = 64;
    private static final int RADIUS = 8;
    private static final int DAMAGE = 2000;
    private static final int MONEY_ON_KILL = 10;

    /**
     * Creates the TestEnemy by calling the Unit constructor as well as
     * setting the default values to their defined static ones.
     * 
     * @param spawnPoint the starting location
     *
     * @return the created TestEnemy
     */
    public TestEnemy(Vector2 spawnPoint) {
        super(spawnPoint);
        this.health = MAX_HEALTH;
        this.speed = MAX_SPEED;
        this.damage = DAMAGE;
        this.moneyOnKill = MONEY_ON_KILL;
        this.bounds = new Circle(spawnPoint, RADIUS);
        this.ai = new RunnerAi(this);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }
}
