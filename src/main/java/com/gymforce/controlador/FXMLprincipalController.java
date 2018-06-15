package com.gymforce.controlador;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FXMLprincipalController {

    @FXML
    private BorderPane principal;
    
    @FXML
    private VBox vbSlideBar;

    @FXML
    void hbClases(MouseEvent event) throws IOException {
    	AnchorPane clase;
    	clase = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLclases.fxml"));
		principal.setRight(clase);
    }
    
    @FXML
    void hbDietas(MouseEvent event) {

    }
    
    @FXML
    void hbEmpleados(MouseEvent event) {

    }   

    @FXML
    void hbFinanzas(MouseEvent event) {

    }

    @FXML
    void hbMembresias(MouseEvent event) {

    }

    @FXML
    void hbMobiliario(MouseEvent event) {

    }

    @FXML
    void hbPlanesEntrenamiento(MouseEvent event) {

    }

    @FXML
    void hbProductos(MouseEvent event) {

    }

    @FXML
    void hbSalir(MouseEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void hbSocios(MouseEvent event) {

    }

    @FXML
    void hbVentas(MouseEvent event) {

    }
    
    @FXML
    void hideSlideBar(MouseEvent event) {
    }

}
