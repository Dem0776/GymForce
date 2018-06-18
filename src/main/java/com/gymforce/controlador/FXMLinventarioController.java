package com.gymforce.controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.modelo.ConexionMySQL;
import com.gymforce.modelo.Marca;
import com.gymforce.modelo.Producto;
import com.gymforce.modelo.Mobiliario;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;


	public class FXMLinventarioController implements Initializable {
		private ConexionMySQL conexion;
		  
		 @FXML
		    private AnchorPane productos1;
		 @FXML
		    private AnchorPane mobiliario1;

		 
		    @FXML
		    private JFXTextField txtBuscarProducto;
		    
		    private ObservableList<Producto>listproducto;
		    private ObservableList<Mobiliario>listmobiliario;

		    @FXML
		    private JFXTreeTableView<Producto> tblViewProductos;
		    private TreeTableColumn<Producto, String> clmnnombre_producto;
			private TreeTableColumn<Producto, String> clmndesc_producto;
			private TreeTableColumn<Producto, Double> clmnprecioActual_producto;
			private TreeTableColumn<Producto,Integer> clmnexistencia_producto;
			private TreeTableColumn<Producto,Marca>clmnMarca ;
		    
		    
		    
		    @FXML
		    private JFXTextField txtBuscarMobiliario;

		    
		    
		    @FXML
		    private JFXTreeTableView<Mobiliario> tblViewMobiliario;
		    
		    private TreeTableColumn<Mobiliario, String> clmnnombre_mobiliario;
			private TreeTableColumn<Mobiliario, String> clmncostomobiliario;
			
		    
		    
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

		    	
			    
				conexion= new ConexionMySQL();
				
				conexion.establecerConexion();
				
				listproducto=FXCollections.observableArrayList();
				listmobiliario=FXCollections.observableArrayList();
				
				
				Producto.llenarTableProducto(conexion.getConnection(), listproducto);
				Mobiliario.llenarTableMobiliario(conexion.getConnection(), listmobiliario);
				
				
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
				
				//ASIGNACION DE NOMBRES A COLUMNASS
				clmnnombre_mobiliario= new TreeTableColumn<>("Nombre");
				clmncostomobiliario= new TreeTableColumn<>("Costo");
				
				//VALORES
				clmnnombre_mobiliario.setCellValueFactory(new TreeItemPropertyValueFactory<Mobiliario,String>("desc_mobiliario"));
				clmncostomobiliario.setCellValueFactory(new TreeItemPropertyValueFactory<>("costo_mobiliario"));
				
		final TreeItem<Mobiliario> root1= new RecursiveTreeItem<Mobiliario>(listmobiliario,RecursiveTreeObject::getChildren);
				
				tblViewMobiliario.setRoot(root1);
				tblViewMobiliario.setShowRoot(false);
				
				
				
				//AGREGADO DE COLUMNAS
				tblViewMobiliario.getColumns().addAll(clmnnombre_mobiliario,
						clmncostomobiliario);
				
				
				tblViewProductos.getColumns().addAll(clmnnombre_producto,
						clmndesc_producto,
						clmnexistencia_producto,
						clmnprecioActual_producto,
						clmnMarca
						);
				
				conexion.cerrarConexion();		
			}
		
		
		}

	

  
