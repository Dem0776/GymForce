package com.gymforce.controlador;

import java.util.Optional;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Proveedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLagregarProveedor {
	ConexionMySQL conexion;
	@FXML
	private AnchorPane Proveedor2;

	@FXML
	private JFXTextField txtemailProveedor;

	@FXML
	private JFXTextField txtTelefonoProveedor;

	@FXML
	private JFXTextField txtrfcProveedor;

	@FXML
	private JFXTextField txtrazonSocialProveedor;

	@FXML
	private JFXTextField txtContactoProveedor;

	@FXML
	private JFXTextField txtbuscarProveedor;

	@FXML
	private JFXButton btnirbuscarProveedor;

	 @FXML
	    void btnprueba(ActionEvent event) {
		 conexion = new ConexionMySQL();

			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar guardando");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA GUARADAR LOS DATOS ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {


			if (txtrfcProveedor.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio", "Ingrese el RFC del proveedor");
				
				txtrfcProveedor.requestFocus();
			} else if (txtrazonSocialProveedor.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio ", "Ingrese razon social");
				txtrazonSocialProveedor.requestFocus();
			} else if (txtemailProveedor.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio ", "Ingrese proveedor");
				txtemailProveedor.requestFocus();
			} else if (txtTelefonoProveedor.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio ", "Ingrese email");
				txtTelefonoProveedor.requestFocus();
			} else if (txtContactoProveedor.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio ", "Ingrese contacto de proveedor");
				txtContactoProveedor.requestFocus();
			
			} else {
				try {
					conexion.establecerConexion();
	                Proveedor mob = new Proveedor(txtrfcProveedor.getText(),
	                		txtrazonSocialProveedor.getText(),txtemailProveedor.getText(),
	                		txtTelefonoProveedor.getText(),txtContactoProveedor.getText());
	             
	                int noReg = mob.guardarProveedor(conexion.getConnection());
	                if (noReg != 0) {
	                    Mensaje.informacion("Guardar Registro", "Proveedor Almacenado Correctamente");
	                    txtrfcProveedor.setText("");
	    				txtrazonSocialProveedor.setText("");
	    				txtemailProveedor.setText("");
	    				txtbuscarProveedor.setText("");
	    				txtTelefonoProveedor.setText("");
	    				txtContactoProveedor.setText("");
	    				
	                    conexion.cerrarConexion();
	                }
				} catch (Exception e) {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
				}
			}
			}else {
				txtrfcProveedor.setText("");
				txtrazonSocialProveedor.setText("");
				txtemailProveedor.setText("");
				txtbuscarProveedor.setText("");
				txtTelefonoProveedor.setText("");
				txtContactoProveedor.setText("");
				conexion.cerrarConexion();
			}
	    }
	
	@FXML
	void btnActualizarProveedor(ActionEvent event) {

	}

	@FXML
    void btnAgregarProveedor(ActionEvent event) {
       
	}
    

	@FXML
	void btnEliminarProveedor(ActionEvent event) {

	}

}
