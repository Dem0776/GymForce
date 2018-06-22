package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Forma_pago;

import com.gymforce.modelo.Membresia;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Producto;
import com.gymforce.modelo.Socio;
import com.gymforce.modelo.Venta;
import com.jfoenix.controls.JFXButton;
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
	private ObservableList<Forma_pago> listaFP;
    int banderaCompra=0;
	@FXML
	private TableView<Membresia> tblMembresias;

	@FXML
	private TableColumn<Membresia, String> clmnMembresia;

	@FXML
	private TableColumn<Membresia, Double> clmnCostoMembresia;

	@FXML
	private TableView<Producto> tblProductos;

	@FXML
	private TableColumn<Producto, String> clmnProducto;

	@FXML
	private TableColumn<Producto, Double> clmnCostoProducto;

	@FXML
	private TableView<Clase> tblClases;

	@FXML
	private TableColumn<Clase, String> clmnClases;

	@FXML
	private TableColumn<Clase, Double> clmnCostosClases;

	@FXML
	private TableView<Venta> tblCarrito;

	@FXML
	private TableColumn<Venta, Integer> clmnCantidadCarrito;

	@FXML
	private TableColumn<Producto, String> clmnProductoCarrito;

	@FXML
	private TableColumn<Producto, Double> clmnPreciounitCarrito;

	@FXML
	private TableColumn<?, ?> clmnPrecioTotalCarrito;

	@FXML
	private JFXTextField txtRFCcliente;

	@FXML
	private JFXComboBox<Forma_pago> cmbFormaPago;

	@FXML
	private JFXTextField txtTotalCompra;

	@FXML
	void btnAgregarClases(ActionEvent event) {
   
		if(banderaCompra==0) {
			JOptionPane.showMessageDialog(null, "APLICA");
			 banderaCompra=4+1;
			 Venta v= new Venta();
			 
			 //v.guardarVenta(conexion.getConnection(),txtRFCcliente.getText());
				int noReg = v.guardarVenta(conexion.getConnection(),txtRFCcliente.getText());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Venta Almacenada Correctamente");
				//	oblListaPlanEntrTbv.add(plan);
				}else {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
				}
			 
			 
		}else {
			JOptionPane.showMessageDialog(null, "NO APLICA");
		}
		
	}

	@FXML
	void btnAgregarMembresias(ActionEvent event) {
	
		if(banderaCompra==0) {
			JOptionPane.showMessageDialog(null, "APLICA");
			 banderaCompra=4+1;
			 Venta v= new Venta();
			// v.guardarVenta(conexion.getConnection(),txtRFCcliente.getText());
				int noReg = v.guardarVenta(conexion.getConnection(),txtRFCcliente.getText());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Venta Almacenada Correctamente");
				//	oblListaPlanEntrTbv.add(plan);
				}else {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
				}
			 
		}else {
			JOptionPane.showMessageDialog(null, "NO APLICA");
		}
		
		
	}

	@FXML
	void btnAgregarProductos(ActionEvent event) {
		
		if(banderaCompra==0) {
			JOptionPane.showMessageDialog(null, "APLICA");
			 banderaCompra=4+1;
			 Venta v= new Venta();
			

				int noReg = v.guardarVenta(conexion.getConnection(),txtRFCcliente.getText());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Venta Almacenada Correctamente");
				//	oblListaPlanEntrTbv.add(plan);
				}else {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
				}
			conexion.cerrarConexion();	 
		}else {
			JOptionPane.showMessageDialog(null, "NO APLICA");
		}
		
		
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

	@FXML
	private JFXButton btnAgregarP;
	@FXML
	private JFXButton btnAgregarC;

	@FXML
	private JFXButton btnCarritoR;
	@FXML
	private JFXButton btnAgregarM;
	@FXML
	private JFXButton btnComprarID;

	@FXML
	private JFXButton btnCancelarID;
	@FXML
	private JFXButton btnBuscarC;

	public void habilitarinicial() {
		btnComprarID.setDisable(true);
		btnCancelarID.setDisable(true);
		btnAgregarP.setDisable(true);
		btnAgregarC.setDisable(true);
		btnAgregarM.setDisable(true);
		btnCarritoR.setDisable(true);
		txtRFCcliente.setDisable(false);
		txtTotalCompra.setDisable(true);
		cmbFormaPago.setDisable(true);
		tblClases.setDisable(true);
		tblMembresias.setDisable(true);
		tblProductos.setDisable(true);
		tblCarrito.setDisable(true);
		txtRFCcliente.requestFocus();
	}
	
	
	public void habilitarfinal() {
		btnComprarID.setDisable(true);
		btnCancelarID.setDisable(true);
		
		btnAgregarP.setDisable(false);
		btnAgregarC.setDisable(false);
		btnAgregarM.setDisable(false);
		btnCarritoR.setDisable(false);
		txtRFCcliente.setDisable(true);
		txtTotalCompra.setDisable(false);
		cmbFormaPago.setDisable(false);
		tblClases.setDisable(false);
		tblMembresias.setDisable(false);
		tblProductos.setDisable(false);
		tblCarrito.setDisable(false);
		txtTotalCompra.requestFocus();
	}

	@FXML
	void btnBuscarClienteVenta(ActionEvent event) {

		String RFC = txtRFCcliente.getText();

		if (RFC.trim().length() == 0) {
			Mensaje.error("Campo vacio", "INGRESE RFC CLIENTE A BUSCAR");
			txtRFCcliente.requestFocus();
		} else {

			try {

				conexion.establecerConexion();   
			     Socio prod = new Socio();
			     String valor=prod.buscaSocio(conexion.getConnection(), txtRFCcliente.getText());
				
			     if(valor.equals(null)||valor.equals("")) {
			    	 Mensaje.informacion("NO ENCONTRADO", "No se encontro socio con RFC--> "+txtRFCcliente.getText());
			     }else {
			    	System.out.println("ENCONTRADO");
			    	habilitarfinal(); 
			     }
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();

		conexion.establecerConexion();
		habilitarinicial();
		// Inicializar Observable ArrayList
		listaProducto = FXCollections.observableArrayList();
		listaMembresia = FXCollections.observableArrayList();
		listaClases = FXCollections.observableArrayList();
		listaFP=FXCollections.observableArrayList();

		Membresia.llenarTableMembresia(conexion.getConnection(), listaMembresia);
		Producto.extraerDatosProductoTable(conexion.getConnection(), listaProducto);
		Clase.extraerDatosClaseTable(conexion.getConnection(), listaClases);
		Forma_pago.llenarComboFP(conexion.getConnection(),listaFP);
		tblMembresias.setItems(listaMembresia);
		tblProductos.setItems(listaProducto);
		tblClases.setItems(listaClases);
		cmbFormaPago.setItems(listaFP);

		clmnProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre_producto"));
		clmnCostoProducto.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioActual_producto"));

		clmnMembresia.setCellValueFactory(new PropertyValueFactory<Membresia, String>("desc_membresia"));
		clmnCostoMembresia.setCellValueFactory(new PropertyValueFactory<Membresia, Double>("precio_membresia"));

		clmnClases.setCellValueFactory(new PropertyValueFactory<Clase, String>("nombre_clase"));
		clmnCostosClases.setCellValueFactory(new PropertyValueFactory<Clase, Double>("precio"));

		conexion.cerrarConexion();

	}

}
