package logic;

import gui.MissionGUI;
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
        
        new LwjglApplication(new OTMdefence(), cfg);
    }
    
}
