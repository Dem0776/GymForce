package com.gymforce.controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class FXMLcompraPagosController {

	
	@FXML
    private AnchorPane Compras1;
	
	@FXML
    private AnchorPane Servicios1;

    @FXML
    private JFXTextField txtfolioproductosCompra;

    @FXML
    private JFXTextField txtrfcProductosCompra;

    @FXML
    private JFXTextField txtcantidadProductosCompra;

    @FXML
    private JFXTextField txtPrecioUnitarioProductos;

    @FXML
    private JFXTreeTableView<?> tblvwCompraProducto;

    @FXML
    private JFXComboBox<?> cmbFormaPagoProductos;

    @FXML
    private JFXComboBox<?> cmbProductoCompras;

    @FXML
    private JFXDatePicker dpfechaCompras;

    @FXML
    private JFXTextField txtfolioservicios;

    @FXML
    private JFXTextField txtMontoPagoServicio;

    @FXML
    private JFXTreeTableView<?> tblvwCompraServicios;

    @FXML
    private JFXComboBox<?> cmbServicioCompra;

    @FXML
    private JFXDatePicker dpfechaPagoServicio;

    @FXML
    private JFXTextField txtObservacionPagoServicio;

    @FXML
    void btnCancelarCompraProductos(ActionEvent event) {

    }

    @FXML
    void btnCancelarServicio(ActionEvent event) {

    }

    @FXML
    void btnComprarProductos(ActionEvent event) {

    }

    @FXML
    void btnComprarServicio(ActionEvent event) {

    }

    @FXML
    void btnagregarProductoCompra(ActionEvent event) {

    }

    @FXML
    void btnagregarProveedorCompra(ActionEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLagregarProveedor.fxml"));
       Compras1.getChildren().removeAll();
	    Compras1.getChildren().setAll(fxml);
    }

    @FXML
    void btnagregarServicio(ActionEvent event) throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLagregarServicio.fxml"));
		Servicios1.getChildren().removeAll();
	    Servicios1.getChildren().setAll(fxml);
    }

    @FXML
    void btnagregarServicioCompra(ActionEvent event) {

    }

    @FXML
    void btneliminaServicioCompra(ActionEvent event) {

    }

    @FXML
    void btneliminarProducto(ActionEvent event) {

    }

}
