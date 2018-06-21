package com.gymforce.controlador;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Marca;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Producto;
import com.gymforce.modelo.Mobiliario;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

public class FXMLinventarioController implements Initializable {
	private ConexionMySQL conexion;
	private ObservableList<Marca> listaMarca;
	private ObservableList<Producto> listproducto;
	private ObservableList<Mobiliario> listmobiliario;
	@FXML
    private JFXTextField apoyo;

	@FXML
	private TableView<Producto> tblProductos;
	 @FXML
	    private TableColumn<Producto, String> clmnnombreProducto;
	 
	    @FXML
	    private TableColumn<Producto, String> clmnDescripcionProducto;

	    @FXML
	    private TableColumn<Producto,String> clmnPrecioProducto;

	    @FXML
	    private TableColumn<Producto, String> clmnExistenciaProducto;

	    @FXML
	    private TableColumn<Marca,String> clmnMarcaProd;


	
	
	@FXML
	private TableView<Mobiliario> tblMobiliario;
	   @FXML
	    private TableColumn<Mobiliario, String> clmnDescripcionMobiliaro;

	    @FXML
	    private TableColumn<Mobiliario,Double> clmnCostoMobiliario;

	    @FXML
	    private JFXTextField apoyo2;

	@FXML
	private JFXTextField txtMarca;

	@FXML
	private TableView<Marca> tblvwMarca;
	  @FXML
	    private TableColumn<Marca,String> clmnMarca;

	@FXML
	private AnchorPane productos1;
	@FXML
	private AnchorPane mobiliario1;

	@FXML
	private JFXTextField txtBuscarProducto;

	@FXML
	private JFXTextField txtBuscarMobiliario;

	@FXML
	void btnAgregarMarca(ActionEvent event) {
		conexion = new ConexionMySQL();

		if (txtMarca.getText().trim().length() == 0) {
			Mensaje.error("Campos Vacios", "Ingrese una marca");
			txtMarca.requestFocus();
		} else {

			Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Continuar guardando");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA GUARADAR LOS DATOS ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					conexion.establecerConexion();
					Marca mob = new Marca(txtMarca.getText());
					int noReg = mob.guardarMarca(conexion.getConnection());
					if (noReg != 0) {
						Mensaje.informacion("Guardar Registro", "Marca Almacenada Correctamente");
						txtMarca.setText("");
						listaMarca.add(mob);

						conexion.cerrarConexion();
					}
				} catch (Exception e) {
					Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
				}
			}
		}
	}

	@FXML
	void btnEliminarMarca(ActionEvent event) {
		 Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Eliminar Registro");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL REGISTRO ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
	    	conexion = new ConexionMySQL();
	    	conexion.establecerConexion();
	    	Marca mob= new Marca();
	    	int valor=mob.eliminaMarca(conexion.getConnection(),apoyo.getText());
	    	if(valor==0) {
	       	 Mensaje.error("NO ENCONTRADO", "No pudo eliminarse--> "+apoyo2.getText());
	        }else {
	       	 Mensaje.informacion("ELIMINADO","Eliminado con exito--> "+apoyo2.getText());
	       	// JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
	       	 
	        }  
			}else {
				
			}
	    	conexion.cerrarConexion();
	     
	}

	@FXML
	void btnAgregar(ActionEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLagregarMobiliario.fxml"));
		mobiliario1.getChildren().removeAll();
		mobiliario1.getChildren().setAll(fxml);

	}

	@FXML
	void btnBuscarMobiliario(ActionEvent event) {

	}

	@FXML
	void btnBuscarProducto(ActionEvent event) {
		String busqueda = txtBuscarProducto.getText();
		if (busqueda.trim().length() == 0) {
			Mensaje.error("Busqueda vacia", "Ingrese el nombre del producto a buscar");

		} else {
			conexion.establecerConexion();
			Producto mob = new Producto(busqueda);

		}
	}
	  @FXML
	    void EliminarMobiliario(ActionEvent event) {
		  Alert dialogo = new Alert(AlertType.CONFIRMATION);
			dialogo.setTitle("Eliminar Registro");
			dialogo.setHeaderText(null);
			dialogo.initStyle(StageStyle.UTILITY);
			dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL REGISTRO ");
			Optional<ButtonType> result = dialogo.showAndWait();
			if (result.get() == ButtonType.OK) {
	    	conexion = new ConexionMySQL();
	    	conexion.establecerConexion();
	    	Mobiliario mob= new Mobiliario();
	    	int valor=mob.eliminaMobiliario(conexion.getConnection(),apoyo.getText());
	    	if(valor==0) {
	       	 Mensaje.error("NO ENCONTRADO", "No pudo eliminarse--> "+apoyo.getText());
	        }else {
	       	 Mensaje.informacion("ELIMINADO","Eliminado con exito--> "+apoyo.getText());
	       	// JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
	       	 
	        }  
			}else {
				
			}
	    	conexion.cerrarConexion();
	     

	    }
	@FXML
	void btnEliminar(ActionEvent event) {
		
	}

	@FXML
	void btnVentanaAdministrarProducto(ActionEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLagregarProducto.fxml"));
		productos1.getChildren().removeAll();
		productos1.getChildren().setAll(fxml);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		conexion = new ConexionMySQL();

		conexion.establecerConexion();

		// Inicializar Observable ArrayList
		listaMarca = FXCollections.observableArrayList();

		
		listaMarca = FXCollections.observableArrayList();
		listproducto = FXCollections.observableArrayList();
		listmobiliario = FXCollections.observableArrayList();
		
		
		Marca.extraerDatosMarca(conexion.getConnection(), listaMarca);
		Producto.llenarTableProducto(conexion.getConnection(), listproducto);
		Mobiliario.llenarTableMobiliario(conexion.getConnection(), listmobiliario);
		tblProductos.setItems(listproducto);
		tblMobiliario.setItems(listmobiliario);
		tblvwMarca.setItems(listaMarca);

		
		clmnnombreProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre_producto"));
		clmnDescripcionProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("desc_producto"));
		clmnPrecioProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("precioActual_producto"));
		
		clmnExistenciaProducto.setCellValueFactory(new PropertyValueFactory<Producto,String>("existencia_producto"));
		
		clmnMarcaProd.setCellValueFactory(new PropertyValueFactory<Marca,String>("desc_marca"));

       
		clmnDescripcionMobiliaro.setCellValueFactory(new PropertyValueFactory<Mobiliario,String>("desc_mobiliario"));
		clmnCostoMobiliario.setCellValueFactory(new PropertyValueFactory<Mobiliario,Double>("costo_mobiliario"));
		

		clmnMarca.setCellValueFactory(new PropertyValueFactory<Marca,String>("desc_marca"));
		

		
		


		
		eventosTableView();

		
		conexion.cerrarConexion();
	}
	
	
	
	private void eventosTableView() {
        tblMobiliario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mobiliario>() {
         @Override
         public void changed(ObservableValue<? extends Mobiliario> observable, Mobiliario oldValue, Mobiliario registroSelect) {
            apoyo.setText(registroSelect.getDesc_mobiliario());
           
           // JOptionPane.showMessageDialog(null,"VALOR "+valor);
         }
     });
        
        
        tblvwMarca.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Marca>() {
            @Override
            public void changed(ObservableValue<? extends Marca> observable, Marca oldValue, Marca registroSelect) {
               apoyo.setText(registroSelect.getDesc_marca());
              
              // JOptionPane.showMessageDialog(null,"VALOR "+valor);
            }
        });   
 
 }

 

	

}