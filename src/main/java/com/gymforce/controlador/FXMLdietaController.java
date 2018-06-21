package com.gymforce.controlador;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.gymforce.modelo.Alimento;
import com.gymforce.modelo.Clase;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleClaseEntrenador;
import com.gymforce.modelo.DetalleDietaAlimento;
import com.gymforce.modelo.Dieta;
import com.gymforce.modelo.Membresia;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.mysql.fabric.xmlrpc.base.Value;

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

public class FXMLdietaController implements Initializable {

	private ConexionMySQL conexion;
	private int banderaDieta = 0;
	private int banderaDetDieta = 0;
	private int clvDieta;

	private ObservableList<Alimento> oblListaAlimentoTbv;
	private ObservableList<Alimento> oblListaAlimentoCmb;
	private ObservableList<Dieta> oblListaDietaTbv;
	private ObservableList<DetalleDietaAlimento> oblListaDetDietaATbv;

	@FXML
	private JFXTabPane tpDieta;

	@FXML
	private Tab tabVerDietas;

	@FXML
	private Tab tabDietas;

	@FXML
	private Tab tabAlimentos;

	@FXML
	private JFXTextField txtBuscarDieta;

	@FXML
	private TableView<Dieta> tbvDietas;
	private TableColumn<Dieta, String> clmn_dieta;
	private TableColumn<Dieta, String> clmn_descDieta;
	private TableColumn<Dieta, Alimento> clmn_alimentoDieta;

	@FXML
	private JFXTextField txtNombreDieta;

	@FXML
	private JFXTextField txtDescDieta;

	@FXML
	private JFXComboBox<Alimento> cmbAlimentoDieta;

	@FXML
	private TableView<DetalleDietaAlimento> tbvAlimentoDieta;

	@FXML
	private TableColumn<DetalleDietaAlimento, Alimento> clmnAlimento_dda;
	private TableColumn<DetalleDietaAlimento, Number> clmnClv_dda;
	private TableColumn<DetalleDietaAlimento, Dieta> clmnDieta_dda;

	@FXML
	private JFXTextField txtNombreAlimento;

	@FXML
	private JFXTextField txtDescAlimento;

	@FXML
	private TableView<Alimento> tbvAlimento;

	@FXML
	private TableColumn<Alimento, String> clmnnombre_alimento;

	@FXML
	private TableColumn<Alimento, String> clmndesc_alimento;
	
    @FXML
    private JFXButton btnActualizarB;
    
    @FXML
    private JFXButton btnModificar;
    
    @FXML
    private JFXButton btnAgregarAl;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();

		oblListaAlimentoTbv = FXCollections.observableArrayList();
		oblListaAlimentoCmb = FXCollections.observableArrayList();
		oblListaDietaTbv = FXCollections.observableArrayList();
		oblListaDetDietaATbv = FXCollections.observableArrayList();
		btnActualizarB.setVisible(false);

		Alimento.llenarTableAlimento(conexion.getConnection(), oblListaAlimentoTbv);
		Alimento.llenarComboAlimento(conexion.getConnection(), oblListaAlimentoCmb);
		Dieta.llenarTableDieta(conexion.getConnection(), oblListaDietaTbv);

		cmbAlimentoDieta.setItems(oblListaAlimentoCmb);

		clmnnombre_alimento.setCellValueFactory(new PropertyValueFactory<Alimento, String>("nombre_alimento"));
		clmndesc_alimento.setCellValueFactory(new PropertyValueFactory<Alimento, String>("desc_alimento"));
		tbvAlimento.setItems(oblListaAlimentoTbv);

