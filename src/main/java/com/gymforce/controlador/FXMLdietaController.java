package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.Alimento;
import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Dieta;
import com.gymforce.modelo.Mensaje;
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
import javafx.scene.layout.VBox;

public class FXMLdietaController implements Initializable {
	
	private ConexionMySQL conexion;

	private ObservableList<Alimento> oblListaAlimentoTbv;
	private ObservableList<Alimento> oblListaAlimentoCmb;

    @FXML
    private JFXTextField txtBuscarDieta;

    @FXML
    private JFXTreeTableView<Dieta> tbvViewDieta;    

    @FXML
    private VBox txtNombreDieta;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtDescDieta;

    @FXML
    private JFXComboBox<Alimento> cmbAlimentoDieta;

    @FXML
    private JFXButton btnAgregarAlimentoDieta;

    @FXML
    private JFXTreeTableView<?> tbvAlimentoDieta;

    @FXML
    private JFXTextField txtNombreAlimento;

    @FXML
    private JFXTextField txtDescAlimento;

    @FXML
    private JFXTreeTableView<Alimento> tbvAlimento;
    private TreeTableColumn<Alimento, String> clmnNombre_alimento;
	private TreeTableColumn<Alimento, String> clmnDesc_alimento;

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

    }

    @FXML
    void btnAgregarDieta(ActionEvent event) {

    }

    @FXML
    void btnBuscarDieta(ActionEvent event) {

    }

    @FXML
    void btnCancelarAlimento(ActionEvent event) {

    }

    @FXML
    void btnCancelarDieta(ActionEvent event) {

    }

    @FXML
    void btnEliminarDieta(ActionEvent event) {

    }

    @FXML
    void btnVerDieta(ActionEvent event) {

    }

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		oblListaAlimentoTbv = FXCollections.observableArrayList();
		oblListaAlimentoCmb = FXCollections.observableArrayList();
		
		Alimento.llenarTableAlimento(conexion.getConnection(), oblListaAlimentoTbv);
		Alimento.llenarComboAlimento(conexion.getConnection(), oblListaAlimentoCmb);
		
		cmbAlimentoDieta.setItems(oblListaAlimentoCmb);
		
		clmnNombre_alimento = new TreeTableColumn<>("Alimento");
		clmnDesc_alimento = new TreeTableColumn<>("Descripcion");
		clmnNombre_alimento.setPrefWidth(150);
		clmnDesc_alimento.setPrefWidth(150);
		clmnNombre_alimento.setCellValueFactory(new TreeItemPropertyValueFactory<Alimento, String>("nombre_alimento"));
		clmnDesc_alimento.setCellValueFactory(new TreeItemPropertyValueFactory<Alimento, String>("desc_alimento"));
		final TreeItem<Alimento> root = new RecursiveTreeItem<Alimento>(oblListaAlimentoTbv, RecursiveTreeObject::getChildren);
		tbvAlimento.getColumns().addAll(clmnNombre_alimento, clmnDesc_alimento);
		tbvAlimento.setRoot(root);
		tbvAlimento.setShowRoot(false);

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

}


