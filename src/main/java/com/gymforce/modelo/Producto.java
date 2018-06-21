package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Producto {
	
	
	private IntegerProperty clv_producto;
	private StringProperty nombre_producto;
	private StringProperty desc_producto;
	private DoubleProperty precioActual_producto;
	private IntegerProperty existencia_producto;
	private Marca clv_marca;
	private StringProperty status_producto;

	
	public Producto() {
	
	}
	
	public Producto(String nombre_producto, String desc_producto, Double precioActual_producto, int existencia_producto,
			Marca clv_marca) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		this.desc_producto = new SimpleStringProperty(desc_producto);
		this.precioActual_producto = new SimpleDoubleProperty(precioActual_producto);
		this.existencia_producto = new SimpleIntegerProperty(existencia_producto);
		this.clv_marca = clv_marca;

	}
	public Producto(String nombre_producto, String desc_producto, Double precioActual_producto, 
			Marca clv_marca) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		this.desc_producto = new SimpleStringProperty(desc_producto);
		this.precioActual_producto = new SimpleDoubleProperty(precioActual_producto);
		this.clv_marca = clv_marca;

	}
	public Producto(String nombre_producto, String desc_producto, Double precioActual_producto, 
			int existencia_producto) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		this.desc_producto = new SimpleStringProperty(desc_producto);
		this.precioActual_producto = new SimpleDoubleProperty(precioActual_producto);
		this.existencia_producto = new SimpleIntegerProperty(existencia_producto);

	}
	
	
	
	public Producto(String nombre_producto) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		
	}
	

	public Producto(int clv_producto, String nombre_producto, String desc_producto, Double precioActual_producto,
			int existencia_producto, Marca clv_marca, String status_producto) {
		this.clv_producto = new SimpleIntegerProperty(clv_producto);
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
		this.desc_producto = new SimpleStringProperty(desc_producto);
		this.precioActual_producto = new SimpleDoubleProperty(precioActual_producto);
		this.existencia_producto = new SimpleIntegerProperty(existencia_producto);
		this.clv_marca = clv_marca;
		this.status_producto = new SimpleStringProperty(status_producto);
	}

	// Metodos atributo: clv_producto
	public int getClv_producto() {
		return clv_producto.get();
	}

	public void setClv_producto(int clv_producto) {
		this.clv_producto = new SimpleIntegerProperty(clv_producto);
	}

	public IntegerProperty Clv_productoProperty() {
		return clv_producto;
	}

	// Metodos atributo: nombre_producto
	public String getNombre_producto() {
		return nombre_producto.get();
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = new SimpleStringProperty(nombre_producto);
	}

	public StringProperty Nombre_productoProperty() {
		return nombre_producto;
	}

	// Metodos atributo: desc_producto
	public String getDesc_producto() {
		return desc_producto.get();
	}

	public void setDesc_producto(String desc_producto) {
		this.desc_producto = new SimpleStringProperty(desc_producto);
	}

	public StringProperty Desc_productoProperty() {
		return desc_producto;
	}

	// Metodos atributo: precioActual_producto
	public Double getPrecioActual_producto() {
		return precioActual_producto.get();
	}

	public void setPrecioActual_producto(Double precioActual_producto) {
		this.precioActual_producto = new SimpleDoubleProperty(precioActual_producto);
	}

	public DoubleProperty PrecioActual_productoProperty() {
		return precioActual_producto;
	}

	// Metodos atributo: existencia_producto
	public int getExistencia_producto() {
		return existencia_producto.get();
	}

	public void setExistencia_producto(int existencia_producto) {
		this.existencia_producto = new SimpleIntegerProperty(existencia_producto);
	}

	public IntegerProperty Existencia_productoProperty() {
		return existencia_producto;
	}

	// Metodos atributo: clv_marca
	public Marca getClv_marca() {
		return clv_marca;
	}

	public void setClv_marca(Marca clv_marca) {
		this.clv_marca = clv_marca;
	}

	// Metodos atributo: status_producto
	public String getStatus_producto() {
		return status_producto.get();
	}

	public void setStatus_producto(String status_producto) {
		this.status_producto = new SimpleStringProperty(status_producto);
	}

	public StringProperty Status_productoProperty() {
		return status_producto;
	}

	public static void llenarTableProducto(Connection conexion, ObservableList<Producto> listproducto) {
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT" + " nombre_producto," + "desc_producto," + "precioActual_producto,"
					+ "existencia_producto," + "desc_marca"+" " + "FROM " + "producto "
					+ "JOIN marca ON marca.clv_marca=producto.clv_marca where status_producto=1");
			while (rs.next()) {
				listproducto.add(new Producto(rs.getString("nombre_producto"), rs.getString("desc_producto"),
						rs.getDouble("precioActual_producto"), rs.getInt("existencia_producto"),
						new Marca(rs.getString("desc_marca"))));
						//new Marca(rs.getString("desc_marca"))));
			}

		} catch (SQLException ex) {
			// TODO: handle exception
         Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null,ex);
		}
	}
	
	public String consultaid(Connection cn,String Marca) {
		String idmarca="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT clv_marca from marca where desc_marca='"+Marca+"'");
			while (rs.next()) {
				idmarca=rs.getString("clv_marca");
			}
			Marca r=new Marca();
			r.setClv_marca(Integer.parseInt(idmarca));
		}catch(Exception e) {
				
			}
		return idmarca;
	}
	
	public String buscaProducto(Connection cn,String Producto) {
		
		String nombreP="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT nombre_producto from producto where nombre_producto='"+Producto+"'"+"and status_producto=1");
			while (rs.next()) {
				nombreP=rs.getString("nombre_producto");
			}
			
		}catch(Exception e) {
				
			}
		return nombreP;
	}
    public String buscaDescripcion(Connection cn,String Producto) {
		
		String nombreP="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT desc_producto from producto where nombre_producto='"+Producto+"'"+"and status_producto=1");
			while (rs.next()) {
				nombreP=rs.getString("desc_producto");
			}
			
		}catch(Exception e) {
				
			}
		return nombreP;
	}
 public String buscaPrecio(Connection cn,String Producto) {
		
		String nombreP="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT precioActual_producto from producto where nombre_producto='"+Producto+"'"+"and status_producto=1");
			while (rs.next()) {
				nombreP=rs.getString("precioActual_producto");
			}
			
		}catch(Exception e) {
				
			}
		return nombreP;
	}
 public String buscaCantidad(Connection cn,String Producto) {
		
		String nombreP="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT existencia_producto from producto where nombre_producto='"+Producto+"'"+"and status_producto=1");
			while (rs.next()) {
				nombreP=rs.getString("existencia_producto");
			}
			
		}catch(Exception e) {
				
			}
		return nombreP;
	}
 public String buscaMarca(Connection cn,String Producto) {
		
		String nombreP="";
		try {
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT desc_marca from producto JOIN marca on marca.clv_marca=producto.clv_marca where nombre_producto='"+Producto+"'"+"and status_producto=1");
			while (rs.next()) {
				nombreP=rs.getString("existencia_producto");
			}
			
		}catch(Exception e) {
				
			}
		return nombreP;
	}
	
	
	
	
	
	public int GuardarProducto(Connection cn) {
	
		
		PreparedStatement consulta;
		try {
			consulta = cn.prepareStatement("INSERT producto VALUES (DEFAULT,?,?,?,?,2,DEFAULT)");
			consulta.setString(1,  nombre_producto.get());
			consulta.setString(2,  desc_producto.get());
			consulta.setDouble(3,  precioActual_producto.get());
			consulta.setInt(4,  existencia_producto.get());		

			return consulta.executeUpdate();			
	
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	
		
		
			
			
			
		
	}
public int eliminaProducto(Connection cn,String Producto) {
		
		
	PreparedStatement consulta;
	try {
		consulta = cn.prepareStatement("UPDATE producto SET status_producto='0' where nombre_producto='"+Producto+"' and status_producto='1'");
		

		return consulta.executeUpdate();			

			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 0;
	}
	
	}
@Override
public String toString() {
    return nombre_producto.get();
}

	
public int actualizaProducto(Connection cn,String Producto,String desc,String Precio,String Cantidad) {
	
	
	PreparedStatement consulta;
	try {
		consulta = cn.prepareStatement("UPDATE producto SET nombre_producto='"+Producto+"',desc_producto='"+desc+"',"
				+"precioActual_producto='"+Precio+"',existencia_producto='"+Cantidad+"'"+" where nombre_producto='"+Producto+"' and status_producto='1'");
		

		return consulta.executeUpdate();			

			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return 0;
	}
	
	}
	
public static void llenarComboProducto(Connection cn, ObservableList<Producto> listPro) {
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT "+"DISTINCT "+ "nombre_producto "+ "FROM "+ "producto where status_producto='1'");
        while (rs.next()) {
        	listPro.add(
                    new Producto(rs.getString("nombre_producto")
                            
                    ));

        }
    } catch (SQLException ex) {
        Logger.getLogger(TipoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
    }

}
	
	
	
	
}