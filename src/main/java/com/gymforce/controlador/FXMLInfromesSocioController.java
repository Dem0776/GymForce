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
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLInfromesSocioController {

	 @FXML
	    private AnchorPane informe;

	    @FXML
	    private JFXTextField txtSocio;

	    @FXML
	    private TableView<?> tblViewInfMembresias;

	    @FXML
	    private JFXComboBox<?> cmbMembresias;

	    @FXML
	    private JFXButton btnAgregarMembresias;

	    @FXML
	    private JFXButton btnEliminarMembresias;

	    @FXML
	    private JFXTextField txtSocioClases;

	    @FXML
	    private TableView<?> tblViewInfClases;

	    @FXML
	    private JFXComboBox<?> cmbClases;

	    @FXML
	    private JFXButton btnAgregarClase;

	    @FXML
	    private JFXButton btnEliminarClases;

	    @FXML
	    private JFXTextField txtEntrenamiento;

	    @FXML
	    private TableView<?> tblViewInfPlanesEntren;

	    @FXML
	    private JFXComboBox<?> cmbEntrenamiento;

	    @FXML
	    private JFXButton btnAgregarEntren;

	    @FXML
	    private JFXButton btnEliminarPlanesEntrena;

	    @FXML
	    private JFXTextField txtDietas;

	    @FXML
	    private TableView<?> tblViewInfDietas;

	    @FXML
	    private JFXComboBox<?> cmbDietas;

	    @FXML
	    private JFXButton btnAgregarDietas;

	    @FXML
	    private JFXButton btnEliminarDietas;

    @FXML
    void btnAgregar(ActionEvent event) {

    }

    @FXML
    void btnAgregarClases(ActionEvent event) {

    }

    @FXML
    void btnAgregarDieta(ActionEvent event) {

    }

    @FXML
    void btnAgregarEntrenamiento(ActionEvent event) {

    }

    @FXML
    void btnCerrar(ActionEvent event) throws IOException {

    }

    @FXML
    void btnEliminarDieta(ActionEvent event) {

    }
    @FXML
    void IconRegresar(MouseEvent event) throws IOException {
    	informe.getChildren().removeAll();
    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLSocios.fxml"));
    	informe.getChildren().setAll(fxml);
    }
}

