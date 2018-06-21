package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Categoria;
import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleRutinaPlanE;
import com.gymforce.modelo.Ejercicio;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Mobiliario;
import com.gymforce.modelo.PlanEntrenamiento;
import com.gymforce.modelo.Rutina;
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

public class FXMLplanEntrenamientoController implements Initializable {

	private ConexionMySQL conexion;
	private int banderaAddRutPlan = 0;
	private int clvRutina = 0;
	private int clvPlanE = 0;

	private ObservableList<PlanEntrenamiento> oblListaPlanEntrTbv;
	private ObservableList<Categoria> oblListaCategoriaCmb;
	private ObservableList<Rutina> oblListaRutinaTbv;
	private ObservableList<Ejercicio> oblListaEjercicioCmb;
	private ObservableList<Mobiliario> oblListaMobiliariioCmb;
	private ObservableList<Ejercicio> oblListaEjercicioTbv;
	private ObservableList<Categoria> oblListaCategoriaTbv;

	@FXML
	private JFXTextField txtBuscarPlanE;

	@FXML
	private TableView<PlanEntrenamiento> tblViewPlanE;
	private TableColumn<PlanEntrenamiento, String> clmnDesc_planE;
	private TableColumn<PlanEntrenamiento, String> clmnDuracion_planE;
	private TableColumn<PlanEntrenamiento, String> clmnFrecuencia_planE;
	private TableColumn<PlanEntrenamiento, String> clmnDificultad_planE;
	private TableColumn<PlanEntrenamiento, Categoria> clmnCategoria_planE;
	private TableColumn<PlanEntrenamiento, Ejercicio> clmnEjercicio_planE;
	private TableColumn<PlanEntrenamiento, Rutina> clmnRutina_planE;

	@FXML
	private JFXTabPane tpPlanEntr;

	@FXML
	private Tab tabVerPlan;

	@FXML
	private Tab tabPlanEntr;

	@FXML
	private Tab tabRutina;

	@FXML
	private Tab tabEjercicio;

	@FXML
	private Tab tabCategoria;

	@FXML
	private JFXButton btnModificar;

	@FXML
	private JFXTextField txtDescPlanE;

	@FXML
	private JFXTextField txtDuracionPlanE;

	@FXML
	private JFXTextField txtFrecuenciaPlanE;

	@FXML
	private JFXTextField txtDificultadPlanE;

	@FXML
	private JFXComboBox<Categoria> cmbCategoriaPlanE;

	@FXML
	private TableView<Rutina> tbvRutina;

	@FXML
	private TableColumn<Rutina, Ejercicio> clmnEjercicio_rutina;

	@FXML
	private TableColumn<Rutina, Number> clmnSerie_rutina;

	@FXML
	private TableColumn<Rutina, Number> clmnRepeticion_rutina;

	@FXML
	private TableColumn<Rutina, String> clmnDuracion_rutina;

	@FXML
	private TableColumn<Rutina, Number> clmnPeso_rutina;

	@FXML
	private JFXComboBox<Ejercicio> cmbEjercicioRutina;

	@FXML
	private JFXTextField txtSeries;

	@FXML
	private JFXButton btnActualizarB;

	@FXML
	private JFXTextField txtRepeticiones;

	@FXML
	private JFXTextField txtDuracion;

	@FXML
	private JFXTextField txtPeso;
	
    @FXML
    private JFXButton btnAgregarRutina;

	@FXML
	private JFXTextField txtDescEjercicio;

	@FXML
	private JFXTextField txtComplejidad;

	@FXML
	private JFXComboBox<Mobiliario> cmbEquipamento;

	@FXML
	private TableView<Ejercicio> tbvEjercicios;

	private TableColumn<Ejercicio, Integer> clmnClv_ejercicio;

	@FXML
	private TableColumn<Ejercicio, String> clmnNombre_ejercicio;

	@FXML
	private TableColumn<Ejercicio, String> clmnComplejidad_ejercicio;

