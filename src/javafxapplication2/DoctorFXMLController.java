/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author racli
 */
public class DoctorFXMLController implements Initializable {

    @FXML
    private Button Boton_Listo;

    @FXML
    private AnchorPane DatosPersonales;

    @FXML
    private ToggleGroup GrupoSexo;

    @FXML
    private AnchorPane PanelAgregar;
    @FXML
    private TextArea areatratamiento;
    @FXML
    private AnchorPane PanelEstadistica;

    @FXML
    private AnchorPane PanelInformacion;

    @FXML
    private AnchorPane PanelRegistros;

    @FXML
    private Button botAddPaciente;

    @FXML
    private Button botEstadist;

    @FXML
    private Button botRegistroDatos;

    @FXML
    private Spinner<Integer> edad;

    @FXML
    private Spinner<Integer> edadplus;
    @FXML
    private Spinner<Integer> A;
    @FXML
    private Spinner<Integer> B;
    @FXML
    private Spinner<Integer> C;
    @FXML
    private Label veredictoSCORAD;
    @FXML
    private Label veredicto;
    @FXML
    private Label marcador;

    @FXML
    private StackPane stackpanel;
    @FXML
    private CheckBox meses;

    @FXML
    private AnchorPane panelFallece;

    @FXML
    private AnchorPane panelieHospit;

    @FXML
    private AnchorPane panelieHospit1;

