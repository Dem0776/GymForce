package com.gymforce.controlador;


import java.io.IOException;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Mensaje;
import com.gymforce.modelo.Producto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;



public class FXMLagregarProducto {
    ConexionMySQL conexion;
	
	 @FXML
	    private AnchorPane productos2;

    @FXML
    private JFXTextField btndescripcion;

    @FXML
    private JFXTextField btnprecio;

    @FXML
    private JFXTextField btnnombreproducto;

    @FXML
    private JFXTextField btnmarca;

    @FXML
    private JFXTextField txtbuscarproducto;

    @FXML
    private JFXButton btnirbuscarproducto;

    @FXML
    void btnAgregar(ActionEvent event) {
    	try {
    	   Double precio1=Double.parseDouble(btnprecio.getText());
    	}catch(Exception e){
    		Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese Precio correctamente");
            btnprecio.requestFocus();
    		
    	}
    	
         String nombre= btnnombreproducto.getText();
         String descripcion=btndescripcion.getText();
         Double precio=Double.parseDouble(btnprecio.getText());
        String marca=btnmarca.getText();
        
       
        if(nombre.trim().length() == 0){
            Mensaje.error("Campo Vacio","Ingrese el nombre del producto");;
            btnnombreproducto.requestFocus();
        }else if(descripcion.trim().length() == 0){
            Mensaje.error("Campo Vacio", "Ingrese descripcion del producto");
            btndescripcion.requestFocus();
        }else if(precio == null || precio<0){
            Mensaje.error("Campo Vacio o Valor no Valido", "Ingrese Precio correctamente");
            btnprecio.requestFocus();
    }else if(marca.trim().length() == 0) {
        Mensaje.error("Campo Vacio", "Ingrese Marca");
        btnmarca.requestFocus();
        
        
    }else {
    	conexion.establecerConexion();
    //	Producto producto = new Producto(nombre, descripcion, precio,?, marca);
    
    }}
    @FXML
    void btnEliminar(ActionEvent event) {

    }
     
    
    
   
}