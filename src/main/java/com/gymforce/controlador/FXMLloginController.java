package com.gymforce.controlador;

import java.io.IOException;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLloginController {
	
	private ConexionMySQL conexion;
	private Empleado empleado;
	
    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtPassword;
	
	@FXML
    private AnchorPane ventanalogin;

    @FXML
    void btnLogin(ActionEvent event) throws IOException { 
    	int validar = 0;
    	if (txtUsuario.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el usuario");
			txtUsuario.requestFocus();
		} else if (txtPassword.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el password");
			txtPassword.requestFocus();
		} else {
			conexion = new ConexionMySQL();
	    	conexion.establecerConexion();    	
	    	empleado = new Empleado();
	    	validar = empleado.validarUsuario(conexion.getConnection(), String.valueOf(txtUsuario.getText()), 
	    			String.valueOf(txtPassword.getText()));
	    	if(validar == 1) {
	    		FXMLLoader principal = new FXMLLoader(getClass().getResource("/com/gymforce/vista/FXMLprincipal.fxml"));
	        	Parent root;
	        	Stage stage = new Stage();
	        	((Node)event.getSource()).getScene().getWindow().hide();
	        	root = (Parent) principal.load();
	        	stage.setScene(new Scene(root));
	        	stage.initStyle(StageStyle.UNDECORATED);
	        	//stage.setMaximized(true);    	
	        	stage.show();
	    	} else {
	    		Mensaje.error("ERROR", "USUARIO O PASSWORD INCORRECTO");
	    	}
	    	conexion.cerrarConexion();
		}
    }
    
    @FXML
    void btnSalir(ActionEvent event) {
    	Platform.exit();
    }       
}