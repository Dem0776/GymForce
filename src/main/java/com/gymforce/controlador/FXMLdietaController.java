package com.gymforce.controlador;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.gymforce.modelo.Alimento;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.DetalleDietaAlimento;
import com.gymforce.modelo.Dieta;
import com.gymforce.modelo.Mensaje;
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
			guardarDieta();
			guardarDetalleDA();
			banderaDieta = 1;
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
			limpiarDieta();
		}		
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
			Dieta dieta = new Dieta(Dieta.obtenerUltimaDieta(conexion.getConnection()), 
					txtNombreDieta.getText(), 
					txtDescDieta.getText());		
			int noReg = dieta.cancelarDieta(conexion.getConnection());
			conexion.cerrarConexion();
			banderaDieta = 0;
			limpiarDieta();
		}		
	}

	@FXML
	void btnEliminarDieta(ActionEvent event) {

	}

	@FXML
	void btnVerDieta(ActionEvent event) {		
		tbvDietas.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Dieta> observable, Dieta oldValue, Dieta newValue) -> {
			txtNombreDieta.setText(newValue.getNombre_dieta());
			txtDescDieta.setText(newValue.getDesc_dieta());
			cmbAlimentoDieta.setValue(newValue.getNombre_alimento());
        });
		
		tpDieta.getSelectionModel().select(tabDietas);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		
		
		oblListaAlimentoTbv = FXCollections.observableArrayList();
		oblListaAlimentoCmb = FXCollections.observableArrayList();
		oblListaDietaTbv = FXCollections.observableArrayList();		
		oblListaDetDietaATbv = FXCollections.observableArrayList();
		
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
			DetalleDietaAlimento dda = new DetalleDietaAlimento(0, 
					Dieta.obtenerUltimaDieta(conexion.getConnection()), 
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
				
		DetalleDietaAlimento.lleanarTableDda(conexion.getConnection(), 
				oblListaDetDietaATbv,
				Dieta.obtenerUltimaDieta(conexion.getConnection()));
		
		clmnDieta_dda = new TableColumn<>();
		clmnClv_dda = new TableColumn<>();
		clmnDieta_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento, Dieta>("dieta"));
		clmnClv_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento,Number>("clv_dda"));
		clmnAlimento_dda.setCellValueFactory(new PropertyValueFactory<DetalleDietaAlimento, Alimento>("clv_alimento"));
		tbvAlimentoDieta.setItems(oblListaDetDietaATbv);
		
		conexion.cerrarConexion();
	}
	
	private void limpiarDieta() {
		txtNombreDieta.setText("");
		txtDescDieta.setText("");
		cmbAlimentoDieta.setValue(null);
	}

}
