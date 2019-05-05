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
import java.io.InputStream;
import org.apache.commons.io.FileUtils;

public class MapLoader {

    private Map map;

    public MapLoader(Map map) {
        this.map = map;
    }

    public void loadMap(InputStream inputStream) {
        try {
            File file = new File("temp");
            FileUtils.copyInputStreamToFile(inputStream, file);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(";");

                handleLine(fields);
            }
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLine(String[] fields) {
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