		clmn_dieta = new TableColumn<>("Dieta");
		clmn_descDieta = new TableColumn<>("Descripcion");
		clmn_alimentoDieta = new TableColumn<>("Alimento");
		clmn_dieta.setPrefWidth(200);
		clmn_descDieta.setPrefWidth(200);
		clmn_alimentoDieta.setPrefWidth(200);
		clmn_dieta.setCellValueFactory(new PropertyValueFactory<Dieta, String>("nombre_dieta"));
		clmn_descDieta.setCellValueFactory(new PropertyValueFactory<Dieta, String>("desc_dieta"));
		clmn_alimentoDieta.setCellValueFactory(new PropertyValueFactory<Dieta, Alimento>("nombre_alimento"));
		tbvDietas.getColumns().addAll(clmn_dieta, clmn_descDieta, clmn_alimentoDieta);
		tbvDietas.setItems(oblListaDietaTbv);

		conexion.cerrarConexion();
		llenarFormularioSeleccion();
	}
	
    @FXML
    void btnActualizar(ActionEvent event) {
    	tpDieta.getSelectionModel().select(tabVerDietas);
		btnActualizarB.setVisible(false);
		tabVerDietas.setDisable(false);
		if (cmbAlimentoDieta.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un alimento");
			cmbAlimentoDieta.requestFocus();
		} else {				
			actualizarDieta();
			limpiarDieta();
			Mensaje.informacion("Actualizar Registro", "Clase actualizada");			
		}
    }
	
	@FXML
	void btnAgregarAlimento(ActionEvent event) {
		if (txtNombreAlimento.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre");
			txtNombreAlimento.requestFocus();
		} else if (txtDescAlimento.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la descripcion");
			txtDescAlimento.requestFocus();
		} else {
			guardarAlimento();
		}
	}

	@FXML
	void btnAgregarAlimentoDieta(ActionEvent event) {
		if (txtNombreDieta.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre");
			txtNombreAlimento.requestFocus();
		} else if (txtDescDieta.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la descripcion");
			txtDescAlimento.requestFocus();
		} else if (cmbAlimentoDieta.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un alimento");
			cmbAlimentoDieta.requestFocus();
		} else {
			if(banderaDetDieta == 0) {
				guardarDieta();
				guardarDetalleDA();
				banderaDetDieta = 1;
			} else {
				guardarDetalleDA();
			}			
			banderaDieta = 1;
			tbvAlimentoDieta.setItems(null);
			llenarTableAlimentoDieta();
		}
	}

	@FXML
	void btnAgregarDieta(ActionEvent event) {
		if (txtNombreDieta.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el nombre");
			txtNombreAlimento.requestFocus();
		} else if (txtDescDieta.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la descripcion");
			txtDescAlimento.requestFocus();
		} else if (cmbAlimentoDieta.getValue() == null) {
			Mensaje.error("Campo Vacio", "Seleccione un alimento");
			cmbAlimentoDieta.requestFocus();
		} else {
			Mensaje.informacion("Guardar Registro", "Dieta agregada");
			if (banderaDieta == 1) {
				limpiarDieta();
				banderaDieta = 0;
			} else {
				guardarDieta();
				limpiarDieta();
			}			
		}
		banderaDieta = 0;
	}

	@FXML
	void btnBuscarDieta(ActionEvent event) {

	}

	@FXML
	void btnCancelarAlimento(ActionEvent event) {

	}

	@FXML
	void btnCancelarDieta(ActionEvent event) {
		if (banderaDieta == 0) {
			limpiarDieta();			
		} else {
			conexion.establecerConexion();
			Dieta dieta = new Dieta(Dieta.obtenerUltimaDieta(conexion.getConnection()), txtNombreDieta.getText(),
					txtDescDieta.getText());
			int noReg = dieta.cancelarDieta(conexion.getConnection());
			conexion.cerrarConexion();
			banderaDieta = 0;
			limpiarDieta();
		}
		tpDieta.getSelectionModel().select(tabVerDietas);
		btnActualizarB.setVisible(false);
		tabVerDietas.setDisable(false);
		btnAgregarAl.setVisible(true);
		tbvAlimentoDieta.setItems(null);
	}

	@FXML
	void btnEliminarDieta(ActionEvent event) {
		System.out.println(clvDieta);
		conexion.establecerConexion();
		Dieta dieta = new Dieta();
		try {
			if (Mensaje.confirmar("Confirmacion", "Desea eliminar la clase?") == 1) {
				int noReg = dieta.eliminarDieta(conexion.getConnection(), clvDieta);
				tbvDietas.refresh();
			}
		} catch (Exception e) {

		}
		conexion.cerrarConexion();
	}

	@FXML
	void btnVerDieta(ActionEvent event) {
		tpDieta.getSelectionModel().select(tabDietas);
		btnActualizarB.setVisible(true);
		tabVerDietas.setDisable(true);
		btnAgregarAl.setVisible(false);
		llenarTableAlimentoDieta();
	}

	private void guardarAlimento() {
		try {
			conexion.establecerConexion();
			Alimento alimento = new Alimento(txtNombreAlimento.getText(), txtDescAlimento.getText());
			int noReg = alimento.guardarAlimento(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Alimento Almacenado Correctamente");
				oblListaAlimentoTbv.add(alimento);
				oblListaAlimentoCmb.add(alimento);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
		}
		conexion.cerrarConexion();
	}
	
	private void actualizarDieta() {
		conexion.establecerConexion();
		Dieta dieta = new Dieta();
		dieta.setClv_dieta(clvDieta);
		dieta.setNombre_dieta(txtNombreDieta.getText());
		dieta.setDesc_dieta(txtDescDieta.getText());
		int noReg = dieta.actualizarDieta(conexion.getConnection());
		if (noReg != 0) {
			Mensaje.informacion("Actualizar Registro", "Dieta Actualizada Correctamente");
		} else {
			Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
		}
		conexion.cerrarConexion();
	}	
	
	private void guardarDieta() {
		try {
			conexion.establecerConexion();
			Dieta dieta = new Dieta(txtNombreDieta.getText(), txtDescDieta.getText());
			int noReg = dieta.guardarDieta(conexion.getConnection());
			if (noReg != 0) {
				oblListaDietaTbv.add(dieta);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos d");
		}
		conexion.cerrarConexion();
	}

	private void guardarDetalleDA() {
		try {
			conexion.establecerConexion();
			DetalleDietaAlimento dda = new DetalleDietaAlimento(0, Dieta.obtenerUltimaDieta(conexion.getConnection()),
					cmbAlimentoDieta.getSelectionModel().getSelectedItem());
			int noReg = dda.guardarDetalleDietaAlim(conexion.getConnection());
			if (noReg != 0) {
				Mensaje.informacion("Guardar Registro", "Alimento agregado a dieta");
				oblListaDetDietaATbv.add(dda);
			}
		} catch (Exception e) {
			Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos dda");
		}
		conexion.cerrarConexion();
	}

	private void llenarTableAlimentoDieta() {
		conexion.establecerConexion();

		DetalleDietaAlimento.lleanarTableDda(conexion.getConnection(), oblListaDetDietaATbv,
				Dieta.obtenerUltimaDieta(conexion.getConnection()));

		clmnDieta_dda = new TableColumn<>();
		clmnClv_dda = new TableColumn<>();
		clmnDieta_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento, Dieta>("dieta"));
		clmnClv_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento, Number>("clv_dda"));
		clmnAlimento_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento, Alimento>("clv_alimento"));
		tbvAlimentoDieta.setItems(oblListaDetDietaATbv);

		conexion.cerrarConexion();
	}	

	private void llenarFormularioSeleccion() {
		tbvDietas.getSelectionModel().selectedItemProperty()
		.addListener((ObservableValue<? extends Dieta> observable, Dieta oldValue, Dieta newValue) -> {
			clvDieta = newValue.getClv_dieta();
			txtNombreDieta.setText(newValue.getNombre_dieta());
			txtDescDieta.setText(newValue.getDesc_dieta());			
			cmbAlimentoDieta.setValue(newValue.getNombre_alimento());
		});
	}

	private void limpiarDieta() {
		txtNombreDieta.setText("");
		txtDescDieta.setText("");
		cmbAlimentoDieta.setValue(null);
	}

}
