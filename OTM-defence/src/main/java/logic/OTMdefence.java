package logic;

import com.badlogic.gdx.Game;
import gui.MissionScreen;

public class OTMdefence extends Game {

    @Override
    public void create() {
        setScreen(new MissionScreen(this));
    }
    
}
