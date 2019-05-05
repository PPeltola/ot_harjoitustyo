package game.domain;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import java.util.Iterator;
import game.utils.QueueCloningUtil;

public class Path {

    private Vector2 startingPoint;
    private Queue<Vector2> points;
    
    /**
     * Constructs a new Path and initializes it's first point point to a copy of 
     * the given one.
     *
     * @param startingPoint the given starting point
     * 
     * @return the new Path
     */
    public Path(Vector2 startingPoint) {
        this.startingPoint = startingPoint.cpy();
        this.points = new Queue<>();
        addPointToPath(startingPoint);
    }
    
    /**
     * Constructs a new Path which is a copy of the given one.
     *
     * @param path the path to be copied
     * 
     * @return the new Path
     */
    public Path(Path path) {
        this.startingPoint = path.getSpawningPosition().cpy();
        this.points = QueueCloningUtil.copy(path.getPoints());
    }
    
    /**
     * Adds the given point to the end of this path
     *
     * @param point the point to be added
     */
    public void addPointToPath(Vector2 point) {
        points.addLast(point);
    }
    
    /**
     * Removes the point that is first in the queue and returns the 
     * following one.
     *
     * @return the next point
     */
    public Vector2 getNextPoint() {
        points.removeFirst();
        return points.first();
    }

    public Vector2 getSpawningPosition() {
        return startingPoint;
    }
    
    /**
     * Tells if the queue only contains one point, which means that 
     * the path ends.
     *
     * @return a boolean which tells if the path ends to the current point
     */
    public boolean ends() {
        return points.size == 1;
    }

    public Queue<Vector2> getPoints() {
        return points;
    }    
    
    public Iterator<Vector2> getPointIterator() {
        return points.iterator();
    }
}
