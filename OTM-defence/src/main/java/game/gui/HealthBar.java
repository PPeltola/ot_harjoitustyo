package game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class HealthBar {
    
    private int width;
    private int thickness;

    public HealthBar(int width, int thickness) {
        this.width = width;
        this.thickness = thickness;
    }
    
    public void draw(ShapeRenderer shapeRenderer, int x, int y, int max, int curr) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, thickness);
        
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, ((float) curr / max) * width, thickness);
        
        shapeRenderer.end();
    }

    
    
    
}
