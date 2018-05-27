package com.gymforce.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FXMLloginController {

    @FXML
    private AnchorPane login;

    @FXML
    void btnLogin(ActionEvent event) {

    }

    @FXML
    void btnSalir(ActionEvent event) {
    	Platform.exit();
    }

}