    SpinnerValueFactory<Integer> valoredad = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 18, 0);
    SpinnerValueFactory<Integer> valorplus = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 18, 0);
    SpinnerValueFactory<Integer> valorA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> valorB = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
    SpinnerValueFactory<Integer> valorC = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

    public FXMLDocumentController controlador = new FXMLDocumentController();

    public void cerrarpanel(ActionEvent event) {
        stackpanel.setVisible(false);
    }

    public void listo(ActionEvent event) {
        areatratamiento.setText(conexion.obtenertratamiento(edadplus.getValue()));
    }

    public void cerrarApp() {
        System.exit(0);
    }

   public void volvermenu() throws IOException, ClassNotFoundException{
      Parent root= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Stage stage=new Stage();
         stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.getIcons().add(new Image("design/unnamed4.png"));
        stage.setTitle("dermAtop");
        stage.setScene(new Scene(root));
        stage.show(); 
        
        botAddPaciente.getScene().getWindow().hide();
     }
    public void CambioPanel(ActionEvent event) {
        if (event.getSource() == botRegistroDatos) {
            marcador.setText("Cálculo del SCORAD");
            botRegistroDatos.getStyleClass().add("selected");
            botAddPaciente.getStyleClass().remove("selected");
            botEstadist.getStyleClass().remove("selected");
            PanelAgregar.setVisible(false);
            PanelEstadistica.setVisible(false);
            PanelInformacion.setVisible(false);
            valorplus.setValue(0);
            areatratamiento.setText("");
            PanelRegistros.setVisible(true);
        } else {
            if (event.getSource() == botEstadist) {
                marcador.setText("Medicina natural y tradicional");
                valorA.setValue(0);
                valorB.setValue(0);
                valorC.setValue(0);
                veredictoSCORAD.setText("");
                PanelInformacion.setVisible(false);
                botEstadist.getStyleClass().add("selected");
                botRegistroDatos.getStyleClass().remove("selected");
                botAddPaciente.getStyleClass().remove("selected");
                PanelEstadistica.setVisible(true);
                PanelRegistros.setVisible(false);
                PanelAgregar.setVisible(false);

            } else {
                marcador.setText("Diagnóstico de Dermatitis Atópica Infantil");
                limpiar();
                valorA.setValue(0);
                valorB.setValue(0);
                valorC.setValue(0);
                valorplus.setValue(0);
                veredictoSCORAD.setText("");
                areatratamiento.setText("");
                botAddPaciente.getStyleClass().add("selected");
                botRegistroDatos.getStyleClass().remove("selected");
                botEstadist.getStyleClass().remove("selected");
                PanelAgregar.setVisible(true);
                PanelRegistros.setVisible(false);
                PanelEstadistica.setVisible(false);
                PanelInformacion.setVisible(false);
            }
        }

    }

    Conexion conexion;
    String resp;

    public void obtenerSeleccionados(AnchorPane anchorPane) {
        List<String> selectedTexts = new ArrayList<>();
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    selectedTexts.add(checkBox.getText());
                }
            }
        }
        if (!selectedTexts.isEmpty()) {
            if (!resp.isEmpty()) {
                resp += "','";
            }
            resp += String.join("','", selectedTexts);
        }
    }

    public void vaciar(AnchorPane panel) {
        for (Node node : panel.getChildren()) {

            if (node instanceof Spinner) {
                Spinner spin = (Spinner) node;
                spin.getValueFactory().setValue(0);

            }
            if (node instanceof RadioButton) {
                RadioButton radio = (RadioButton) node;
                radio.setSelected(false);
            }
            if (node instanceof CheckBox) {
                CheckBox cbx = (CheckBox) node;
                cbx.setSelected(false);
            }
        }

    }

    public void limpiar() {

        vaciar(panelieHospit1);
        vaciar(panelieHospit);
        vaciar(panelFallece);
        vaciar(DatosPersonales);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //String file = "prolog/ProyectoDA.pl";
        conexion = new Conexion();
        valoredad.setValue(0);
        edad.setValueFactory(valoredad);
        valorplus.setValue(0);
        edadplus.setValueFactory(valorplus);
        valorA.setValue(0);
        A.setValueFactory(valorA);
        valorB.setValue(0);
        B.setValueFactory(valorB);
        valorC.setValue(0);
        C.setValueFactory(valorC);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), DatosPersonales);
        transition.setFromY(200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition.setToY(0); // Posición final en X
        transition.play();
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), panelieHospit);
        transition1.setFromY(200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition1.setToY(0); // Posición final en X
        transition1.play();
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(3), panelFallece);
        transition2.setFromY(200); // Posición inicial en X (fuera de la pantalla a la derecha)
        transition2.setToY(0); // Posición final en X
        transition2.play();

        TranslateTransition label = new TranslateTransition(Duration.seconds(3), marcador);

        label.setFromX(200); // Posición inicial obtenida desde Scene Builder
        label.setToX(0); // Posición final en el centro de la pantalla
        label.play();
        double initialX = -200; // Obtener la posición inicial del botón desde Scene Builder

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.8), botRegistroDatos);

        translateTransition.setFromX(initialX); // Posición inicial obtenida desde Scene Builder
        translateTransition.setToX(0); // Posición final en el centro de la pantalla
        translateTransition.play();
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2.4), botAddPaciente);

        translateTransition1.setFromX(initialX); // Posición inicial obtenida desde Scene Builder
        translateTransition1.setToX(0); // Posición final en el centro de la pantalla
        translateTransition1.play();
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2.8), botEstadist);

        translateTransition2.setFromX(initialX); // Posición inicial obtenida desde Scene Builder
        translateTransition2.setToX(0); // Posición final en el centro de la pantalla
        translateTransition2.play();

        edad.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue == 0) {
                    Boton_Listo.setDisable(true);
                } else {
                    Boton_Listo.setDisable(false);
                }
            }
        });

        meses.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    valoredad.setValue(0);
                    Boton_Listo.setDisable(false);
                } else {
                    Boton_Listo.setDisable(true);
                }
            }
        });
        Boton_Listo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stackpanel.setVisible(true);
                if (GrupoSexo.getSelectedToggle() != null) {
                    RadioButton selectedRadioButton = (RadioButton) GrupoSexo.getSelectedToggle();
                    resp += "'";
                    resp = selectedRadioButton.getText();
                    obtenerSeleccionados(panelieHospit);
                    obtenerSeleccionados(panelFallece);
                    obtenerSeleccionados(panelieHospit1);
                    veredicto.setText(conexion.obtenerdiagnostico(resp, edad.getValue()));

                } else {
                    veredicto.setText("Poca posibilidad de padecer dermatitis atopica,\nconsulte al especialista para mayor seguridad");
                }
                limpiar();
            }

        });

    }

    public void calcular(ActionEvent event) {

        veredictoSCORAD.setText(conexion.obtenergravedad(A.getValue(), B.getValue(), C.getValue()));
        veredictoSCORAD.setVisible(true);
    }

    public void informacion() {
        marcador.setText("INFORMACIÓN");
        botRegistroDatos.getStyleClass().remove("selected");
        botAddPaciente.getStyleClass().remove("selected");
        botEstadist.getStyleClass().remove("selected");
        PanelAgregar.setVisible(false);
        PanelEstadistica.setVisible(false);
        PanelInformacion.setVisible(true);
        PanelRegistros.setVisible(false);
    }

}
