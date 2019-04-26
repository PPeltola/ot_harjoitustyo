package domain;

import actors.HealthBar;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class TestBase extends Base {

    private final int MAX_HEALTH = 10000;

    public TestBase(int x, int y) {
        super(x, y);
        this.health = MAX_HEALTH;
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }
}
