package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestBase extends Base {

    private static final int MAX_HEALTH = 10000;
    private static final int RADIUS = 64; 
    
    /**
     * Creates the TestBase by calling the Base constructor as well as
     * setting the default values to their defined static ones.
     * 
     * @param spawnPoint the base location
     *
     * @return the created TestBase
     */
    public TestBase(Vector2 spawnPoint) {
        super(spawnPoint);
        this.health = MAX_HEALTH;
        this.bounds = new Circle(location, RADIUS);
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
}
