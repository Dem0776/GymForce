package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleClaseEntrenador;
import com.gymforce.modelo.Marca;
import com.gymforce.modelo.Membresia;
import com.gymforce.modelo.Producto;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class FXMLlistraProdController implements Initializable {

	private ConexionMySQL conexion;
	private ObservableList<Membresia> lstMembresia;
	private ObservableList<Producto> lstProducto;
	private ObservableList<Clase> lstClase;

	@FXML
    private VBox vbPrincipal;

    @FXML
    private JFXTextField txtBuscar;

    @FXML
    private TableView<Producto> tblProducto;

    @FXML
    private TableColumn<Producto, String> clmnDesc_producto;

    @FXML
    private TableColumn<Producto, Double> clmnPrecio_producto;

    @FXML
    private TableColumn<Producto, Integer> clmnExistencia_producto;

    @FXML
    private TableColumn<Producto, Marca> clmnMarca_producto;

    @FXML
    private TableView<Membresia> tblMembresia;

    @FXML
    private TableColumn<Membresia, String> clmnDesc_membresia;

    @FXML
    private TableColumn<Membresia, Double> clmnPrecio_membresia;

    @FXML
    private TableColumn<Membresia, Integer> clmnDuracion_membresia;

    @FXML
    private TableView<Clase> tblClase;

    @FXML
    private TableColumn<Clase, String> clmnDesc_clase;

    @FXML
    private TableColumn<Clase, DetalleClaseEntrenador> clmnPrecio_clase;

	@FXML
	void btnAgregar(ActionEvent event) {

	}

	@FXML
	void btnBuscar(ActionEvent event) {

	}

	@FXML
	void btnRegresar(ActionEvent event) throws IOException {
		VBox ventas;
		ventas = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLventas.fxml"));
		vbPrincipal.getChildren().clear();
		vbPrincipal.getChildren().add(ventas);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();

		lstMembresia = FXCollections.observableArrayList();
		lstClase = FXCollections.observableArrayList();
		lstProducto = FXCollections.observableArrayList();

		Membresia.llenarTableMembresia(conexion.getConnection(), lstMembresia);
		Clase.llenarTableClase(conexion.getConnection(), lstClase);
		Producto.llenarTableProducto(conexion.getConnection(), lstProducto);
				
		clmnDesc_producto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre_producto"));
		clmnPrecio_producto.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioActual_producto"));
		clmnExistencia_producto.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("existencia_producto"));
		clmnMarca_producto.setCellValueFactory(new PropertyValueFactory<Producto, Marca>("clv_marca"));
		tblProducto.setItems(lstProducto);
		
		clmnDesc_membresia.setCellValueFactory(new PropertyValueFactory<Membresia, String>("desc_membresia"));
		clmnPrecio_membresia.setCellValueFactory(new PropertyValueFactory<Membresia, Double>("precio_membresia"));		
		clmnDuracion_membresia.setCellValueFactory(new PropertyValueFactory<Membresia, Integer>("diasValidos_membresia"));
		tblMembresia.setItems(lstMembresia);
		
		clmnDesc_clase.setCellValueFactory(new PropertyValueFactory<Clase, String>("nombre_clase"));
		clmnPrecio_clase.setCellValueFactory(new PropertyValueFactory<Clase, DetalleClaseEntrenador>("precio"));
		tblClase.setItems(lstClase);
		
		conexion.cerrarConexion();
	}
}
