/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author racli
 */
public class dermAtop extends Application {
   
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       stage.getIcons().add(new Image("design/unnamed4.png"));
        Scene scene = new Scene(root);
        stage.setMinWidth(400);
        stage.setMinHeight(700);
        stage.setTitle("dermAtop");
        stage.setScene(scene);
        stage.show();
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    
    }
    
}
