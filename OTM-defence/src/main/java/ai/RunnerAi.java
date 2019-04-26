package ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import domain.Base;
import domain.Obstacle;
import domain.Path;
import domain.Unit;
import java.util.Stack;

public class RunnerAi extends Ai {

    private float timePassedSinceLastAction;
    private Vector2 movingTo;
    private Vector2 currentMoveStart;
    private float startingAngle;
    private boolean stopped;
    private Path path;

    public RunnerAi(Unit enemy) {
        super(enemy);
        this.timePassedSinceLastAction = 0;
    }

    public void setStopped(boolean value) {
        stopped = value;
    }

    public void setMovingTo(Vector2 to) {
        if (!enemy.getLocation().equals(to)) {
            setStopped(false);
            movingTo = to;
            Vector2 help = new Vector2(movingTo);
            help.sub(enemy.getLocation());
            currentMoveStart = new Vector2(help);
//            startingAngle = help.angle();
        }
    }

    public void setPath(Path newPath) {
        path = newPath;
        setMovingTo(path.getNextPoint());

    }

    @Override
    public void act(float delta) {
        timePassedSinceLastAction += delta;

        if (enemy.getLocation().equals(movingTo)) {
            setStopped(true);
            if (!path.ends()) {
//                System.out.println(path.getPoints().size);
                setMovingTo(path.getNextPoint());
                setStopped(false);
            }
        }

//        System.out.println(timePassedSinceLastAction + " " + this.enemy.getSpeed() + " " + (timePassedSinceLastAction >= 1 / this.enemy.getSpeed()));
        if (!stopped) {

            while (timePassedSinceLastAction >= 1.0 / enemy.getSpeed()) {
//                System.out.println(timePassedSinceLastAction + " " + 1.0 / enemy.getSpeed());
                Vector2 currentMove = new Vector2(movingTo);
                currentMove = currentMove.sub(enemy.getLocation());

                if (currentMove.x == 0 || Math.abs(currentMove.y / currentMove.x) > Math.abs(currentMoveStart.y / currentMoveStart.x)) {
                    enemy.move(0, MathUtils.clamp((int) (currentMove.y), -1, 1));
                } else {
                    enemy.move(MathUtils.clamp((int) (currentMove.x), -1, 1), 0);
                }

                timePassedSinceLastAction -= (1.0 / this.enemy.getSpeed());
            }
        }
    }

    @Override
    public void collide(Obstacle obstacle) {
        setStopped(true);
    }

    @Override
    public void collide(Unit unit) {
        //setStopped(true);
    }

    @Override
    public void overlap(Base base) {
        base.loseHealth(enemy.getDamage());
        enemy.die();
    }

}
