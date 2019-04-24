package ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
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
            if (!path.isEmpty()) {
                setMovingTo(path.getNextPoint());
                setStopped(false);
            }
        }
        
        if (!stopped && timePassedSinceLastAction >= 1 / this.enemy.getSpeed()) {
            
            Vector2 currentMove = new Vector2(movingTo);
            currentMove = currentMove.sub(enemy.getLocation());
            
//            System.out.println(currentMove);
            System.out.println(currentMoveStart.angle());
            System.out.println(currentMove.angle());
            System.out.println(currentMoveStart.angle(currentMove));
            
            if (currentMove.angle(currentMoveStart) < 0) {
                enemy.move(MathUtils.clamp((int)(movingTo.x - enemy.getLocation().x), -1, 1), 0);
            } else {
                enemy.move(0, MathUtils.clamp((int)(movingTo.y - enemy.getLocation().y), -1, 1));
            }
            
            timePassedSinceLastAction = 0;
        }
    }

    @Override
    public void collide(Obstacle obstacle) {
        setStopped(true);
    }

    @Override
    public void collide(Unit unit) {
        setStopped(true);
    }

}