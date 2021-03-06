package game.launcher;

import game.launcher.OTMdefence;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {
    
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "OTM-defence";
        cfg.useGL30 = false;
        cfg.width = 1024;
        cfg.height = 768;
        cfg.resizable = false;
        cfg.forceExit = true;
        
        new LwjglApplication(new OTMdefence(), cfg);
    }
    
}
