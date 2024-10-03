/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author racli
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button bot_login;

    @FXML
    private Label login_fot;

    @FXML
    private Label usuarioADM;
     @FXML
    private TextArea areainfo;
    @FXML
    private Label usuarioDR;
    @FXML
    private Hyperlink cerrarventana;
 public void close(ActionEvent event) {
areainfo.setVisible(false);
cerrarventana.setVisible(false);
    }
 public void info(ActionEvent event) {
areainfo.setVisible(true);
cerrarventana.setVisible(true);
    }
    
     public void entrar() throws IOException, ClassNotFoundException{
      Parent root= FXMLLoader.load(getClass().getResource("DoctorFXML.fxml"));
           Stage stage=new Stage();
         stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.getIcons().add(new Image("design/unnamed4.png"));
        stage.setTitle("dermAtop");
        stage.setScene(new Scene(root));
        stage.show(); 
        
        bot_login.getScene().getWindow().hide();
     }
    public void salir() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     DropShadow ds=new DropShadow();
        ds.setColor(Color.BLUE);
        ds.setRadius(5);
        ds.setOffsetX(5);
        
              TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5), usuarioADM);
        transition.setFromX(-200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition.setToX(0); // Posición final en X
        transition.play();
          TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1.5),usuarioDR );
        transition1.setFromX(200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition1.setToX(0); // Posición final en X
        transition1.play();
         TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1.5), login_fot);
        transition2.setFromY(-200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition2.setToY(0); // Posición final en X
        transition2.play();
            
   
    }
    

}
