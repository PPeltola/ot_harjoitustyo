package ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import domain.Obstacle;
import domain.Unit;

public class RunnerAi extends Ai {

    private float timePassedSinceLastAction;
    private Vector2 movingTo;
    private Vector2 currentMoveStart;
    private float startingAngle;
    private boolean moving;

    public RunnerAi(Unit enemy) {
        super(enemy);
        this.timePassedSinceLastAction = 0;
        setMovingTo(new Vector2(100, 100));
    }

    public void setMoving(boolean value) {
        moving = value;
    }

    public void setMovingTo(Vector2 to) {
        if (!enemy.getLocation().equals(to)) {
            setMoving(true);
            movingTo = to;
            currentMoveStart = enemy.getLocation();
            
            Vector2 help = new Vector2(movingTo);
            help.sub(enemy.getLocation());
            
            startingAngle = help.angle();
        }
    }

    @Override
    public void act(float delta) {
        timePassedSinceLastAction += delta;
        if (moving && timePassedSinceLastAction >= 1 / this.enemy.getSpeed() && !enemy.getLocation().equals(movingTo)) {
            
            Vector2 currentMove = new Vector2(movingTo);
            currentMove = currentMove.sub(enemy.getLocation());
            
//            System.out.println(currentMove);
//            System.out.println(startingAngle);
//            System.out.println(currentMove.angle());
            
            if (startingAngle >= currentMove.angle()) {
                enemy.move(MathUtils.clamp((int)(movingTo.x - enemy.getLocation().x), -1, 1), 0);
            } else {
                enemy.move(0, MathUtils.clamp((int)(movingTo.y - enemy.getLocation().y), -1, 1));
            }
            
            timePassedSinceLastAction -= 1 / enemy.getSpeed();
        }
    }

    @Override
    public void collide(Obstacle obstacle) {
        setMoving(false);
    }

    @Override
    public void collide(Unit unit) {
        setMoving(false);
    }

}
