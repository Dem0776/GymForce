package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Forma_pago;

import com.gymforce.modelo.Membresia;

import com.gymforce.modelo.Producto;
import com.gymforce.modelo.Venta;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLventasController implements Initializable {
	private ConexionMySQL conexion;
	private ObservableList<Producto> listaProducto;
	private ObservableList<Membresia> listaMembresia;
	private ObservableList<Clase> listaClases;
	private ObservableList<Venta> listaCarrito;
	
    @FXML
    private TableView<Membresia> tblMembresias;

    @FXML
    private TableColumn<Membresia,String> clmnMembresia;

    @FXML
    private TableColumn<Membresia,Double> clmnCostoMembresia;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, String> clmnProducto;

    @FXML
    private TableColumn<Producto, Double> clmnCostoProducto;

    @FXML
    private TableView<Clase> tblClases;

    @FXML
    private TableColumn<Clase,String> clmnClases;

    @FXML
    private TableColumn<Clase,Double> clmnCostosClases;

    @FXML
    private TableView<Venta> tblCarrito;

    @FXML
    private TableColumn<Venta, Integer> clmnCantidadCarrito;

    @FXML
    private TableColumn<Producto,String> clmnProductoCarrito;

    @FXML
    private TableColumn<Producto,Double> clmnPreciounitCarrito;

    @FXML
    private TableColumn<?,?> clmnPrecioTotalCarrito;

    @FXML
    private JFXTextField txtRFCcliente;

    @FXML
    private JFXComboBox<Forma_pago> cmbFormaPago;

    @FXML
    private JFXTextField txtTotalCompra;

    @FXML
    void btnAgregarClases(ActionEvent event) {

    }

    @FXML
    void btnAgregarMembresias(ActionEvent event) {

    }

    @FXML
    void btnAgregarProductos(ActionEvent event) {

    }

    @FXML
    void btnCancelarVentaProductos(ActionEvent event) {

    }

    @FXML
    void btnComprarProductos(ActionEvent event) {

    }

    @FXML
    void btnRetirarCarrito(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();

		conexion.establecerConexion();

		// Inicializar Observable ArrayList
		listaProducto = FXCollections.observableArrayList();
		
		Producto.extraerDatosProductoTable(conexion.getConnection(), listaProducto);
		
		tblProductos.setItems(listaProducto);
		
		clmnProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre_producto"));
		clmnCostoProducto.setCellValueFactory(new PropertyValueFactory<Producto,Double>("precioActual_producto"));
		
		conexion.cerrarConexion();
		
	}

}
