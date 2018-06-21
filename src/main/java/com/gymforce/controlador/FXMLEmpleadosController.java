package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.TipoEmpleado;
import com.gymforce.reportes.Reportes2;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLEmpleadosController implements Initializable {
	int contador = 0;
	private ConexionMySQL conexion;

	private ObservableList<TipoEmpleado> listaTipo;
	private ObservableList<TipoEmpleado> listaTipoCmb;
	private ObservableList<Empleado> listaEmpleado;

	@FXML
	private JFXButton btnReporte;

	@FXML
	private FontAwesomeIconView iconRegresar;

	@FXML
	private JFXTabPane TabEmpleado;

	@FXML
	private AnchorPane empleadoAP;

	@FXML
	private Tab tabverEmpleados;

	@FXML
	private Tab tabadministrarEmpleado;

	@FXML
	private Tab tabadministratTE;

	@FXML
	private JFXTextField txtBuscarEmpleado;

	@FXML
	private TableView<Empleado> tblViewEmpleados;

	@FXML
	private JFXButton btnVerEmpleado;

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
	private JFXTextField txtUsuario;

	@FXML
	private JFXTextField txtContraseña;

	@FXML
	private ComboBox<TipoEmpleado> cmbTipoEmpleado;

	@FXML
	private TableColumn<Empleado, String> clmnRFC;
	@FXML
	private TableColumn<Empleado, String> clmnNombre;
	@FXML
	private TableColumn<Empleado, String> clmnApl1;
	@FXML
	private TableColumn<Empleado, String> clmnApel2;
	@FXML
	private TableColumn<Empleado, String> clmnTelefono;
	@FXML
	private TableColumn<Empleado, String> clmnCorreo;
	@FXML
	private TableColumn<Empleado, String> clmnUsuario;
	@FXML
	private TableColumn<Empleado, String> clmnPassword;
	@FXML
	private TableColumn<Empleado, TipoEmpleado> clmnClvTE;

	@FXML
	private JFXButton btnAgregarEmpleado;

	@FXML
	private JFXButton btnModicarEmpleado;

	@FXML
	private JFXButton btnEliminarEmpleado;

	@FXML
	private JFXTextField txtTipoEmpleado;

	@FXML
	private JFXButton btnAgregarTE;

	@FXML
	private JFXButton btnEliminarTE;

	@FXML
	private TableView<TipoEmpleado> tblViewEmpleadosTipos;
	@FXML
	private TableColumn<TipoEmpleado, String> clmnDesc_TE;

	@FXML
	void btnCrearReporte(ActionEvent event) {
		//ConexionMySQL cenexion = new ConexionMySQL();
        //cenexion.establecerConexion();
		//Connection conn = cenexion.getConnection();
		//Reporte.crearReporte(conn,"C:\\Users\\Misael\\JaspersoftWorkspace\\GymForce\\Empleado.jasper");
		//Reporte.showReporte();
		Reportes2 reporte = new Reportes2("Empleado");
    	reporte.generarReporte();
	}

	@FXML
	void btnAgregar(ActionEvent event) {
		if (txtRfc.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese un RFC de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtNombre.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Nombre de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtApellido1.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el primer Apellido de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtApellido2.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el segundo Apellido de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtTelefono.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Telefono de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtCorreo.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Correo de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtUsuario.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Usuario de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (txtContraseña.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese la Contraseña de Empleado");
			txtTipoEmpleado.requestFocus();
		} else if (cmbTipoEmpleado.getValue() == null) {
			Mensaje.error("Campo Vacio", "Ingrese el cargo de Empleado");
			txtTipoEmpleado.requestFocus();
		} else {
			try {
				conexion.establecerConexion();
				Empleado miCargo = new Empleado(txtRfc.getText(), txtNombre.getText(), txtApellido1.getText(),
						txtApellido2.getText(), txtTelefono.getText(), txtCorreo.getText(), txtUsuario.getText(),
						txtContraseña.getText(), cmbTipoEmpleado.getSelectionModel().getSelectedItem());
				int noReg = miCargo.guardarEmpleado(conexion.getConnection());
				if (noReg != 0) {
					Alert dialogo = new Alert(AlertType.CONFIRMATION);
					dialogo.setTitle("Continuar Guardando");
					dialogo.setHeaderText(null);
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.setContentText("EN REALIDAD DESEA GUARDAR LOS DATOS ");
					Optional<ButtonType> result = dialogo.showAndWait();
					if (result.get() == ButtonType.OK) {
						listaEmpleado.add(miCargo);
						// validarMembresia();
					} else {
						// validarMembresia();
					}

				}
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}
	}

	@FXML
	void btnAgregarTipoEmp(ActionEvent event) {
		if (txtTipoEmpleado.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese un cargo de Tipo de Empleado");
			txtTipoEmpleado.requestFocus();
		} else {
			try {
				conexion.establecerConexion();
				TipoEmpleado miCargo = new TipoEmpleado(txtTipoEmpleado.getText());
				int noReg = miCargo.guardarTipoEmpleado(conexion.getConnection());
				if (noReg != 0) {
					Alert dialogo = new Alert(AlertType.CONFIRMATION);
					dialogo.setTitle("Continuar Guardando");
					dialogo.setHeaderText(null);
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.setContentText("EN REALIDAD DESEA GUARDAR LOS DATOS ");
					Optional<ButtonType> result = dialogo.showAndWait();
					if (result.get() == ButtonType.OK) {
						listaTipo.add(miCargo);
						validarMembresia();
					} else {
						validarMembresia();
					}
				}
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}
	}

	@FXML
	void btnEliminar(ActionEvent event) throws IOException {
		conexion.establecerConexion();
		int noReg = tblViewEmpleados.getSelectionModel().getSelectedItem().eliminarEmpleado(conexion.getConnection());
		if (noReg != 0) {
			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar Eliminacion");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL EMPLEADO " + txtRfc.getText());
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
				empleadoAP.getChildren().removeAll();
				Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleados.fxml"));
				empleadoAP.getChildren().setAll(fxml);
				// validarMembresia();
			}
		} else {
			Mensaje.error("Eliminar Empleado", "Problemas al eliminar al Empleado, Intente de nuevo");
		}
		conexion.cerrarConexion();
	}

	@FXML
	void btnEliminarTipo(ActionEvent event) {
		conexion.establecerConexion();
		int noReg = tblViewEmpleadosTipos.getSelectionModel().getSelectedItem()
				.eliminarTipoEmpleado(conexion.getConnection());
		if (noReg != 0) {
			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar Eliminacion");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL EMPLEADO " + txtRfc.getText());
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {

			}
		} else {
			Mensaje.error("Eliminar Cargo", "Problemas al eliminar el Cargo, Intente de nuevo");
		}
		conexion.cerrarConexion();
	}

	@FXML
	void btnModificarEmpleado(ActionEvent event) throws IOException {
		conexion.establecerConexion();
		Empleado miCargo = new Empleado(txtRfc.getText(), txtNombre.getText(), txtApellido1.getText(),
				txtApellido2.getText(), txtTelefono.getText(), txtCorreo.getText(), txtUsuario.getText(),
				txtContraseña.getText(), cmbTipoEmpleado.getSelectionModel().getSelectedItem());
		int noReg = miCargo.actualizarEmpleado(conexion.getConnection());
		if (noReg != 0) {
			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar Guardando");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA GUARDAR LOS DATOS ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
				empleadoAP.getChildren().removeAll();
				Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleados.fxml"));
				empleadoAP.getChildren().setAll(fxml);
				// validarMembresia();
			}
		} else {
			Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
		}
		conexion.cerrarConexion();
	}

	@FXML
	void btnVer(ActionEvent event) {
		if (contador == 1) {
			tabadministrarEmpleado.setDisable(false);
			TabEmpleado.getSelectionModel().select(tabadministrarEmpleado);
			contador = 0;
			deshabilitar();
		} else {
			Mensaje.error("Accion No Valida", "Primero Seleccione un Registro");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		btnEliminarEmpleado.setVisible(false);
		btnModicarEmpleado.setVisible(false);
		iconRegresar.setVisible(false);
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaTipo = FXCollections.observableArrayList();
		listaEmpleado = FXCollections.observableArrayList();
		listaTipoCmb = FXCollections.observableArrayList();

		TipoEmpleado.llenarTableTipoEmpleado(conexion.getConnection(), listaTipo);
<<<<<<< HEAD
		TipoEmpleado.llenarComboClasif(conexion.getConnection(), listaTipo);

=======
		TipoEmpleado.llenarComboClasif(conexion.getConnection(), listaTipoCmb);
		
>>>>>>> fb756e37e437783a0d0b8c526a288d281dfc3315
		Empleado.llenarTableEmpleado(conexion.getConnection(), listaEmpleado);
		cmbTipoEmpleado.setItems(listaTipoCmb);
		tblViewEmpleadosTipos.setItems(listaTipo);
		tblViewEmpleados.setItems(listaEmpleado);

		clmnDesc_TE.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, String>("desc_te"));
		clmnRFC.setCellValueFactory(new PropertyValueFactory<Empleado, String>("rfc_empleado"));
		clmnNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre_empleado"));
		clmnApl1.setCellValueFactory(new PropertyValueFactory<Empleado, String>("ape1_empleado"));
		clmnApel2.setCellValueFactory(new PropertyValueFactory<Empleado, String>("ape2_empleado"));
		clmnTelefono.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono_empleado"));
		clmnCorreo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email_empleado"));
		clmnUsuario.setCellValueFactory(new PropertyValueFactory<Empleado, String>("usuario_empleado"));
		clmnPassword.setCellValueFactory(new PropertyValueFactory<Empleado, String>("password_empleado"));
		clmnClvTE.setCellValueFactory(new PropertyValueFactory<Empleado, TipoEmpleado>("clv_te"));

		llenarFormularioSeleccionEmpleado();
		conexion.cerrarConexion();

	}

	@FXML
	void IconRegresar(MouseEvent event) {
		tabverEmpleados.setDisable(false);
		tabadministratTE.setDisable(false);
		TabEmpleado.getSelectionModel().select(tabverEmpleados);
		validarMembresia();
	}

	private void llenarFormularioSeleccionEmpleado() {
		tblViewEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends Empleado> observable, Empleado oldValue, Empleado newValue) -> {
					tabadministrarEmpleado.setDisable(true);
					txtRfc.setText(newValue.getRfc_empleado());
					txtNombre.setText(newValue.getNombre_empleado());
					txtApellido1.setText(newValue.getApe1_empleado());
					txtApellido2.setText(newValue.getApe2_empleado());
					txtTelefono.setText(newValue.getTelefono_empleado());
					txtCorreo.setText(newValue.getEmail_empleado());
					txtUsuario.setText(newValue.getUsuario_empleado());
					txtContraseña.setText(newValue.getPassword_empleado());
					cmbTipoEmpleado.setValue(newValue.getClv_te());
					contador = 1;
				});
	}

	public void deshabilitar() {
		btnAgregarEmpleado.setVisible(false);
		btnEliminarEmpleado.setVisible(true);
		btnModicarEmpleado.setVisible(true);
		tabverEmpleados.setDisable(true);
		tabadministratTE.setDisable(true);
		iconRegresar.setVisible(true);
	}

	public void validarMembresia() {
		txtRfc.setText("");
		txtNombre.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtUsuario.setText("");
		txtContraseña.setText("");
		cmbTipoEmpleado.setValue(null);
		btnAgregarEmpleado.setVisible(true);
		btnModicarEmpleado.setVisible(false);
		btnEliminarEmpleado.setVisible(false);
		iconRegresar.setVisible(false);
	}

}