	@FXML
	private TableColumn<Ejercicio, Mobiliario> clmnEquipamento_mobiliario;

	@FXML
	private JFXTextField txtDescCategoria;

	@FXML
	private TableView<Categoria> tbvCategoria;
	@FXML
	private TableColumn<Categoria, String> clmnNombre_categoria;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();

		oblListaCategoriaTbv = FXCollections.observableArrayList();
		oblListaEjercicioTbv = FXCollections.observableArrayList();
		oblListaEjercicioCmb = FXCollections.observableArrayList();
		oblListaMobiliariioCmb = FXCollections.observableArrayList();
		oblListaRutinaTbv = FXCollections.observableArrayList();
		oblListaCategoriaCmb = FXCollections.observableArrayList();
		oblListaPlanEntrTbv = FXCollections.observableArrayList();

		Categoria.llenarTableCategoria(conexion.getConnection(), oblListaCategoriaTbv);
		Categoria.llenarComboCategoria(conexion.getConnection(), oblListaCategoriaCmb);
		Mobiliario.llenarComboEquipamento(conexion.getConnection(), oblListaMobiliariioCmb);
		Ejercicio.lleanarTableEjercicio(conexion.getConnection(), oblListaEjercicioTbv);
		Rutina.llenarTableRutina(conexion.getConnection(), oblListaRutinaTbv);
		Ejercicio.llenarComboEjercicio(conexion.getConnection(), oblListaEjercicioCmb);
		PlanEntrenamiento.llenarTablePlanE(conexion.getConnection(), oblListaPlanEntrTbv);

		cmbEquipamento.setItems(oblListaMobiliariioCmb);
		cmbEjercicioRutina.setItems(oblListaEjercicioCmb);
		cmbCategoriaPlanE.setItems(oblListaCategoriaCmb);
		btnActualizarB.setVisible(false);

