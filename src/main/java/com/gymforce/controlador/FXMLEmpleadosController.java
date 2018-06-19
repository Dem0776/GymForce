package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.TipoEmpleado;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class FXMLEmpleadosController implements Initializable {

	private ConexionMySQL conexion;

	private ObservableList<TipoEmpleado> listaTipo;
	private ObservableList<Empleado> listaEmpleado;

	@FXML
	private JFXButton btnModificarEmpleadoTable;

	@FXML
	private JFXTextField txtBuscarEmpleado;

	@FXML
	private JFXTreeTableView<Empleado> tblViewEmpleados;

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
	private TreeTableColumn<Empleado, String> clmnRFC;
	private TreeTableColumn<Empleado, String> clmnNombre;
	private TreeTableColumn<Empleado, String> clmnApl1;
	private TreeTableColumn<Empleado, String> clmnApel2;
	private TreeTableColumn<Empleado, String> clmnTelefono;
	private TreeTableColumn<Empleado, String> clmnCorreo;
	private TreeTableColumn<Empleado, String> clmnUsuario;
	private TreeTableColumn<Empleado, String> clmnPassword;
	private TreeTableColumn<Empleado, TipoEmpleado> clmnClvTE;

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
	private JFXTreeTableView<TipoEmpleado> tblViewEmpleadosTipos;
	private TreeTableColumn<TipoEmpleado, String> clmnDesc_TE;

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
	void btnEliminar(ActionEvent event) {

	}

	@FXML
	void btnEliminarTipo(ActionEvent event) {

	}

	@FXML
	void btnModificar(ActionEvent event) {

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

		clmnDesc_TE = new TreeTableColumn<>("Tipo de Empleado");
		clmnDesc_TE.setPrefWidth(150);
		clmnDesc_TE.setCellValueFactory(new TreeItemPropertyValueFactory<TipoEmpleado, String>("desc_te"));
		final TreeItem<TipoEmpleado> root = new RecursiveTreeItem<TipoEmpleado>(listaTipo,
				RecursiveTreeObject::getChildren);
		tblViewEmpleadosTipos.getColumns().addAll(clmnDesc_TE);
		tblViewEmpleadosTipos.setRoot(root);
		tblViewEmpleadosTipos.setShowRoot(false);

		clmnRFC = new TreeTableColumn<>("RFC");
		clmnNombre = new TreeTableColumn<>("Nombre");
		clmnApl1 = new TreeTableColumn<>("Apellido 1");
		clmnApel2 = new TreeTableColumn<>("Apellido 2");
		clmnTelefono = new TreeTableColumn<>("Telefono");
		clmnCorreo = new TreeTableColumn<>("Correo");
		clmnUsuario = new TreeTableColumn<>("Usuario");
		clmnPassword = new TreeTableColumn<>("Password");
		clmnClvTE = new TreeTableColumn<>("Tipo Empleado");
		clmnRFC.setPrefWidth(100);
		clmnNombre.setPrefWidth(100);
		clmnApl1.setPrefWidth(100);
		clmnApel2.setPrefWidth(100);
		clmnTelefono.setPrefWidth(100);
		clmnCorreo.setPrefWidth(100);
		clmnUsuario.setPrefWidth(100);
		clmnPassword.setPrefWidth(100);
		clmnClvTE.setPrefWidth(100);
		clmnRFC.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("rfc_empleado"));
		clmnNombre.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("nombre_empleado"));
		clmnApl1.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("ape1_empleado"));
		clmnApel2.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("ape2_empleado"));
		clmnTelefono.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("telefono_empleado"));
		clmnCorreo.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("email_empleado"));
		clmnUsuario.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("usuario_empleado"));
		clmnPassword.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, String>("password_empleado"));
		clmnClvTE.setCellValueFactory(new TreeItemPropertyValueFactory<Empleado, TipoEmpleado>("clv_te"));
		
		final TreeItem<Empleado> rootEmpleado = new RecursiveTreeItem<Empleado>(listaEmpleado,
				RecursiveTreeObject::getChildren);
		tblViewEmpleados.getColumns().addAll(clmnRFC,clmnNombre,clmnApl1,clmnApel2,clmnTelefono,clmnCorreo,clmnUsuario,clmnPassword,clmnClvTE);
		tblViewEmpleados.setRoot(rootEmpleado);
		tblViewEmpleados.setShowRoot(false);	

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
