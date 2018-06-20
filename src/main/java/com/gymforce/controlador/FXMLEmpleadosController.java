package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.TipoEmpleado;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLEmpleadosController implements Initializable {

	private ConexionMySQL conexion;

	private ObservableList<TipoEmpleado> listaTipo;
	private ObservableList<Empleado> listaEmpleado;
	
	 @FXML
	private AnchorPane empleadoAP;
	
	@FXML
	private JFXButton btnModificarEmpleadoTable;

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
	private JFXComboBox<TipoEmpleado> cmbTipoEmpleado;
	
	@FXML private TableColumn<Empleado, String> clmnRFC;
	@FXML private TableColumn<Empleado, String> clmnNombre;
	@FXML private TableColumn<Empleado, String> clmnApl1;
	@FXML private TableColumn<Empleado, String> clmnApel2;
	@FXML private TableColumn<Empleado, String> clmnTelefono;
	@FXML private TableColumn<Empleado, String> clmnCorreo;
	@FXML private TableColumn<Empleado, String> clmnUsuario;
	@FXML private TableColumn<Empleado, String> clmnPassword;
	@FXML private TableColumn<Empleado, TipoEmpleado> clmnClvTE;

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
	@FXML private TableColumn<TipoEmpleado, String> clmnDesc_TE;

	@FXML
	void btnAgregar(ActionEvent event) {
		if (txtRfc.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese un RFC de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtNombre.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Nombre de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtApellido1.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el primer Apellido de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtApellido2.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el segundo Apellido de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtTelefono.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Telefono de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtCorreo.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Correo de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtUsuario.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Usuario de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(txtContraseña.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese la Contraseña de Empleado");
			txtTipoEmpleado.requestFocus();
		}else if(cmbTipoEmpleado.getValue() == null) {
			Mensaje.error("Campo Vacio", "Ingrese el cargo de Empleado");
			txtTipoEmpleado.requestFocus();
		}else {
			try {
				conexion.establecerConexion();
				Empleado miCargo = new Empleado(txtRfc.getText(),
						txtNombre.getText(),
						txtApellido1.getText(),
						txtApellido2.getText(),
						txtTelefono.getText(),
						txtCorreo.getText(),
						txtUsuario.getText(),
						txtContraseña.getText(),
						cmbTipoEmpleado.getSelectionModel().getSelectedItem());
				int noReg = miCargo.guardarEmpleado(conexion.getConnection());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Tipo de Empleado Almacenado Correctamente");
					listaEmpleado.add(miCargo);
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
					Mensaje.informacion("Guardar Registro", "Tipo de Empleado Almacenado Correctamente");
					listaTipo.add(miCargo);
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
    		dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL EMPLEADO "+txtRfc.getText());
    		Optional<ButtonType> result = dialogo.showAndWait();
    		if (result.get() == ButtonType.OK) {
    			empleadoAP.getChildren().removeAll();
    	    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleado.fxml"));
    	    	empleadoAP.getChildren().setAll(fxml);
    	    	//validarMembresia();
    		}
        }else {
                Mensaje.error("Eliminar Empleado", "Problemas al eliminar al Empleado, Intente de nuevo");
            }
        conexion.cerrarConexion();
	}

	@FXML
	void btnEliminarTipo(ActionEvent event) {

	}

	@FXML
	void btnModificar(ActionEvent event) throws IOException {
		conexion.establecerConexion();
		Empleado miCargo = new Empleado(txtRfc.getText(),
				txtNombre.getText(),
				txtApellido1.getText(),
				txtApellido2.getText(),
				txtTelefono.getText(),
				txtCorreo.getText(),
				txtUsuario.getText(),
				txtContraseña.getText(),
				cmbTipoEmpleado.getSelectionModel().getSelectedItem());
		int noReg = miCargo.actualizarEmpleado(conexion.getConnection());
                if (noReg != 0) {        	
                    Alert dialogo = new Alert(AlertType.CONFIRMATION);
            		dialogo.setTitle("Continuar Actualizacion");
            		dialogo.setHeaderText(null);
            		dialogo.initStyle(StageStyle.UTILITY);
            		dialogo.setContentText("EN REALIDAD DESEA ACTUALIZAR LOS DATOS ");
            		Optional<ButtonType> result = dialogo.showAndWait();
            		if (result.get() == ButtonType.OK) {
            			empleadoAP.getChildren().removeAll();
            	    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLEmpleado.fxml"));
            	    	empleadoAP.getChildren().setAll(fxml);
            	    	//validarMembresia();
            		}
                }else{
                    Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
                }
        conexion.cerrarConexion();
	}

	@FXML
	void btnModificarEmpleado(ActionEvent event) {

	}

	@FXML
	void btnVer(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaTipo = FXCollections.observableArrayList();
		listaEmpleado = FXCollections.observableArrayList();

		TipoEmpleado.llenarTableTipoEmpleado(conexion.getConnection(), listaTipo);
		TipoEmpleado.llenarComboClasif(conexion.getConnection(), listaTipo);
		
		Empleado.llenarTableEmpleado(conexion.getConnection(), listaEmpleado);
		cmbTipoEmpleado.setItems(listaTipo);
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

		conexion.cerrarConexion();
		seleccionarColumnaTalbe();
	}

	private void seleccionarColumnaTalbe() {
		tblViewEmpleadosTipos.getSelectionModel().selectedIndexProperty()
				.addListener((observable, oldCount, newCount) -> {
					System.out.println("Hola");
				});
	}

	public void deshabilitar() {

	}

}