		clmnDesc_planE = new TableColumn<>("Plan de Entrenamiento");
		clmnDuracion_planE = new TableColumn<>("Duracion");
		clmnFrecuencia_planE = new TableColumn<>("Frecuencia");
		clmnDificultad_planE = new TableColumn<>("Dificultad");
		clmnCategoria_planE = new TableColumn<>("Categoria");
		clmnEjercicio_planE = new TableColumn<>("Ejercicio");
		clmnRutina_planE = new TableColumn<>("Series");
		clmnDesc_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, String>("desc_pe"));
		clmnDuracion_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, String>("duracion_pe"));
		clmnFrecuencia_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, String>("frecuencia_pe"));
		clmnDificultad_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, String>("dificultad_pe"));
		clmnCategoria_planE
				.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, Categoria>("clv_categoria"));
		clmnEjercicio_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, Ejercicio>("ejercicio"));
		clmnRutina_planE.setCellValueFactory(new PropertyValueFactory<PlanEntrenamiento, Rutina>("serie"));
		tblViewPlanE.getColumns().addAll(clmnDesc_planE, clmnDuracion_planE, clmnFrecuencia_planE, clmnDificultad_planE,
				clmnCategoria_planE, clmnEjercicio_planE, clmnRutina_planE);
		tblViewPlanE.setItems(oblListaPlanEntrTbv);

		clmnNombre_categoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("desc_categoria"));
		clmnNombre_categoria.setPrefWidth(150);
		tbvCategoria.setItems(oblListaCategoriaTbv);

		clmnClv_ejercicio = new TableColumn<>();
		clmnClv_ejercicio.setCellValueFactory(new PropertyValueFactory<Ejercicio, Integer>("clv_ejercicio"));
		clmnNombre_ejercicio.setCellValueFactory(new PropertyValueFactory<Ejercicio, String>("desc_ejercicio"));
		clmnComplejidad_ejercicio
				.setCellValueFactory(new PropertyValueFactory<Ejercicio, String>("complejidad_ejercicio"));
		clmnEquipamento_mobiliario
				.setCellValueFactory(new PropertyValueFactory<Ejercicio, Mobiliario>("clv_mobiliario"));
		clmnNombre_ejercicio.setPrefWidth(150);
		clmnComplejidad_ejercicio.setPrefWidth(150);
		clmnEquipamento_mobiliario.setPrefWidth(150);
		tbvEjercicios.setItems(oblListaEjercicioTbv);

		clmnEjercicio_rutina.setCellValueFactory(new PropertyValueFactory<Rutina, Ejercicio>("clv_ejercicio"));
		clmnSerie_rutina.setCellValueFactory(new PropertyValueFactory<Rutina, Number>("serie_rutina"));
		clmnRepeticion_rutina.setCellValueFactory(new PropertyValueFactory<Rutina, Number>("repeticion_rutina"));
		clmnDuracion_rutina.setCellValueFactory(new PropertyValueFactory<Rutina, String>("duracion_rutina"));
		clmnPeso_rutina.setCellValueFactory(new PropertyValueFactory<Rutina, Number>("peso_rutina"));
		tbvRutina.setItems(oblListaRutinaTbv);

		conexion.cerrarConexion();
		llenarFormularioRutina();
		llenarFormularioPlanE();
	}

	@FXML
	void btnActualizar(ActionEvent event) {
		tpPlanEntr.getSelectionModel().select(tabVerPlan);
		btnActualizarB.setVisible(false);
		tabVerPlan.setDisable(false);
		if (cmbCategoriaPlanE.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un instructor");
			cmbCategoriaPlanE.requestFocus();
		} else {
			actualizarPlanE();
			limpiarPlanE();
			Mensaje.informacion("Actualizar Registro", "Clase actualizada");
		}
	}

	@FXML
	void btnAgregarCategoria(ActionEvent event) {
		if (txtDescCategoria.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre de la categoria");
			txtDescCategoria.requestFocus();
		} else {
			try {
				guardarCategoria();
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
		}
	}

	@FXML
	void btnAgregarEjercicio(ActionEvent event) {
		if (txtDescEjercicio.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre del ejercicio");
			txtDescEjercicio.requestFocus();
		} else if (txtComplejidad.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la complejidad del ejercicio");
			txtComplejidad.requestFocus();
		} else if (cmbEquipamento.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione el equipo a utilizar");
			cmbEquipamento.requestFocus();
		} else {
			guardarEjercicio();
		}
	}

	@FXML
	void btnAgregarPlanE(ActionEvent event) {
		if (txtDescPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre del plan");
			txtDescPlanE.requestFocus();
		} else if (txtDuracionPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la duracion del plan");
			txtDuracionPlanE.requestFocus();
		} else if (txtFrecuenciaPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la frecuencia del plan");
			txtFrecuenciaPlanE.requestFocus();
		} else if (txtDificultadPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la dificultad del plan");
			txtDificultadPlanE.requestFocus();
		} else if (cmbCategoriaPlanE.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione la categoria");
			cmbCategoriaPlanE.requestFocus();
		} else {
			if (banderaAddRutPlan == 1) {
				banderaAddRutPlan = 0;

			} else {
				guardarPlanE();
			}
		}
	}

	@FXML
	void btnAgregarRutina(ActionEvent event) {
		int serie;
		int rep;
		int peso;
		if (cmbEjercicioRutina.getValue() == null) {
			Mensaje.error("Campo Vacio", "Selecciona un ejercicio");
			cmbEjercicioRutina.requestFocus();
		} else if (txtSeries.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa las series de la rutina");
			txtComplejidad.requestFocus();
		} else if (txtDuracion.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la duracion de la rutina");
			txtComplejidad.requestFocus();
		} else {
			if (txtPeso.getText().trim().length() == 0 && txtRepeticiones.getText().trim().length() != 0) {
				txtPeso.setText("0");
			}
			if (txtRepeticiones.getText().trim().length() == 0 && txtPeso.getText().trim().length() != 0) {
				txtRepeticiones.setText("0");
			}
			try {
				serie = Integer.valueOf(txtSeries.getText());
				rep = Integer.valueOf(txtRepeticiones.getText());
				peso = Integer.valueOf(txtPeso.getText());
				guardarRutina();
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, mmm");
			}
			txtPeso.setText("");
		}
	}

	@FXML
	void btnAgregarRutinaPlan(ActionEvent event) {
		if (txtDescPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre del plan");
			txtDescPlanE.requestFocus();
		} else if (txtDuracionPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la duracion del plan");
			txtDuracionPlanE.requestFocus();
		} else if (txtFrecuenciaPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la frecuencia del plan");
			txtFrecuenciaPlanE.requestFocus();
		} else if (txtDificultadPlanE.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la dificultad del plan");
			txtDificultadPlanE.requestFocus();
		} else if (cmbCategoriaPlanE.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione la categoria");
			cmbCategoriaPlanE.requestFocus();
		} else if (clvRutina == 0) {
			Mensaje.error("Campo Vacio", "Seleccione una rutina");
		} else {
			if (banderaAddRutPlan == 0) {
				guardarPlanE();
				guardarDetalleRpe();
				banderaAddRutPlan = 1;
			} else {
				guardarDetalleRpe();
			}
		}
	}

	@FXML
	void btnBuscarPlanE(ActionEvent event) {

	}

	@FXML
	void btnCancelarCategoria(ActionEvent event) {

	}

	@FXML
	void btnCancelarEjercicio(ActionEvent event) {

	}

	@FXML
	void btnCancelarPlanE(ActionEvent event) {
		limpiarPlanE();
		tpPlanEntr.getSelectionModel().select(tabVerPlan);
		btnAgregarRutina.setVisible(true);
		tabVerPlan.setDisable(false);
		banderaAddRutPlan = 0;
	}

	@FXML
	void btnCancelarRutina(ActionEvent event) {

	}

	@FXML
	void btnEliminarPlanE(ActionEvent event) {
		conexion.establecerConexion();
		if(clvPlanE != 0) {
			PlanEntrenamiento plan = new PlanEntrenamiento();
			try {
				if (Mensaje.confirmar("Confirmacion", "Desea eliminar el Plan?") == 1) {
					int noReg = plan.eliminarPlanE(conexion.getConnection(), clvPlanE);
					tblViewPlanE.refresh();
				}
			} catch (Exception e) {

			}
		} else {
			Mensaje.advertencia("Selecciona un plan de la lista");
		}
		
		conexion.cerrarConexion();
	}

	@FXML
	void btnVerPlanE(ActionEvent event) {
		if(clvPlanE != 0) {
			tpPlanEntr.getSelectionModel().select(tabPlanEntr);
			btnActualizarB.setVisible(true);
			tabVerPlan.setDisable(true);
			btnAgregarRutina.setVisible(false);
		} else {
			Mensaje.advertencia("Selecciona un plan de la lista");
		}
	}

	private void guardarCategoria() {
		try {
			conexion.establecerConexion();
			Categoria cat = new Categoria(txtDescCategoria.getText());
			int noReg = cat.guardarCategoria(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Categoria Almacenada Correctamente");
				oblListaCategoriaTbv.add(cat);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}

	private void guardarEjercicio() {
		try {
			conexion.establecerConexion();
			Ejercicio ejercicio = new Ejercicio(txtDescEjercicio.getText(), txtComplejidad.getText(),
					cmbEquipamento.getSelectionModel().getSelectedItem());
			int noReg = ejercicio.guardarEjercicio(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Ejercicio Almacenada Correctamente");
				oblListaEjercicioCmb.add(ejercicio);
				oblListaEjercicioTbv.add(ejercicio);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}

	private void guardarRutina() {
		try {
			conexion.establecerConexion();
			Rutina rutina = new Rutina(Integer.valueOf(txtSeries.getText()), Integer.valueOf(txtRepeticiones.getText()),
					txtDuracion.getText(), Integer.valueOf(txtPeso.getText()),
					cmbEjercicioRutina.getSelectionModel().getSelectedItem());

			int noReg = rutina.guardarRutina(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Rutina Almacenada Correctamente");
				oblListaRutinaTbv.add(rutina);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
		}
		conexion.cerrarConexion();
	}

	private void guardarPlanE() {
		try {
			conexion.establecerConexion();
			PlanEntrenamiento plan = new PlanEntrenamiento(0, txtDescPlanE.getText(), txtDuracionPlanE.getText(),
					txtFrecuenciaPlanE.getText(), txtDificultadPlanE.getText(), "0",
					cmbCategoriaPlanE.getSelectionModel().getSelectedItem());

			int noReg = plan.guardarPlanE(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Plane Almacenado Correctamente");
				oblListaPlanEntrTbv.add(plan);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
		}
		conexion.cerrarConexion();
	}

	private void guardarDetalleRpe() {
		try {
			conexion.establecerConexion();
			DetalleRutinaPlanE drpe = new DetalleRutinaPlanE(
					PlanEntrenamiento.obtenerUltimoPlan(conexion.getConnection()), clvRutina);
			int noReg = drpe.guardarDetalleRutinaPlanE(conexion.getConnection());
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}
	
	private void actualizarPlanE() {
		conexion.establecerConexion();
		PlanEntrenamiento plan = new PlanEntrenamiento();
		plan.setClv_pe(clvPlanE);
		plan.setDesc_pe(txtDescPlanE.getText());
		plan.setDuracion_pe(txtDuracionPlanE.getText());
		plan.setFrecuencia_pe(txtFrecuenciaPlanE.getText());
		plan.setDificultad_pe(txtDificultadPlanE.getText());
		plan.setClv_categoria(cmbCategoriaPlanE.getSelectionModel().getSelectedItem());
		int noReg = plan.actualizarPlanE(conexion.getConnection());
		if (noReg != 0) {
			Mensaje.informacion("Actualizar Registro", "Plan Actualizado Correctamente");
		} else {
			Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
		}
		conexion.cerrarConexion();
	}
	
	private void llenarFormularioRutina() {
		tbvRutina.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends Rutina> observable, Rutina oldValue, Rutina newValue) -> {
					clvRutina = newValue.getClv_rutina();
					txtSeries.setText(String.valueOf(newValue.getSerie_rutina()));
					txtRepeticiones.setText(String.valueOf(newValue.getRepeticion_rutina()));
					txtDuracion.setText(String.valueOf(newValue.getDuracion_rutina()));
					txtPeso.setText(String.valueOf(newValue.getPeso_rutina()));
					cmbEjercicioRutina.setValue(newValue.getClv_ejercicio());
				});
	}

	private void llenarFormularioPlanE() {
		tblViewPlanE.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends PlanEntrenamiento> observable, PlanEntrenamiento oldValue,
						PlanEntrenamiento newValue) -> {
					clvPlanE = newValue.getClv_pe();
					txtDescPlanE.setText(String.valueOf(newValue.getDesc_pe()));
					txtDuracionPlanE.setText(String.valueOf(newValue.getDuracion_pe()));
					txtFrecuenciaPlanE.setText(String.valueOf(newValue.getFrecuencia_pe()));
					txtDificultadPlanE.setText(String.valueOf(newValue.getDificultad_pe()));
					cmbCategoriaPlanE.setValue(newValue.getClv_categoria());
				});
	}

	private void limpiarPlanE() {
		txtDescPlanE.setText("");
		txtDuracionPlanE.setText("");
		txtFrecuenciaPlanE.setText("");
		txtDificultadPlanE.setText("");
		cmbCategoriaPlanE.setValue(null);
	}

}
