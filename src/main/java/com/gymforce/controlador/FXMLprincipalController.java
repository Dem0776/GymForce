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
		principal.setCenter(clase);
    }
    
    @FXML
    void hbDietas(MouseEvent event) throws IOException {
    	AnchorPane dieta;
    	dieta = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLdieta.fxml"));
		principal.setCenter(dieta);
    }
    
    @FXML
    void hbEmpleados(MouseEvent event) throws IOException {
    	AnchorPane Empleados;
    	Empleados = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleados.fxml"));
		principal.setCenter(Empleados);
    }   

    @FXML
    void hbFinanzas(MouseEvent event) {

    }

    @FXML
    void hbMembresias(MouseEvent event) throws IOException {
    	AnchorPane Membresias;
    	Membresias = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLMembresias.fxml"));
		principal.setCenter(Membresias);
    }

    @FXML
    void hbMobiliario(MouseEvent event) {

    }

    @FXML
    void hbPlanesEntrenamiento(MouseEvent event) throws IOException {
    	AnchorPane planEntr;
    	planEntr = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLplanEntrenamiento.fxml"));
		principal.setCenter(planEntr);
    }

    @FXML
    void hbProductos(MouseEvent event) throws IOException {

    }

    @FXML
    void hbSalir(MouseEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void hbSocios(MouseEvent event) throws IOException {
    	AnchorPane planEntr;
		planEntr = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLSocios.fxml"));
		principal.setCenter(planEntr);
    }

    @FXML
    void hbVentas(MouseEvent event) {

    }
    
    @FXML
    void hideSlideBar(MouseEvent event) {
    }

}
