package game.map;

import com.badlogic.gdx.math.Vector2;
import game.domain.Obstacle;
import game.domain.Path;
import game.domain.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    private Map map;

    public MapLoader(Map map) {
        this.map = map;
    }

    public void loadMap(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(";");

                switch (fields[0]) {
                    case "Base":
                        loadBase(fields);
                        break;
                    case "Obstacle":
                        loadObstacle(fields);
                        break;
                    case "Path":
                        loadPath(fields);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBase(String[] fields) {
        switch (fields[1]) {
            case "TestBase":
                map.setBaseActor(new TestBase(new Vector2(Integer.parseInt(fields[2]), Integer.parseInt(fields[3]))));
                break;
        }
    }

    private void loadObstacle(String[] fields) {
        float[] vertices = new float[Integer.parseInt(fields[1])];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = Integer.parseInt(fields[i + 2]);
        }

        map.addObstacle(new Obstacle(vertices));
    }

    private void loadPath(String[] fields) {
        switch (fields[1]) {
            case "RunnerPath":
                Path path = new Path(new Vector2(Integer.parseInt(fields[2]), Integer.parseInt(fields[3])));
                
                for (int i = 4; i < fields.length; i += 2) {
                    path.addPointToPath(new Vector2(Integer.parseInt(fields[i]), Integer.parseInt(fields[i + 1])));
                }
                
                map.addPath(path);
                break;
        }
    }

}
