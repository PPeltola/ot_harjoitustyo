package ai;

import domain.Obstacle;
import domain.Unit;

public class RunnerAi extends Ai {

    private float timePassedSinceLastAction;
    private boolean moving;

    public RunnerAi(Unit enemy) {
        super(enemy);
        this.timePassedSinceLastAction = 0;
        this.moving = true;
    }

    public void setMoving(boolean value) {
        this.moving = value;
    }

    @Override
    public void act(float delta) {
        timePassedSinceLastAction += delta;
        if (moving && timePassedSinceLastAction >= 1 / this.enemy.getSpeed()) {
            enemy.move(1, 1);
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
        System.out.println("helllooo");
    }

}
