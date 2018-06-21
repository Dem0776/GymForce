package com.gymforce.controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Forma_pago;
import com.gymforce.modelo.Marca;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Producto;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class FXMLventasController implements Initializable {
    ConexionMySQL conexion;
	 private ObservableList<Producto> listaproducto;
	 private ObservableList<Forma_pago> listafp;
	 
	 @FXML
	    private TableView<Producto> tblvwProductosVentas;

	
    @FXML
    private JFXTextField txtrfcProductos;

    @FXML
    private JFXTextField txtcantidadProductos;

    @FXML
    private JFXTextField txtPrecioUnitarioProductos;

    @FXML
    private JFXComboBox<Forma_pago> cmbFormaPagoProductos;

    @FXML
    private JFXComboBox<Producto> cmbProductos;

    @FXML
    private JFXTextField txtrfcMembresias;

    @FXML
    private JFXTextField txtCantidadMembresias;

    @FXML
    private JFXTextField txtPrecioUnitarioMembresias;

    @FXML
    private JFXComboBox<?> cmbFormaPagoMembresias;

    @FXML
    private JFXComboBox<?> cmbMembresias;

    @FXML
    private TableView<?> tablvwMembresiasVenta;

    @FXML
    private JFXTextField txtrfcClases;

    @FXML
    private JFXTextField txtcantidadClases;

    @FXML
    private JFXTextField txtPrecioUnitarioClases;

    @FXML
    private JFXComboBox<?> cmbFormaPagoClases;

    @FXML
    private JFXComboBox<?> cmbClases;

    @FXML
    private TableView<?> tblvwClasesVentas;

    @FXML
    void btnAgregarClases(ActionEvent event) {

    }

    @FXML
    void btnAgregarMembresias(ActionEvent event) {

    }

    @FXML
    void btnCancelarCompraClases(ActionEvent event) {

    }

    @FXML
    void btnCancelarMembresias(ActionEvent event) {

    }

    @FXML
    void btnCancelarVentaProductos(ActionEvent event) {

    }

    @FXML
    void btnComprarClases(ActionEvent event) {

    }

    @FXML
    void btnComprarMembresias(ActionEvent event) {

    }

    @FXML
    void btnComprarProductos(ActionEvent event) {

    }

    @FXML
    void btnEliminarListaClases(ActionEvent event) {

    }

    @FXML
    void btnEliminarListaMembresias(ActionEvent event) {

    }

    @FXML
    void btnagregarProductoVenta(ActionEvent event) {

    	conexion = new ConexionMySQL();
    	
   	 int bandera=0;			
			try {
				String fp = cmbFormaPagoClases.getValue().toString();
				String pro=cmbProductos.getValue().toString();
				int cant= Integer.parseInt(txtcantidadProductos.getText());
				double precio=Double.parseDouble(txtPrecioUnitarioProductos.getText());
			} catch (Exception e) {
				
      bandera=1;
			}
        
       
      
       if(txtrfcProductos.getText().trim().length() == 0){
           Mensaje.error("Campo Vacio","Ingrese el RFC del SOCIO");;
           txtrfcProductos.requestFocus();
       }else if(txtcantidadProductos.getText().trim().length() == 0||Integer.parseInt(txtcantidadProductos.getText())<0){
           Mensaje.error("Campo Vacio o Valor no valido", "Ingrese cantidad del producto");
           txtcantidadProductos.requestFocus();
       }else if(txtPrecioUnitarioProductos.getText().trim().length() == 0||Double.parseDouble(txtPrecioUnitarioProductos.getText())<0){
           Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese Precio correctamente");
           txtPrecioUnitarioProductos.requestFocus();
       
   }else if(bandera==1) {
		Mensaje.error("Sin seleccion", "Ingrese la marca");
		cmbFormaPagoProductos.requestFocus();
		
       
       
   }else {
   	
   	Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Continuar guardando");
		dialogo.setHeaderText(null);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText("EN REALIDAD DESEA GUARADAR LOS DATOS ");
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {
    	
    }
   }
    }

    @FXML
    void btneliminarProducto(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
        listaproducto = FXCollections.observableArrayList();
        listafp = FXCollections.observableArrayList();
        
		
		
		Producto.llenarComboProducto(conexion.getConnection(),listaproducto);
	Forma_pago.llenarComboFP(conexion.getConnection(), listafp);
		
		cmbFormaPagoProductos.setItems(listafp);
		cmbProductos.setItems(listaproducto);
		
		
		conexion.cerrarConexion();
	
	}

}
