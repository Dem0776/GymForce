package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Socio;
import com.gymforce.modelo.Venta;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class FXMLventasController implements Initializable{
	
	private ConexionMySQL conexion;
	Date fecha;
	private ObservableList<Socio> oblcmbSocio;
	
    @FXML
    private VBox vbprincipal;
    
    @FXML
    private JFXTextField txtNoVenta;

    @FXML
    private JFXTextField txtFecha;

    @FXML
    private JFXComboBox<Socio> cmbCliente;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtEntregado;

    @FXML
    private JFXTextField txtCambio;
    
    @FXML
    void btnAgregar(ActionEvent event) throws IOException {
    	if (!txtNoVenta.getText().equals("")) {
    		VBox ventas;
        	ventas = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLlistaProductos.fxml"));
        	vbprincipal.getChildren().clear();
        	vbprincipal.getChildren().add(ventas);
		}    	
    }

    @FXML
    void btnNuevaVenta(ActionEvent event) {
    	conexion.establecerConexion();
    	Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
    	txtFecha.setText(año + "-" + (mes+1) + "-" + dia);
    	txtNoVenta.setText(String.valueOf(Venta.obtenerNumeroVentas(conexion.getConnection())));
    	oblcmbSocio = FXCollections.observableArrayList();
    	Socio.llenarComboSocio1(conexion.getConnection(), oblcmbSocio);
    	cmbCliente.setItems(oblcmbSocio);
    	conexion.cerrarConexion();
    }

    @FXML
    void btnRealizaVenta(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
	}
}