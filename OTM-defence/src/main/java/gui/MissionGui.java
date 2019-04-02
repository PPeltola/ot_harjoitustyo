package gui;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MissionGui extends Application {
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("OTM-defence");
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setResizable(false);
        
        //Canvas canvas = new Canvas(500, 500);
        
        Button buildTower = new Button("Build a tower!");
        
        BorderPane components = new BorderPane();
        
        //components.setCenter(canvas);
        components.setBottom(buildTower);
        
        Scene scene = new Scene(components);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(MissionGui.class);
    }
}
