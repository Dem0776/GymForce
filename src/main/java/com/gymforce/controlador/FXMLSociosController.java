package com.gymforce.controlador;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class FXMLSociosController {

	@FXML
    private AnchorPane socios;

    @FXML
    private JFXTextField txtBuscarSocio;

    @FXML
    private JFXTreeTableView<?> tblViewSocios;

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
    private JFXComboBox<?> cmbMembresia;

    @FXML
    private JFXComboBox<?> cmbClases;

    @FXML
    private JFXComboBox<?> cmbPlanesEntren;

    @FXML
    private JFXComboBox<?> cmbDietas;

    @FXML
    private JFXTreeTableView<?> tblViewAgegar;

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

}

