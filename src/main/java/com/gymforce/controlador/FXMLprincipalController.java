package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FXMLprincipalController implements Initializable{

    @FXML
    public BorderPane bpPrincipal;
    
    @FXML
    void btnClases(ActionEvent event) throws IOException {
    	AnchorPane clase;
    	clase = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLclases.fxml"));
    	bpPrincipal.setCenter(clase);
    }

    @FXML
    void btnDietas(ActionEvent event) throws IOException {
    	AnchorPane dieta;
    	dieta = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLdieta.fxml"));
    	bpPrincipal.setCenter(dieta);
    }

    @FXML
    void btnEmpleados(ActionEvent event) throws IOException {
    	AnchorPane Empleados;
    	Empleados = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleados.fxml"));
    	bpPrincipal.setCenter(Empleados);
    }

    @FXML
    void btnFinanzas(ActionEvent event) {

    }

    @FXML
    void btnInventario(ActionEvent event) throws IOException {
    	AnchorPane inventario;
    	inventario = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLinventario.fxml"));
    	bpPrincipal.setCenter(inventario);
    }

    @FXML
    void btnMembresias(ActionEvent event) throws IOException {
    	AnchorPane Membresias;
    	Membresias = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLMembresias.fxml"));
    	bpPrincipal.setCenter(Membresias);
    }

    @FXML
    void btnPagosServicios(ActionEvent event) throws IOException {
    	AnchorPane Compras;
    	Compras = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLcomprasPagos.fxml"));
    	bpPrincipal.setCenter(Compras);
    }

    @FXML
    void btnPlanesEntrenamiento(ActionEvent event) throws IOException {
    	AnchorPane planEntr;
    	planEntr = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLplanEntrenamiento.fxml"));
    	bpPrincipal.setCenter(planEntr);
    }

    @FXML
    void btnSalir(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void btnSocios(ActionEvent event) throws IOException {
    	AnchorPane planEntr;
		planEntr = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLSocios.fxml"));
		bpPrincipal.setCenter(planEntr);
    }

    @FXML
    void btnVentas(ActionEvent event) throws IOException {
    	VBox ventas;
    	ventas = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLventas.fxml"));
    	bpPrincipal.setCenter(ventas);
    }       

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
    
}
