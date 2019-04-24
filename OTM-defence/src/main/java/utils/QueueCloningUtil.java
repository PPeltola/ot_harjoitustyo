package utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

public class QueueCloningUtil {
    
    public static Queue<Vector2> copy(Queue<Vector2> queue) {
        Queue<Vector2> ret = new Queue<>(queue.size);
        
        for (Vector2 point : queue) {
            ret.addLast(new Vector2(point));
        }
        
        return ret;
    }
    
}
