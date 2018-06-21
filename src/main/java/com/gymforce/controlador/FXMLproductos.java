package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Marca;

import com.gymforce.modelo.Producto;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.TreeTableColumn;

import javafx.scene.layout.AnchorPane;

public class FXMLproductos implements Initializable  {

    @FXML
    private AnchorPane productos;

    @FXML
    private JFXTextField txtBuscarProducto;

    
    private ConexionMySQL conexion;
  
      private ObservableList<Producto>listproducto;
      
      @FXML
     // private JFXTreeTableView<Producto> tblViewProductos;
      private TreeTableColumn<Producto, String> clmnnombre_producto;
  	private TreeTableColumn<Producto, String> clmndesc_producto;
  	private TreeTableColumn<Producto, Double> clmnprecioActual_producto;
  	private TreeTableColumn<Producto,Integer> clmnexistencia_producto;
  	private TreeTableColumn<Producto,Marca>clmnMarca ;
      
      
      
      
      @FXML
      void btnAgregar(ActionEvent event) {

      }


      @FXML
      void btnBuscarProducto(ActionEvent event) {

      }

      @FXML
      void btnEliminar(ActionEvent event) {

      }

      @FXML
      void btnVentanaAdministrarProducto(ActionEvent event) throws IOException {
      	Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLagregarProducto.fxml"));
  		productos.getChildren().removeAll();
  	    productos.getChildren().setAll(fxml);
      
      }

    
    
    //////////////////

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		/*Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLproductos.fxml"));
			productos.getChildren().removeAll();
		    productos.getChildren().setAll(fxml);
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		conexion= new ConexionMySQL();
		
		conexion.establecerConexion();
		
		listproducto=FXCollections.observableArrayList();
		
		Producto.llenarTableProducto(conexion.getConnection(), listproducto);
		
		clmnnombre_producto= new TreeTableColumn<>("Nombre");
		clmndesc_producto= new TreeTableColumn<>("Descripcion");
		clmnexistencia_producto=new TreeTableColumn<>("Existencia");
		clmnprecioActual_producto= new TreeTableColumn<>("Precio");
		clmnMarca= new TreeTableColumn<>("Marca");
		
		clmnnombre_producto.setCellValueFactory(new TreeItemPropertyValueFactory<Producto,String>("nombre_producto"));
		clmndesc_producto.setCellValueFactory(new TreeItemPropertyValueFactory<Producto,String>("desc_producto"));
		clmnexistencia_producto.setCellValueFactory(new TreeItemPropertyValueFactory<Producto,Integer>("existencia_producto"));
		clmnprecioActual_producto.setCellValueFactory(new TreeItemPropertyValueFactory<>("precioActual_producto"));;
		clmnMarca.setCellValueFactory(new TreeItemPropertyValueFactory<Producto,Marca>("clv_marca"));
		
		final TreeItem<Producto> root= new RecursiveTreeItem<Producto>(listproducto,RecursiveTreeObject::getChildren);
		
		tblViewProductos.setRoot(root);
		tblViewProductos.setShowRoot(false);
		
		
		tblViewProductos.getColumns().addAll(clmnnombre_producto,
				clmndesc_producto,
				clmnexistencia_producto,
				clmnprecioActual_producto,
				clmnMarca
				);
		
		conexion.cerrarConexion();		
		
		
		
		
	}
*/
    
	}  
}