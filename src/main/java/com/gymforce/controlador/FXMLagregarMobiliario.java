package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Mobiliario;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

public class FXMLagregarMobiliario implements Initializable {
	ConexionMySQL conexion;
	private ObservableList<Mobiliario> listMobiliario;
	@FXML
	private JFXTextField txtDescripcionMobiliario;

	@FXML
	private JFXTextField txtCostoMobiliario;

	@FXML
	void btnAgregar(ActionEvent event) {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}