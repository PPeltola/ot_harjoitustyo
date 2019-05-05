package game.domain;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import game.gui.BulletTrail;
import game.logic.ai.TowerAi;
import java.util.Iterator;

public abstract class Tower {
    
    protected TowerAi ai;
    protected Vector2 location;
    protected Circle bounds;
    protected Circle range;
    protected float fireRate;
    protected int damage;
    protected int cost;
    protected Array<BulletTrail> trails;
    protected float turretAngle;
    
    /**
     * Creates the Tower by setting location to the given one and initializing
     * the other variables.
     * 
     * @param location the desired location
     *
     * @return the created Tower
     */
    public Tower(Vector2 location) {
        this.location = location;
        this.trails = new Array<>();
        this.turretAngle = 0;
    }
    
    public void addTrail(BulletTrail trail) {
        trails.add(trail);
    }

    public Circle getBounds() {
        return bounds;
    }

    public int getDamage() {
        return damage;
    }

    public float getFireRate() {
        return fireRate;
    }

    public Vector2 getLocation() {
        return location;
    }

    public float getTurretAngle() {
        return turretAngle;
    }

    public void setTurretAngle(float turretAngle) {
        this.turretAngle = turretAngle;
    }

    public void act(float delta) {
        ai.act(delta);
    }

    public int getCost() {
        return cost;
    }

    public Circle getRange() {
        return range;
    }  

    public Array<BulletTrail> getTrails() {
        return trails;
    }
    
    /**
     * Updates the Tower's BulletTrails by removing the ones that have been 
     * marked as done and updating the rest.
     * 
     * @param f delta time
     */
    public void updateTrails(float f) {
        Iterator<BulletTrail> trailsIter = trails.iterator();
        BulletTrail trail;
        
        while (trailsIter.hasNext()) {
            trail = trailsIter.next();
            if (trail.isDone()) {
                trailsIter.remove();
            } else {
                trail.update(f);
            }
        }
    }
    
    public void checkTarget(Array<Unit> units) {
        ai.checkTarget(units);
    }
}
