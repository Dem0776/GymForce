package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Dieta;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.PlanEntrenamiento;
import com.gymforce.modelo.Socio;
import com.gymforce.modelo.TipoEmpleado;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLSociosController  implements Initializable{
	    int contador = 0;
	    private ConexionMySQL conexion;
	    private ObservableList<Clase> listaClase;
	    private ObservableList<Dieta> listaDieta;
	    private ObservableList<PlanEntrenamiento> listaPE;
	    private ObservableList<Socio> listaSocio;
	    
	    @FXML
	    private FontAwesomeIconView añadirPE;

	    @FXML
	    private FontAwesomeIconView subPE;
	    
	    @FXML
	    private FontAwesomeIconView añadirDieta;

	    @FXML
	    private FontAwesomeIconView subDieta;
	    
	    @FXML
	    private AnchorPane socios;

	    @FXML
	    private JFXTextField txtBuscarSocio;

	    @FXML
	    private TableView<Socio> tblViewSocios;
	    
	    @FXML
	    private TableColumn<Socio, String> clmnRfcSocio;

	    @FXML
	    private TableColumn<Socio,String> clmnNombreSocio;

	    @FXML
	    private TableColumn<Socio, String> clmnApe1Socio;

	    @FXML
	    private TableColumn<Socio, String> clmnApe2Socio;

	    @FXML
	    private TableColumn<Socio, String> clmnTelefonoSocio;
	    
	    @FXML
	    private TableColumn<Socio, String> clmnDireccionSocio;

	    @FXML
	    private TableColumn<Socio, String> clmnEmailSocio;

	    @FXML
	    private JFXButton btnVerSocio;

	    @FXML
	    private JFXTabPane tabPaneSocios;
	    
	    @FXML
	    private Tab tabVerSocios;
	    
	    @FXML
	    private Tab tabAdministrarSocio;

	    @FXML
	    private JFXButton btnModificarEmpleado;

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
	    private JFXTextField txtDireccion;

	    @FXML
	    private JFXTextField txtCorreo;

	    @FXML
	    private JFXButton btnAgregarSocio;

	    @FXML
	    private JFXButton btnModificarSocio;

	    @FXML
	    private JFXButton btnEliminarSocio;

	    @FXML
	    private JFXComboBox<PlanEntrenamiento> cmbPlanesEntren;

	    @FXML
	    private JFXComboBox<Dieta> cmbDietas;

	    @FXML
	    private TableView<?> tblViewAgegar;
	    
	    @FXML
	    private TableColumn<?, ?> clmnRfcPE;
	    
    @FXML
    void btnAgregar(ActionEvent event) {
    	if (txtRfc.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese un RFC de Socio");
			txtRfc.requestFocus();
		} else if (txtNombre.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Nombre de Socio");
			txtNombre.requestFocus();
		} else if (txtApellido1.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el primer Apellido de Socio");
			txtApellido1.requestFocus();
		} else if (txtApellido2.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el segundo Apellido de Socio");
			txtApellido2.requestFocus();
		} else if (txtTelefono.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Telefono de Socio");
			txtTelefono.requestFocus();
		} else if (txtDireccion.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese la Direccion de Socio");
			txtDireccion.requestFocus();
		} else if (txtCorreo.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese el Correo del Socio");
			txtCorreo.requestFocus();
		}else {
			try {
				conexion.establecerConexion();
				Socio miSocio = new Socio(txtRfc.getText(), txtNombre.getText(), txtApellido1.getText(),
						txtApellido2.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
				int noReg = miSocio.guardarSocio(conexion.getConnection());
				if (noReg != 0) {
					Alert dialogo = new Alert(AlertType.CONFIRMATION);
					dialogo.setTitle("Continuar Guardando");
					dialogo.setHeaderText(null);
					dialogo.initStyle(StageStyle.UTILITY);
					dialogo.setContentText("EN REALIDAD DESEA GUARDAR LOS DATOS ");
					Optional<ButtonType> result = dialogo.showAndWait();
					if (result.get() == ButtonType.OK) {
						Alert dialogo1 = new Alert(AlertType.CONFIRMATION);
						dialogo1.setTitle("Continuar Detalle");
						dialogo1.setHeaderText(null);
						dialogo1.initStyle(StageStyle.UTILITY);
						dialogo1.setContentText("DESEA AGREGAR PLANES DE ENTRENAMIENTOY DIETAS");
						Optional<ButtonType> result1 = dialogo1.showAndWait();
						if (result1.get() == ButtonType.OK) {
							listaSocio.add(miSocio);
							activarCombos();
							desactivarEmpleado();
						// validarMembresia();
					} else {
						// validarMembresia();
					}
						
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
		int noReg = tblViewSocios.getSelectionModel().getSelectedItem().eliminarSocio(conexion.getConnection());
		if (noReg != 0) {
			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar Eliminacion");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL EMPLEADO " + txtRfc.getText());
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
				socios.getChildren().removeAll();
				Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLSocios.fxml"));
				socios.getChildren().setAll(fxml);
				// validarMembresia();
			}
		} else {
			Mensaje.error("Eliminar Socio", "Problemas al eliminar al Socio, Intente de nuevo");
		}
		conexion.cerrarConexion();
    }

    @FXML
    void btnModificar(ActionEvent event) throws IOException {
    	conexion.establecerConexion();
    	Socio miSocio = new Socio(txtRfc.getText(), txtNombre.getText(), txtApellido1.getText(),
				txtApellido2.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
		int noReg = miSocio.actualizarSocio(conexion.getConnection());
		if (noReg != 0) {
			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar Actualizando");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA MODIFCAR LOS DATOS ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
				socios.getChildren().removeAll();
				Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLSocios.fxml"));
				socios.getChildren().setAll(fxml);
				// validarMembresia();
			}
		} else {
			Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
		}
		conexion.cerrarConexion();
    }

    @FXML
    void btnVer(ActionEvent event) throws IOException {
    	
    	if (contador == 1) {
    		
    		
    		//System.out.println(obj.getRfc());
    		socios.getChildren().removeAll();
        	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLInformesSocio.fxml"));
        	socios.getChildren().setAll(fxml);
        	FXMLInfromesSocioController obj = new FXMLInfromesSocioController();
        	
    		obj.setRfc("kjhgc");
			contador = 0;
			//deshabilitar();
			
		} else {
			Mensaje.error("Accion No Valida", "Primero Seleccione un Registro");
		}	
    }
    @FXML
    void btnModificarEmpleado(ActionEvent event) {
    	if (contador == 1) {
			tabAdministrarSocio.setDisable(false);
			tabPaneSocios.getSelectionModel().select(tabAdministrarSocio);
			contador = 0;
			//deshabilitar();
		} else {
			Mensaje.error("Accion No Valida", "Primero Seleccione un Registro");
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		desactivarCombos();
		
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		
		listaDieta = FXCollections.observableArrayList();
		listaPE = FXCollections.observableArrayList();
		
		listaSocio = FXCollections.observableArrayList();
		
		
		Dieta.llenarComboDieta(conexion.getConnection(), listaDieta);
		PlanEntrenamiento.llenarComboPE(conexion.getConnection(), listaPE);
		
		Socio.llenarTableSocio(conexion.getConnection(), listaSocio);
		
	
		cmbDietas.setItems(listaDieta);
		cmbPlanesEntren.setItems(listaPE);
		tblViewSocios.setItems(listaSocio);
		
		clmnRfcSocio.setCellValueFactory(new PropertyValueFactory<Socio,String>("rfc_socio"));
	    clmnNombreSocio.setCellValueFactory(new PropertyValueFactory<Socio,String>("nombre_socio"));
		clmnApe1Socio.setCellValueFactory(new PropertyValueFactory<Socio,String>("ape1_socio"));
		clmnApe2Socio.setCellValueFactory(new PropertyValueFactory<Socio,String>("ape2_socio"));
		clmnTelefonoSocio.setCellValueFactory(new PropertyValueFactory<Socio,String>("telefono_socio"));
		clmnDireccionSocio.setCellValueFactory(new PropertyValueFactory<Socio,String>("direccion_socio"));
		clmnEmailSocio.setCellValueFactory(new PropertyValueFactory<Socio,String>("email_socio"));	
		llenarFormularioSeleccionSocios();
		conexion.cerrarConexion();
	}
	private void llenarFormularioSeleccionSocios() {
		tblViewSocios.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends Socio> observable, Socio oldValue, Socio newValue) -> {
					//tabadministrarEmpleado.setDisable(true);
					txtRfc.setText(newValue.getRfc_socio());
					txtNombre.setText(newValue.getNombre_socio());
					txtApellido1.setText(newValue.getApe1_socio());
					txtApellido2.setText(newValue.getApe2_socio());
					txtTelefono.setText(newValue.getTelefono_socio());
					txtDireccion.setText(newValue.getDireccion_socio());
					txtCorreo.setText(newValue.getEmail_socio());
					contador = 1;
				});
	}
	

    @FXML
    void subDieta(MouseEvent event) {

    }

    @FXML
    void subPE(MouseEvent event) {

    }
    
    @FXML
    void añadirDieta(MouseEvent event) {
    	
		
    }

    @FXML
    void añadirPE(MouseEvent event) {
    
    }
    
    public void desactivarCombos() {
    	cmbDietas.setDisable(true);
		cmbPlanesEntren.setDisable(true);
		añadirDieta.setDisable(true);
		añadirPE.setDisable(true);
		subDieta.setDisable(true);
		subPE.setDisable(true);
    }
    public void activarCombos() {
    	cmbDietas.setDisable(false);
		cmbPlanesEntren.setDisable(false);
		añadirDieta.setDisable(false);
		añadirPE.setDisable(false);
		subDieta.setDisable(false);
		subPE.setDisable(false);
    }
    public void desactivarEmpleado() {
    	txtRfc.setDisable(true);
    	txtNombre.setDisable(true);
    	txtApellido1.setDisable(true);
    	txtApellido2.setDisable(true);
    	txtTelefono.setDisable(true);
    	txtDireccion.setDisable(true);
    	txtCorreo.setDisable(true);
    }
    
}

