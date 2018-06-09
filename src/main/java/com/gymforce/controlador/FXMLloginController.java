package com.gymforce.controlador;

import java.io.IOException;

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

	@FXML
    private AnchorPane ventanalogin;

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
    	FXMLLoader principal = new FXMLLoader(getClass().getResource("/com/gymforce/vista/FXMLprincipal.fxml"));
    	Parent root;
    	Stage stage = new Stage();
    	((Node)event.getSource()).getScene().getWindow().hide();
    	root = (Parent) principal.load();
    	stage.setScene(new Scene(root));
    	stage.initStyle(StageStyle.UNDECORATED);
    	//stage.setMaximized(true);    	
    	stage.show();
    }

    @FXML
    void btnSalir(ActionEvent event) {
    	Platform.exit();
    }

}