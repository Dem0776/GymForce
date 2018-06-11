package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.TipoEmpleado;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLempleadoController implements Initializable {
	private ObservableList<Empleado> informacion;

	@FXML
	private TableView<Empleado> tblInformacion;

	@FXML
	private TableColumn<Empleado, String> clmnrfc_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnnombre_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnape1_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnape2_empleado;
	@FXML
	private TableColumn<Empleado, String> clmntelefono_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnemail_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnusuario_empleado;
	@FXML
	private TableColumn<Empleado, String> clmnpassword_empleado;
	@FXML
	private TableColumn<Empleado, TipoEmpleado> clmnclv_te;
	@FXML
	private TableColumn<Empleado, String> clmnstatus_empleado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clmnrfc_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("rfc_empleado"));
		clmnnombre_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre_empleado"));
		clmnape1_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("ape1_empleado"));
		clmnape2_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("ape2_empleado"));
		clmntelefono_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono_empleado"));
		clmnemail_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email_empleado"));
		clmnusuario_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("usuario_empleado"));
		clmnpassword_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("password_empleado"));
		clmnclv_te.setCellValueFactory(new PropertyValueFactory<Empleado, TipoEmpleado>("clv_te"));
		clmnstatus_empleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("status_empleado"));

		tblInformacion.setItems(informacion);
	}

}
