package com.gymforce.controlador;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;



public class FXMLagregarProducto {

	
	 @FXML
	    private AnchorPane productos2;

    @FXML
    private JFXTextField btndescripcion;

    @FXML
    private JFXTextField btnprecio;

    @FXML
    private JFXTextField btnnombreproducto;

    @FXML
    private JFXTextField btnmarca;

    @FXML
    private JFXTextField txtbuscarproducto;

    @FXML
    private JFXButton btnirbuscarproducto;

    @FXML
    void btnAgregar(ActionEvent event) {

    }

    @FXML
    void btnEliminar(ActionEvent event) {

    }

   
}