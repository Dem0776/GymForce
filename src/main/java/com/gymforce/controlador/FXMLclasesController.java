package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleClaseEntrenador;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLclasesController implements Initializable {

	private ConexionMySQL conexion;
	private int banderaClase = 0;
	private int contAgregarC = 0;
	private int banderaCancelar = 0;
	private int clvClase;

	private ObservableList<Clase> listaClase;
	private ObservableList<Empleado> listaInstructor;

	@FXML
	private JFXTabPane tpClases;

	@FXML
	private Tab tabVerClase;

	@FXML
	private Tab tabClase;

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
	private JFXButton btnAgregarClase;

	@FXML
	private JFXButton btnActualizar;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		
		listaClase = FXCollections.observableArrayList();
		listaInstructor = FXCollections.observableArrayList();
		btnActualizar.setVisible(false);

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
		llenarFormularioSeleccion();
	}

	@FXML
	void btnActualizarClase(ActionEvent event) {
		Double precio;
		tpClases.getSelectionModel().select(tabVerClase);
		btnActualizar.setVisible(false);
		tabVerClase.setDisable(false);
		if (cmbInstructor.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un instructor");
			cmbInstructor.requestFocus();
		} else {
			try {
				precio = Double.valueOf(txtPrecio.getText());
				actualizarClase();
				actualizarDetalleCE();
				limpiar();				
				Mensaje.informacion("Actualizar Registro", "Clase actualizada");
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}

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
				if (banderaClase == 0) {
					guardarClase();
					guardarDetalleCE();
					limpiar();
					// banderaCancelar = 1;
					Mensaje.informacion("Guardar Registro", "Clase agregada");
				} else {
					Mensaje.informacion("Guardar Registro", "Clase agregada");
					contAgregarC = 0;
					limpiar();
				}

			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}
	}

	@FXML
	void btnBuscarClase(ActionEvent event) {

	}

	@FXML
	void btnCancelarClase(ActionEvent event) {
		limpiar();
		tpClases.getSelectionModel().select(tabVerClase);
		btnActualizar.setVisible(false);
		tabVerClase.setDisable(false);
		/*
		 * if (banderaCancelar == 1) { conexion.establecerConexion(); Clase clase = new
		 * Clase(Clase.obtenerUltimaClase(conexion.getConnection())); int noReg =
		 * clase.cancelarClase(conexion.getConnection()); conexion.cerrarConexion();
		 * banderaCancelar = 0; }
		 */
	}

	@FXML
	void btnEliminiarClase(ActionEvent event) {
		conexion.establecerConexion();
		Clase clase = new Clase();
		try {
			if (Mensaje.confirmar("Confirmacion", "Desea eliminar la clase?") == 1) {
				int noReg = clase.eliminarClase(conexion.getConnection(), clvClase);
				tvbViewClases.refresh();
			}
		} catch (Exception e) {

		}
		conexion.cerrarConexion();
	}

	@FXML
	void btnVerClase(ActionEvent event) {
		tpClases.getSelectionModel().select(tabClase);
		btnActualizar.setVisible(true);
		tabVerClase.setDisable(true);
	}

	private void guardarClase() {
		try {
			conexion.establecerConexion();
			Clase clase = new Clase(txtNombre.getText(), txtDescripcion.getText());
			int noReg = clase.guardarClase(conexion.getConnection());
			if (noReg != 0) {
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

	private void actualizarClase() {
		conexion.establecerConexion();
		Clase clase = new Clase();
		clase.setClv_clase(clvClase);
		clase.setNombre_clase(txtNombre.getText());
		clase.setDesc_clase(txtDescripcion.getText());
		int noReg = clase.actualizarClase(conexion.getConnection());
		if (noReg != 0) {
			Mensaje.informacion("Actualizar Registro", "Clase Actualizada Correctamente");
		} else {
			Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
		}
		conexion.cerrarConexion();
	}

	private void actualizarDetalleCE() {
		conexion.establecerConexion();
		DetalleClaseEntrenador dce = new DetalleClaseEntrenador(cmbInstructor.getSelectionModel().getSelectedItem(),
				clvClase, Double.valueOf(txtPrecio.getText()));
		int noReg = dce.actualizarDetalleClaseE(conexion.getConnection());
		conexion.cerrarConexion();
	}

	private void limpiar() {
		txtNombre.setText("");
		txtDescripcion.setText("");
		txtPrecio.setText("");
		cmbInstructor.setValue(null);
	}

	private void llenarFormularioSeleccion() {
		tvbViewClases.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends Clase> observable, Clase oldValue, Clase newValue) -> {
					clvClase = newValue.getClv_clase();
					txtNombre.setText(newValue.getNombre_clase());
					txtDescripcion.setText(String.valueOf(newValue.getDesc_clase()));
					txtPrecio.setText(String.valueOf(newValue.getPrecio()));
					cmbInstructor.setValue(newValue.getNombreInstructor());
				});
	}
}
