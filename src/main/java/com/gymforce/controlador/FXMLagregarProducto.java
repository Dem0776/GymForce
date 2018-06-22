package com.gymforce.controlador;



import java.net.URL;



import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Marca;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Producto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;



public class FXMLagregarProducto implements Initializable {
    ConexionMySQL conexion;
    private ObservableList<Marca> listaMar;
	 @FXML
	    private AnchorPane productos2;
	 @FXML
	    private JFXButton btnCancelarp;

    @FXML
    private JFXTextField btndescripcion;

    @FXML
    private JFXTextField btnprecio;

    @FXML
    private JFXTextField btnnombreproducto;

    @FXML
    private JFXComboBox<Marca> cmbMarca;

    @FXML
    private JFXButton btnagregarp;
    @FXML
    private JFXButton btnactualizarp;

    @FXML
    private JFXButton btneliminarp;
    
    @FXML
    private JFXTextField txtbuscarproducto;

    @FXML
    private JFXTextField txtCantidadProducto;
    
    @FXML
    private JFXButton btnirbuscarproducto;

    @FXML
    void btnAgregar(ActionEvent event) {
    	conexion = new ConexionMySQL();
    	
    	 int bandera=0;			
			try {
				String estatus = cmbMarca.getValue().toString();
				int varia= Integer.parseInt(txtCantidadProducto.getText());
			} catch (Exception e) {
				
       bandera=1;
			}
         
        
       
        if(btnnombreproducto.getText().trim().length() == 0){
            Mensaje.error("Campo Vacio","Ingrese el nombre del producto");;
            btnnombreproducto.requestFocus();
        }else if(btndescripcion.getText().trim().length() == 0){
            Mensaje.error("Campo Vacio", "Ingrese descripcion del producto");
            btndescripcion.requestFocus();
        }else if(btnprecio.getText().trim().length() == 0||Double.parseDouble(btnprecio.getText())<0){
            Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese Precio correctamente");
            btnprecio.requestFocus();
        }else if(txtCantidadProducto.getText().trim().length()==0||Integer.parseInt(txtCantidadProducto.getText())<0) {
        	 Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese cantidad de producto correctamente");
             txtCantidadProducto.requestFocus();
    }else if(bandera==1) {
		Mensaje.error("Sin seleccion", "Ingrese la marca");
		cmbMarca.requestFocus();
		
        
        
    }else {
    	
    	Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Continuar guardando");
		dialogo.setHeaderText(null);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText("EN REALIDAD DESEA GUARADAR LOS DATOS ");
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				
				conexion.establecerConexion();
				
				Producto prod= new Producto(btnnombreproducto.getText(),
						btndescripcion.getText(),
						Double.parseDouble(btnprecio.getText()),
						Integer.parseInt(txtCantidadProducto.getText()));
				int noReg = prod.GuardarProducto(conexion.getConnection());
				if (noReg != 0) {
					Mensaje.informacion("Guardar Registro", "Produco Almacenado Correctamente");
					btnnombreproducto.setText("");
					btndescripcion.setText("");
					btnprecio.setText("");
					txtCantidadProducto.setText("");
					cmbMarca.setValue(null);
					

					conexion.cerrarConexion();
				}	
				
					
				
				//Producto prod= new 
					conexion.cerrarConexion();
				}
			catch (Exception e) {
				Mensaje.error("Valores no Validos", "Verifique que los datos sean correctos");
			}	
		}
		
		}
        conexion.cerrarConexion();
    }
    @FXML
    void btnEliminar(ActionEvent event) {
    	Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Eliminar Registro");
		dialogo.setHeaderText(null);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText("EN REALIDAD DESEA ELIMINAR EL REGISTRO ");
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {
    	conexion = new ConexionMySQL();
    	conexion.establecerConexion();
    	Producto prod= new Producto();
    	int valor=prod.eliminaProducto(conexion.getConnection(), txtbuscarproducto.getText());
    	if(valor==0) {
       	 Mensaje.error("NO ENCONTRADO", "No pudo eliminarse--> "+txtbuscarproducto.getText());
        }else {
       	 Mensaje.informacion("ELIMINADO","Eliminado con exito--> "+txtbuscarproducto.getText());
       	// JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
       	 habilitar();
        }  
		}else {
			
		}
    	conexion.cerrarConexion();
    	
    	
    }

    @FXML
    void btnActualizar(ActionEvent event) {
    	
    	int bandera=0;			
		try {
			String estatus = cmbMarca.getValue().toString();
			int varia= Integer.parseInt(txtCantidadProducto.getText());
		} catch (Exception e) {
			
   bandera=1;
		}
     
    
   
    if(btnnombreproducto.getText().trim().length() == 0){
        Mensaje.error("Campo Vacio","Ingrese el nombre del producto");;
        btnnombreproducto.requestFocus();
    }else if(btndescripcion.getText().trim().length() == 0){
        Mensaje.error("Campo Vacio", "Ingrese descripcion del producto");
        btndescripcion.requestFocus();
    }else if(btnprecio.getText().trim().length() == 0||Double.parseDouble(btnprecio.getText())<0){
        Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese Precio correctamente");
        btnprecio.requestFocus();
    }else if(txtCantidadProducto.getText().trim().length()==0||Integer.parseInt(txtCantidadProducto.getText())<0) {
    	 Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese cantidad de producto correctamente");
         txtCantidadProducto.requestFocus();
}else if(bandera==1) {
	Mensaje.error("Sin seleccion", "Ingrese la marca");
	cmbMarca.requestFocus();
	
    
    
}else {
    	
    	
    	Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Actualizar Registro");
		dialogo.setHeaderText(null);
		dialogo.initStyle(StageStyle.UTILITY);
		dialogo.setContentText("EN REALIDAD DESEA ACTUALIZAR EL REGISTRO ");
		Optional<ButtonType> result = dialogo.showAndWait();
		if (result.get() == ButtonType.OK) {
    	
    	
    	conexion = new ConexionMySQL();
    	conexion.establecerConexion();
    	Producto prod= new Producto();
    	int valor=prod.actualizaProducto(conexion.getConnection(), btnnombreproducto.getText(), btndescripcion.getText(),btnprecio.getText(),txtCantidadProducto.getText());
    	
    	if(valor==0) {
       	 Mensaje.error("NO ENCONTRADO", "No pudo actualizarse--> "+txtbuscarproducto.getText());
        }else {
       	 Mensaje.informacion("ACTUALIZADO","Actualizado con exito--> "+txtbuscarproducto.getText());
       	// JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
       	 habilitar();
        }  
		}
}
    	conexion.cerrarConexion();
    	
    
    	
    	
    	
    	
    	
    	
    }
    
    
    
    @FXML
    void btnBuscarProducto(ActionEvent event) {
   inhabilitar();
   conexion = new ConexionMySQL();
	
   if(txtbuscarproducto.getText().trim().length()==0) {
	   Mensaje.error("Campo vacio", "Llene criterio de busqueda");
   }
   
   else {
	conexion.establecerConexion();   
     Producto prod = new Producto();
     String valor=prod.buscaProducto(conexion.getConnection(), txtbuscarproducto.getText());
     String desc=prod.buscaDescripcion(conexion.getConnection(), txtbuscarproducto.getText());
     String precio=prod.buscaPrecio(conexion.getConnection(), txtbuscarproducto.getText());
     String existencia=prod.buscaCantidad(conexion.getConnection(), txtbuscarproducto.getText());
     if(valor.equals(null)||valor.equals("")) {
    	 Mensaje.informacion("NO ENCONTRADO", "No se encontro producto--> "+txtbuscarproducto.getText());
     }else {
    	 btnnombreproducto.setText(valor);
    	 btndescripcion.setText(desc);
    	 btnprecio.setText(precio);
    	 txtCantidadProducto.setText(existencia);
    	 
    	// JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
     }
   
   
   
    }
    }
    
    @FXML
    void btnCancelar(ActionEvent event) {
   habilitar();
    }
    
    public void limpiar(){
           inhabilitar();
    }
    public void inhabilitar(){
        btnagregarp.setDisable(true);
        btnactualizarp.setDisable(false);
        btneliminarp.setDisable(false);
        btnCancelarp.setDisable(false);
    }
    public void habilitar(){
        btnagregarp.setDisable(false);
        btnactualizarp.setDisable(true);
        btneliminarp.setDisable(true);
        btnCancelarp.setDisable(true);
        btnirbuscarproducto.setDisable(false);
        btndescripcion.setText("");
        btnnombreproducto.setText("");
        btnprecio.setText("");
        txtCantidadProducto.setText("");
        cmbMarca.setValue(null);
        
    }

    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	habilitar();	
		conexion = new ConexionMySQL();
		conexion.establecerConexion();
        listaMar = FXCollections.observableArrayList();
        
		
		
		Marca.llenarComboMarca(conexion.getConnection(),listaMar);
	
		cmbMarca.setItems(listaMar);
		
		conexion.cerrarConexion();
		
	}
     
    
    
   
}