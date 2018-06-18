package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.gymforce.modelo.Categoria;
import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleClaseEntrenador;
import com.gymforce.modelo.DetalleRutinaPlanE;
import com.gymforce.modelo.Ejercicio;
import com.gymforce.modelo.Empleado;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Mobiliario;
import com.gymforce.modelo.PlanEntrenamiento;
import com.gymforce.modelo.Rutina;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class FXMLplanEntrenamientoController implements Initializable {

	private ConexionMySQL conexion;

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
	private JFXTreeTableView<PlanEntrenamiento> tblViewPlanE;

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
	private JFXTreeTableView<Rutina> tbRutina;
	private TreeTableColumn<Rutina, Ejercicio> clmnEjercicio_rutina;
	private TreeTableColumn<Rutina, String> clmnSerie_rutina;
	private TreeTableColumn<Rutina, String> clmnRepeticion_rutina;
	private TreeTableColumn<Rutina, String> clmnDuracion_rutina;
	private TreeTableColumn<Rutina, String> clmnPeso_rutina;

	@FXML
	private JFXComboBox<Ejercicio> cmbEjercicioRutina;

	@FXML
	private JFXTextField txtSeries;

	@FXML
	private JFXTextField txtRepeticiones;

	@FXML
	private JFXTextField txtDuracion;

	@FXML
	private JFXTextField txtPeso;

	@FXML
	private JFXTextField txtDescEjercicio;

	@FXML
	private JFXTextField txtComplejidad;

	@FXML
	private JFXComboBox<Mobiliario> cmbEquipamento;

	@FXML
	private JFXTreeTableView<Ejercicio> tbEjercicios;
	private TreeTableColumn<Ejercicio, String> clmnNombre_ejercicio;
	private TreeTableColumn<Ejercicio, String> clmnComplejidad_ejercicio;
	private TreeTableColumn<Ejercicio, Mobiliario> clmnEquipamento_mobiliario;

	@FXML
	private JFXTextField txtDescCategoria;

	@FXML
	private JFXTreeTableView<Categoria> tbvCategoria;
	private TreeTableColumn<Categoria, String> clmnNombre_categoria;

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
			guardarPlanE();
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
			if (txtPeso.getText().trim().length() == 0 && 
					txtRepeticiones.getText().trim().length() != 0) {
				txtPeso.setText("0");
			}
			if (txtRepeticiones.getText().trim().length() == 0 && 
					txtPeso.getText().trim().length() != 0) {
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

	}

	@FXML
	void btnCancelarRutina(ActionEvent event) {

	}

	@FXML
	void btnEliminarPlanE(ActionEvent event) {

	}

	@FXML
	void btnVerPlanE(ActionEvent event) {

	}

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

		cmbEquipamento.setItems(oblListaMobiliariioCmb);
		cmbEjercicioRutina.setItems(oblListaEjercicioCmb);
		cmbCategoriaPlanE.setItems(oblListaCategoriaCmb);

		clmnNombre_categoria = new TreeTableColumn<>("Categoria");
		clmnNombre_categoria.setPrefWidth(150);
		clmnNombre_categoria.setCellValueFactory(new TreeItemPropertyValueFactory<Categoria, String>("desc_categoria"));
		final TreeItem<Categoria> rootCategoria = new RecursiveTreeItem<Categoria>(oblListaCategoriaTbv,
				RecursiveTreeObject::getChildren);
		tbvCategoria.getColumns().addAll(clmnNombre_categoria);
		tbvCategoria.setRoot(rootCategoria);
		tbvCategoria.setShowRoot(false);

		clmnNombre_ejercicio = new TreeTableColumn<>("Ejercicio");
		clmnComplejidad_ejercicio = new TreeTableColumn<>("Complejidad");
		clmnEquipamento_mobiliario = new TreeTableColumn<>("Equipamento");
		clmnNombre_ejercicio.setPrefWidth(150);
		clmnComplejidad_ejercicio.setPrefWidth(150);
		clmnEquipamento_mobiliario.setPrefWidth(150);
		clmnNombre_ejercicio.setCellValueFactory(new TreeItemPropertyValueFactory<Ejercicio, String>("desc_ejercicio"));
		clmnComplejidad_ejercicio
				.setCellValueFactory(new TreeItemPropertyValueFactory<Ejercicio, String>("complejidad_ejercicio"));
		clmnEquipamento_mobiliario
				.setCellValueFactory(new TreeItemPropertyValueFactory<Ejercicio, Mobiliario>("clv_mobiliario"));
		final TreeItem<Ejercicio> rootEjercicio = new RecursiveTreeItem<Ejercicio>(oblListaEjercicioTbv,
				RecursiveTreeObject::getChildren);
		tbEjercicios.getColumns().addAll(clmnNombre_ejercicio, clmnComplejidad_ejercicio, clmnEquipamento_mobiliario);
		tbEjercicios.setRoot(rootEjercicio);
		tbEjercicios.setShowRoot(false);

		clmnEjercicio_rutina = new TreeTableColumn<>("Ejercicio");
		clmnSerie_rutina = new TreeTableColumn<>("Series");
		clmnRepeticion_rutina = new TreeTableColumn<>("Repeticiones");
		clmnDuracion_rutina = new TreeTableColumn<>("Duracion");
		clmnPeso_rutina = new TreeTableColumn<>("Peso");
		clmnEjercicio_rutina.setCellValueFactory(new TreeItemPropertyValueFactory<Rutina, Ejercicio>("clv_ejercicio"));
		clmnSerie_rutina.setCellValueFactory(new TreeItemPropertyValueFactory<Rutina, String>("serie_rutina"));
		clmnRepeticion_rutina.setCellValueFactory(new TreeItemPropertyValueFactory<Rutina, String>("repeticion_rutina"));
		clmnDuracion_rutina.setCellValueFactory(new TreeItemPropertyValueFactory<Rutina, String>("duracion_rutina"));
		clmnPeso_rutina.setCellValueFactory(new TreeItemPropertyValueFactory<Rutina, String>("peso_rutina"));
		final TreeItem<Rutina> rootRutina = new RecursiveTreeItem<Rutina>(oblListaRutinaTbv,
				RecursiveTreeObject::getChildren);
		tbRutina.getColumns().addAll(clmnEjercicio_rutina, clmnSerie_rutina, clmnRepeticion_rutina, clmnDuracion_rutina,
				clmnPeso_rutina);
		tbRutina.setRoot(rootRutina);
		tbRutina.setShowRoot(false);

		conexion.cerrarConexion();
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
			PlanEntrenamiento plan = new PlanEntrenamiento(0, txtDescPlanE.getText(), 
					txtDuracionPlanE.getText(), txtFrecuenciaPlanE.getText(), 
					txtDificultadPlanE.getText(), "1", cmbCategoriaPlanE.getSelectionModel().getSelectedItem());			

			int noReg = plan.guardarPlanE(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Plane Almacenado Correctamente");
				//oblListaPlanEntrTbv.add(plan);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos, algo ocurrio");
		}
		conexion.cerrarConexion();
	}
	
	private void guardarDetalleCE() {
		try {
			conexion.establecerConexion();
			/*DetalleRutinaPlanE planR = new DetalleRutinaPlanE(PlanEntrenamiento.obtenerUltimoPlan(conexion.getConnection()), 
					cmb);*/
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}

}
