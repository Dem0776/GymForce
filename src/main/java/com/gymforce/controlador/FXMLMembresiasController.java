package com.gymforce.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Membresia;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXButton;
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

public class FXMLMembresiasController implements Initializable{
	
	private ConexionMySQL conexion;

	private ObservableList<Membresia> listaMembresia;

    @FXML
    private JFXTreeTableView<Membresia> tblViewMembresias;
    
    private TreeTableColumn<Membresia,String> clmndesc_membresia;
    private TreeTableColumn<Membresia,Double> clmnprecio_membresia;
    private TreeTableColumn<Membresia,Number> clmndiasValidos_membresia;

    @FXML
    private JFXTextField txtBuscarMembresia;
    
    @FXML
    private JFXButton btnVerMembresia;

    @FXML
    private JFXButton btnModificarMembresiaTable;

    @FXML
    private JFXTextField txtDescripcion;

    @FXML
    private JFXTextField txtPrecio;

    @FXML
    private JFXTextField txtDiasValidos;

    @FXML
    private JFXButton btnAgregarMembresa;

    @FXML
    private JFXButton btnModicarMembresia;

    @FXML
    private JFXButton btnEliminarMembresia;
    @FXML
    void btnAgregar(ActionEvent event) {
		if (txtDescripcion.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa la descripcion de la membresia");
			txtDescripcion.requestFocus();
		} else if (txtPrecio.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa el precio de la membresia");
			txtPrecio.requestFocus();
		} else if (txtDiasValidos.getText().trim().length() == 0) {
			Mensaje.error("Campo Vacio", "Ingresa los dias validos de la membresia");
			txtDiasValidos.requestFocus();
		} else {
			try {
				conexion.establecerConexion();
                Membresia miMembresia = new Membresia(txtDescripcion.getText(),
                        Double.valueOf(txtPrecio.getText()),
                        Integer.valueOf(txtDiasValidos.getText()));
                int noReg = miMembresia.guardarMembresia(conexion.getConnection());
                if (noReg != 0) {
                    Mensaje.informacion("Guardar Registro", "Membresia Almacenada Correctamente");
                    listaMembresia.add(miMembresia);
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
    void btnModificar(ActionEvent event) {

    }

    @FXML
    void btnVer(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaMembresia = FXCollections.observableArrayList();
		
		Membresia.llenarTableMembresia(conexion.getConnection(), listaMembresia);
		
		clmndesc_membresia = new TreeTableColumn<>("Descripcion");
		clmnprecio_membresia = new TreeTableColumn<>("Precio");
		clmndiasValidos_membresia = new TreeTableColumn<>("Dias Validos");
		clmndesc_membresia.setPrefWidth(150);
		clmnprecio_membresia.setPrefWidth(150);
		clmndiasValidos_membresia.setPrefWidth(150);
		
		clmndesc_membresia.setCellValueFactory(new TreeItemPropertyValueFactory<Membresia,String>("desc_membresia"));
		clmnprecio_membresia.setCellValueFactory(new TreeItemPropertyValueFactory<Membresia,Double>("precio_membresia"));
		clmndiasValidos_membresia.setCellValueFactory(new TreeItemPropertyValueFactory<Membresia,Number>("diasValidos_membresia"));
		final TreeItem<Membresia> root = new RecursiveTreeItem<Membresia>(listaMembresia, RecursiveTreeObject::getChildren);
		tblViewMembresias.getColumns().addAll(clmndesc_membresia, clmnprecio_membresia, clmndiasValidos_membresia);
		tblViewMembresias.setRoot(root);
		tblViewMembresias.setShowRoot(false);
		
		conexion.cerrarConexion();
		seleccionarColumnaTalbe();
	}
	
	
	
	private void seleccionarColumnaTalbe() {
		tblViewMembresias.getSelectionModel().selectedIndexProperty().addListener((observable, oldCount, newCount) -> {
			System.out.println("Hola");
		});
	}

}
