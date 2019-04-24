package domain;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import java.util.Iterator;
import utils.QueueCloningUtil;

public class Path {

    private Vector2 spawningPosition;
    private Queue<Vector2> points;

    public Path(int x, int y) {
        this.spawningPosition = new Vector2(x, y);
        this.points = new Queue<>();
        addPointToPath(spawningPosition);
    }

    public Path(Path path) {
        this.spawningPosition = path.getSpawningPosition().cpy();
        this.points = QueueCloningUtil.copy(path.getPoints());
        addPointToPath(spawningPosition);
    }

    public void addPointToPath(Vector2 point) {
        points.addLast(point);
    }
    
    public Vector2 getNextPoint() {
        points.removeFirst();
        return points.first();
    }

    public Vector2 getSpawningPosition() {
        return spawningPosition;
    }
    
    public boolean isEmpty() {
        return points.isEmpty();
    }

    public Queue<Vector2> getPoints() {
        return points;
    }    
    
    public Iterator<Vector2> getPointIterator() {
        return points.iterator();
    }
}
