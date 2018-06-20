package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.TipoEmpleado;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class FXMLSociosController  implements Initializable{
	    
	    private ConexionMySQL conexion;
	    private ObservableList<Clase> listaClase;
	    @FXML
	    private AnchorPane socios;

	    @FXML
	    private JFXTextField txtBuscarSocio;

	    @FXML
	    private TableView<?> tblViewSocios;
	    
	    @FXML
	    private TableColumn<?, ?> clmnRfcSocio;

	    @FXML
	    private TableColumn<?, ?> clmnNombreSocio;

	    @FXML
	    private TableColumn<?, ?> clmnApe1Socio;

	    @FXML
	    private TableColumn<?, ?> clmnApe2Socio;

	    @FXML
	    private TableColumn<?, ?> clmnTelefonoSocio;

	    @FXML
	    private TableColumn<?, ?> clmnEmailSocio;

	    @FXML
	    private JFXButton btnVerSocio;

	    @FXML
	    private JFXButton btnModificarSocioTable;

	    @FXML
	    private JFXTextField txtRfc;

	    @FXML
	    private JFXTextField txtNombre;

	    @FXML
	    private JFXTextField txtApellido1;

	    @FXML
	    private JFXTextField txtApellido2;

	    @FXML
	    private JFXTextField txtTelefono;

	    @FXML
	    private JFXTextField txtCorreo;

	    @FXML
	    private JFXButton btnAgregarSocio;

	    @FXML
	    private JFXButton btnModificarSocio;

	    @FXML
	    private JFXButton btnEliminarSocio;

	    @FXML
	    private JFXComboBox<Clase> cmbClases;

	    @FXML
	    private JFXComboBox<?> cmbPlanesEntren;

	    @FXML
	    private JFXComboBox<?> cmbDietas;

	    @FXML
	    private TableView<?> tblViewAgegar;
	    
	    @FXML
	    private TableColumn<?, ?> clmnAñadir;
	    
    @FXML
    void btnAgregar(ActionEvent event) {

    }

    @FXML
    void btnEliminar(ActionEvent event) {

    }

    @FXML
    void btnModificar(ActionEvent event) {

    }

    @FXML
    void btnVer(ActionEvent event) throws IOException {
    	socios.getChildren().removeAll();
    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLInformesSocio.fxml"));
    	socios.getChildren().setAll(fxml);
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaClase = FXCollections.observableArrayList();
		Clase.llenarComboClase(conexion.getConnection(), listaClase);
		cmbClases.setItems(listaClase);
		conexion.cerrarConexion();
		
	}

}

