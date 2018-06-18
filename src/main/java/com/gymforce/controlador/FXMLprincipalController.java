package com.gymforce.controlador;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class FXMLprincipalController {

    @FXML
    public BorderPane principal;
    
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
    void hbPagosCompras(MouseEvent event) throws IOException {
    	AnchorPane Compras;
    	Compras = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLcomprasPagos.fxml"));
		principal.setCenter(Compras);
    }


    @FXML
    void hbPlanesEntrenamiento(MouseEvent event) throws IOException {
    	AnchorPane planEntr;
    	planEntr = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLplanEntrenamiento.fxml"));
		principal.setCenter(planEntr);
    }

    @FXML
<<<<<<< HEAD
    void hbProductos(MouseEvent event) throws IOException {

=======
    void hbInventario(MouseEvent event) throws IOException {
    	AnchorPane inventario;
    	inventario = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLinventario.fxml"));
		principal.setCenter(inventario);
>>>>>>> 4bcec089be9bad41f7e69837630f7b1e196ac8e1
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
    void hbVentas(MouseEvent event) throws IOException {
    	AnchorPane ventas;
    	ventas = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLventas.fxml"));
		principal.setCenter(ventas);
    	
    	
    }
    
    @FXML
    void hideSlideBar(MouseEvent event) {
    }

    public void regresar() throws IOException {
    	AnchorPane inventario;
    	inventario = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLinventario.fxml"));
		principal.setCenter(inventario);
   
    }
    
}
