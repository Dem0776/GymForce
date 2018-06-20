package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleClaseEntrenador;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLclasesController implements Initializable {

	private ConexionMySQL conexion;

	private ObservableList<Clase> listaClase;
	private ObservableList<Empleado> listaInstructor;

	@FXML
	private JFXTextField txtBuscarClase;

    @FXML
    private TableView<Clase> tvbViewClases;

	private TableColumn<Clase, String> clmnNombre_clase;
	private TableColumn<Clase, String> clmnDesc_clase;
	private TableColumn<Clase, Empleado> clmnInstructor;
	private TableColumn<Clase, DetalleClaseEntrenador> clmnPrecio;

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextField txtDescripcion;

	@FXML
	private JFXTextField txtPrecio;

	@FXML
	private JFXComboBox<Empleado> cmbInstructor;

    @FXML
    private JFXTextField txtDomingoInicio;

    @FXML
    private JFXTextField txtDomingoFin;

    @FXML
    private JFXTextField txtLunesInicio;

    @FXML
    private JFXTextField txtMartesInicio;

    @FXML
    private JFXTextField txtMiercolesInicio;

    @FXML
    private JFXTextField txtJuevesInicio;

    @FXML
    private JFXTextField txtViernesInicio;

    @FXML
    private JFXTextField txtSabadoInicio;

    @FXML
    private JFXTextField txtLunesFin;

    @FXML
    private JFXTextField txtMartesFin;

    @FXML
    private JFXTextField txtMiercolesFin;

    @FXML
    private JFXTextField txtJuevesFin;

    @FXML
    private JFXTextField txtViernesFin;

    @FXML
    private JFXTextField txtSabadoFin;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaClase = FXCollections.observableArrayList();
		listaInstructor = FXCollections.observableArrayList();

		Clase.llenarTableClase(conexion.getConnection(), listaClase);
		Empleado.llenarComboInstructor(conexion.getConnection(), listaInstructor);

		cmbInstructor.setItems(listaInstructor);

		clmnNombre_clase = new TableColumn<>("Clase");
		clmnDesc_clase = new TableColumn<>("Descripcion");
		clmnInstructor = new TableColumn<>("Instructor");
		clmnPrecio = new TableColumn<>("Precio");
		/*
		 * clmnNombre_clase.setPrefWidth(150); clmnDesc_clase.setPrefWidth(150);
		 * clmnInstructor.setPrefWidth(150); clmnPrecio.setPrefWidth(150);
		 */
		clmnNombre_clase.setCellValueFactory(new PropertyValueFactory<Clase, String>("nombre_clase"));
		clmnDesc_clase.setCellValueFactory(new PropertyValueFactory<Clase, String>("desc_clase"));
		clmnInstructor.setCellValueFactory(new PropertyValueFactory<Clase, Empleado>("nombreInstructor"));
		clmnPrecio.setCellValueFactory(new PropertyValueFactory<Clase, DetalleClaseEntrenador>("precio"));		
		tvbViewClases.getColumns().addAll(clmnNombre_clase, clmnDesc_clase, clmnInstructor, clmnPrecio);		
		tvbViewClases.setItems(listaClase);

		conexion.cerrarConexion();		
	}

	@FXML
	void btnAgregarClase(ActionEvent event) {
		Double precio;
		if (txtNombre.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre de la clase");
			txtNombre.requestFocus();
		} else if (txtDescripcion.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la descripcion de la clase");
			txtDescripcion.requestFocus();
		} else if (txtPrecio.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el precio de la clase");
			txtPrecio.requestFocus();
		} else if (cmbInstructor.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un instructor");
			cmbInstructor.requestFocus();
		} else {
			try {
				precio = Double.valueOf(txtPrecio.getText());
				guardarClase();
				guardarDetalleCE();
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}
	}

	@FXML
	void btnAsignarHorarioClase(ActionEvent event) {

	}

	@FXML
	void btnBuscarClase(ActionEvent event) {

	}

	@FXML
	void btnCancelarClase(ActionEvent event) {

	}

	@FXML
	void btnEliminiarClase(ActionEvent event) {

	}

	@FXML
	void btnVerClase(ActionEvent event) {

	}

	private void guardarClase() {
		try {
			conexion.establecerConexion();
			Clase clase = new Clase(txtNombre.getText(), txtDescripcion.getText());
			int noReg = clase.guardarClase(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Clase Almacenada Correctamente");
				listaClase.add(clase);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}

	private void guardarDetalleCE() {
		try {
			conexion.establecerConexion();
			DetalleClaseEntrenador dec = new DetalleClaseEntrenador(cmbInstructor.getSelectionModel().getSelectedItem(),
					Clase.obtenerUltimaClase(conexion.getConnection()), Double.valueOf(txtPrecio.getText()));
			int noReg = dec.guardarDetalleClaseEntr(conexion.getConnection());
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}

	private void limpiar() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
	}

	/*private void seleccionarColumnaTalbe() {
		tvbViewClases.getSelectionModel().selectedIndexProperty().addListener((observable, oldCount, newCount) -> {
			 TreeItem<Clase> selectedItem = tvbViewClases.getSelectionModel().getSelectedItem();
			 int index = selectedItem.getParent().getChildren().indexOf(selectedItem);
			 System.out.println(index);
		});
	}*/
}
