//@Vitor Lisboa Nogueira @João Luiz Reolon

package trab_cg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Trab_CG extends Application {
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Pane root = FXMLLoader.load(getClass().getResource("CG_trab.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        //((Controle)root.load().getController()).carregou(); 
        stage.setScene(scene);
        stage.setTitle("!Lines Drawn");
        //stage.setMinHeight(500);
        //stage.setMinWidth(450);
        //stage.setResizable(false);
        stage.sizeToScene(); 
        stage.show(); 
    }    
}