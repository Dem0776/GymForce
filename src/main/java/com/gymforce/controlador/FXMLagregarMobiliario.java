package com.gymforce.controlador;

import javafx.scene.control.ButtonType;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Mobiliario;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class FXMLagregarMobiliario {
	ConexionMySQL conexion;
	@FXML
	private JFXTextField txtDescripcionMobiliario;

	@FXML
	private JFXTextField txtCostoMobiliario;

	@FXML
	void btnAgregar(ActionEvent event) {
		conexion = new ConexionMySQL();

		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Continuar guardando");
		dialogo.setHeaderText(null);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText("EN REALIDAD DESEA GUARADAR LOS DATOS ");
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {

			if (txtDescripcionMobiliario.getText().trim().length() == 0) {
				Mensaje.error("Campo Vacio", "Ingrese la descripcio del mobiliario");
				;
				txtDescripcionMobiliario.requestFocus();
			} else if (Double.valueOf(txtCostoMobiliario.getText()) < 0) {
				Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese costo correctamente");
				txtCostoMobiliario.requestFocus();

			} else {

				try {
					conexion.establecerConexion();
					Mobiliario mob = new Mobiliario(txtDescripcionMobiliario.getText(),
							Double.valueOf(txtCostoMobiliario.getText()));

					int noReg = mob.guardarMobiliario(conexion.getConnection());
					if (noReg != 0) {
						Mensaje.informacion("Guardar Registro", "Mobiliario Almacenada Correctamente");
						txtDescripcionMobiliario.setText("");
						txtCostoMobiliario.setText("");
					conexion.cerrarConexion();
					}
				} catch (Exception e) {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
				}
			}
		}else {
			txtDescripcionMobiliario.setText("");
			txtCostoMobiliario.setText("");
		if (txtDescripcionMobiliario.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese la descripcio del mobiliario");
			;
			txtDescripcionMobiliario.requestFocus();
		} else if (Double.valueOf(txtCostoMobiliario.getText()) < 0) {
			Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese costo correctamente");
			txtCostoMobiliario.requestFocus();

		} else {
			try {
				conexion.establecerConexion();
				Mobiliario mob = new Mobiliario(txtDescripcionMobiliario.getText(),
						Double.valueOf(txtDescripcionMobiliario.getText()));
				int noReg = mob.guardarMobiliario(conexion.getConnection());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Mobiliario Almacenada Correctamente");
					//listMobiliario.add(mob);					
				}
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
			conexion.cerrarConexion();
		}
		}
		}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
