package game.logic.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import game.domain.Base;
import game.domain.Obstacle;
import game.domain.Path;
import game.domain.Unit;

public class RunnerAi extends UnitAi {
    
    private Vector2 movingTo;
    private Vector2 currentMoveStart;
    private Path path;

    public RunnerAi(Unit enemy) {
        super(enemy);
        this.timePassedSinceLastAction = 0;
    }

    public void setStopped(boolean value) {
        stopped = value;
    }

    /**
     * Sets the current location which the unit is moving towards to the 
     * given one, unless the unit already happens to be at the location
     * 
     * @param to the desired location
     */
    public void setMovingTo(Vector2 to) {
        if (!enemy.getLocation().equals(to)) {
            setStopped(false);
            movingTo = to;
            Vector2 help = new Vector2(movingTo);
            help.sub(enemy.getLocation());
            currentMoveStart = new Vector2(help);
        }
    }

    public void setPath(Path newPath) {
        path = newPath;
        setMovingTo(path.getNextPoint());

    }
    
    /**
     * Adds delta time to timePassedSinceLastAction and takes a step if 
     * enough time has passed. Also checks if the unit has arrived at 
     * it's desired location and sets a new one if the path has any left
     * 
     * @param spawnPoint the starting location
     */
    @Override
    public void act(float delta) {
        timePassedSinceLastAction += delta;
        
        if (enemy.getLocation().equals(movingTo)) {
            setStopped(true);
            if (!path.ends()) {
                setMovingTo(path.getNextPoint());
                setStopped(false);
            }
        }
        
        if (!stopped) {
            while (timePassedSinceLastAction >= 1.0 / enemy.getSpeed()) {
                takeStep();
                timePassedSinceLastAction -= (1.0 / this.enemy.getSpeed());
            }
        }
    }
    
    /**
     * Calculates in which direction to take a step (one pixel) by comparing 
     * the current angle towards the desired location to the original one 
     * at the beginning of current move step, and thus tries to move in as 
     * straight of a line as possible
     */
    private void takeStep() {
        Vector2 currentMove = new Vector2(movingTo);
        currentMove = currentMove.sub(enemy.getLocation());
        if (currentMove.x == 0 || Math.abs(currentMove.y / currentMove.x) > Math.abs(currentMoveStart.y / currentMoveStart.x)) {
            enemy.move(0, MathUtils.clamp((int) (currentMove.y), -1, 1));
        } else {
            enemy.move(MathUtils.clamp((int) (currentMove.x), -1, 1), 0);
        }
    }
    
    /**
     * A callback if this unit collides with an obstacle
     * 
     * @param obstacle the obstacle hit
     */
    @Override
    public void collide(Obstacle obstacle) {
        setStopped(true);
    }
    
    /**
     * A callback if this unit collides with an another unit
     * 
     * @param unit the unit hit
     */
    @Override
    public void collide(Unit unit) {
    }
    
    /**
     * A callback if this unit overlaps with a base
     * 
     * @param base the base which overlaps this unit
     */
    @Override
    public void overlap(Base base) {
        base.loseHealth(enemy.getDamage());
        enemy.die();
    }

}
