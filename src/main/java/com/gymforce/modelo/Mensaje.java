package com.gymforce.modelo;

import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class Mensaje {
	public static void advertencia(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("GymForce");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	public static void error(String encabezado, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("GymForce");
		alert.setHeaderText(encabezado);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	public static void informacion(String encabezado, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("GymForce");
		alert.setHeaderText(encabezado);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	public static int confirmar(String encabezado, String mensaje) {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Continuar Actualizacion");
		dialogo.setHeaderText(encabezado);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText(mensaje);
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {
			return 1;
		} else {			
			return 0;
		}

	}
}
