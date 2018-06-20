 package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Membresia;
import com.gymforce.modelo.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLMembresiasController implements Initializable{
	
	private ConexionMySQL conexion;

	private ObservableList<Membresia> listaMembresia;

    @FXML
    private AnchorPane membresiaAP;
	
	@FXML
    private TableView<Membresia> tblViewMembresias;

	@FXML private TableColumn<Membresia,String> clmndesc_membresia;
	@FXML private TableColumn<Membresia,Double> clmnprecio_membresia;
	@FXML private TableColumn<Membresia,Number> clmndiasValidos_membresia;
    
    @FXML
    private JFXTabPane MembresiasPrincipal;
    
    @FXML
    private Tab VerMembresias;
    
    @FXML
    private Tab AdministrarMembresias;

    @FXML
    private JFXTextField txtBuscarMembresia;
    
    @FXML
    private JFXButton btnVerMembresia;

    @FXML
    private FontAwesomeIconView iconRegresar;
    
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
	int contador=0;
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
                	Alert dialogo = new Alert(AlertType.CONFIRMATION);
            		dialogo.setTitle("Continuar Guardando");
            		dialogo.setHeaderText(null);
            		dialogo.initStyle(StageStyle.UTILITY);
            		dialogo.setContentText("EN REALIDAD DESEA GUARDAR LOS DATOS ");
            		Optional<ButtonType> result = dialogo.showAndWait();
            		if (result.get() == ButtonType.OK) {
            			listaMembresia.add(miMembresia);
            			validarMembresia();
            		}else {
            			validarMembresia();
            		}
                    
                }
			} catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}
			conexion.cerrarConexion();
		}
    }

    @FXML
    void btnEliminar(ActionEvent event) throws IOException {
    	 conexion.establecerConexion();
         int noReg = tblViewMembresias.getSelectionModel().getSelectedItem().eliminarMembresia(conexion.getConnection());
         if (noReg != 0) {        	
             Alert dialogo = new Alert(AlertType.CONFIRMATION);
     		dialogo.setTitle("Continuar Eliminacion");
     		dialogo.setHeaderText(null);
     		dialogo.initStyle(StageStyle.UTILITY);
     		dialogo.setContentText("EN REALIDAD DESEA ELIMINAR LOS DATOS ");
     		Optional<ButtonType> result = dialogo.showAndWait();
     		if (result.get() == ButtonType.OK) {
     			membresiaAP.getChildren().removeAll();
     	    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLMembresias.fxml"));
     	    	membresiaAP.getChildren().setAll(fxml);
     	    	validarMembresia();
     		}
         }else {
                 Mensaje.error("Eliminar Membresia", "Problemas al eliminar La Membresia, Intente de nuevo");
             }
         conexion.cerrarConexion();
    }

    @FXML
    void btnModificar(ActionEvent event) throws IOException {
    	conexion.establecerConexion();
    	Membresia miMembresia = new Membresia(txtDescripcion.getText(),
                Double.valueOf(txtPrecio.getText()),
                Integer.valueOf(txtDiasValidos.getText()));
        
        int noReg = miMembresia.actualizarMembresia(conexion.getConnection());
                if (noReg != 0) {        	
                    Alert dialogo = new Alert(AlertType.CONFIRMATION);
            		dialogo.setTitle("Continuar Actualizacion");
            		dialogo.setHeaderText(null);
            		dialogo.initStyle(StageStyle.UTILITY);
            		dialogo.setContentText("EN REALIDAD DESEA ACTUALIZAR LOS DATOS ");
            		Optional<ButtonType> result = dialogo.showAndWait();
            		if (result.get() == ButtonType.OK) {
            			membresiaAP.getChildren().removeAll();
            	    	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLMembresias.fxml"));
            	    	membresiaAP.getChildren().setAll(fxml);
            	    	validarMembresia();
            		}
                }else{
                    Mensaje.error("Actualizar Registro", "Problemas al Actualizar");
                }
        conexion.cerrarConexion();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		btnEliminarMembresia.setVisible(false);
		btnModicarMembresia.setVisible(false);
		iconRegresar.setVisible(false);
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
		listaMembresia = FXCollections.observableArrayList();
		
		Membresia.llenarTableMembresia(conexion.getConnection(), listaMembresia);
		
		tblViewMembresias.setItems(listaMembresia);
		
		clmndesc_membresia.setCellValueFactory(new PropertyValueFactory<Membresia,String>("desc_membresia"));
		clmnprecio_membresia.setCellValueFactory(new PropertyValueFactory<Membresia,Double>("precio_membresia"));
		clmndiasValidos_membresia.setCellValueFactory(new PropertyValueFactory<Membresia,Number>("diasValidos_membresia"));
	
		llenarFormularioSeleccion();
		conexion.cerrarConexion();
		
	}
	
	@FXML
    void tblVerMembresia(MouseEvent event) {	
    }

	private void llenarFormularioSeleccion() {
        tblViewMembresias.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Membresia> observable, Membresia oldValue, Membresia newValue) -> {
        	AdministrarMembresias.setDisable(true);
        	txtDescripcion.setText(newValue.getDesc_membresia());  
        	txtPrecio.setText(String.valueOf(newValue.getPrecio_membresia()));
        	txtDiasValidos.setText(String.valueOf(newValue.getDiasValidos_membresia()));
        	contador=1;
        });
    }
    @FXML
    void btnVer(ActionEvent event) {
    	if(contador==1) {
    	AdministrarMembresias.setDisable(false);
		MembresiasPrincipal.getSelectionModel().select(AdministrarMembresias);
		contador=0;
		deshabilitar();
    	}else{
    		Mensaje.error("Accion No Valida", "Primero Seleccione un Registro");
    	}
    }
    @FXML
    void IconRegresar(MouseEvent event) {
    	VerMembresias.setDisable(false);
    	MembresiasPrincipal.getSelectionModel().select(VerMembresias);
    	validarMembresia();
    }
    public void deshabilitar() {
    	btnAgregarMembresa.setVisible(false);
    	btnEliminarMembresia.setVisible(true);
    	btnModicarMembresia.setVisible(true);
    	VerMembresias.setDisable(true);
    	iconRegresar.setVisible(true);
    }
    public void validarMembresia() {
    	txtDescripcion.setText("");
    	txtDiasValidos.setText("");
    	txtPrecio.setText("");
    	btnAgregarMembresa.setVisible(true);
    	btnModicarMembresia.setVisible(false);
    	btnEliminarMembresia.setVisible(false);
    	iconRegresar.setVisible(false);
    }
    
}
