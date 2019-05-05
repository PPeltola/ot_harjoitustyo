package game.logic.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import game.domain.Tower;
import game.domain.Unit;
import game.gui.BulletTrail;

public class TurretAi extends TowerAi {

    private Unit target;
    private boolean hasTarget;

    public TurretAi(Tower tower) {
        this.tower = tower;
        this.hasTarget = false;
    }
    
    /**
     * Adds delta time to timePassedSinceLastAction, adjusts the turret angle 
     * if a target exists and fires at the target if enough time has passed
     * 
     * @param f delta time
     */
    @Override
    public void act(float f) {
        timePassedSinceLastAction += f;
        
        if (hasTarget) {
            tower.setTurretAngle(360 - (float) ((float) (180 * Math.atan2(target.getLocation().x - tower.getLocation().x, target.getLocation().y - tower.getLocation().y)) / Math.PI));
            
            if (timePassedSinceLastAction >= tower.getFireRate()) {
                target.takeDamage(tower.getDamage());
                timePassedSinceLastAction = 0;
                Vector2 turretPos = new Vector2(tower.getLocation());
                Vector2 turretHead = new Vector2(0, 20);
                turretHead.rotate(tower.getTurretAngle());
                turretPos.add(turretHead);
                tower.addTrail(new BulletTrail(turretPos, target.getLocation()));
            }
        }

    }
    
    /**
     * Checks through the given array of units and picks one in range if 
     * one exists and this Tower doesn't have a target yet. Also checks 
     * if current target is out of range and untargets it if so
     * 
     * @param units the array of existing units
     */
    @Override
    public void checkTarget(Array<Unit> units) {
        if (!hasTarget || target.getLocation().dst(tower.getLocation()) > tower.getRange().radius) {
            hasTarget = false;
            
            for (Unit unit : units) {
                if (tower.getRange().contains(unit.getLocation().x, unit.getLocation().y)) {
                    target = unit;
                    hasTarget = true;
                }
            }
        }
    }
}
