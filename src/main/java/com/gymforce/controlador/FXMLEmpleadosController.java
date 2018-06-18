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

public class FXMLEmpleadosController implements Initializable{
	
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

    }

    @FXML
    void btnAgregarTipoEmp(ActionEvent event) {
    	if (txtTipoEmpleado.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingrese un cargo de Empleado");
			txtTipoEmpleado.requestFocus();
		}else {
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
		
		cmbTipoEmpleado.setItems(listaTipo);
		
		clmnDesc_TE = new TreeTableColumn<>("Tipo de Empleado");
		clmnDesc_TE.setPrefWidth(150);
		clmnDesc_TE.setCellValueFactory(new TreeItemPropertyValueFactory<TipoEmpleado,String>("desc_te"));
		final TreeItem<TipoEmpleado> root = new RecursiveTreeItem<TipoEmpleado>(listaTipo, RecursiveTreeObject::getChildren);
		tblViewEmpleadosTipos.getColumns().addAll(clmnDesc_TE);
		tblViewEmpleadosTipos.setRoot(root);
		tblViewEmpleadosTipos.setShowRoot(false);
		
	
		
		conexion.cerrarConexion();
		seleccionarColumnaTalbe();
	}
	private void seleccionarColumnaTalbe() {
		tblViewEmpleadosTipos.getSelectionModel().selectedIndexProperty().addListener((observable, oldCount, newCount) -> {
			System.out.println("Hola");
		});
	}
	public void deshabilitar() {
		
	}

}
