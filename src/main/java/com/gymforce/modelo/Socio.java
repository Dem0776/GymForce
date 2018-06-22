package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Socio{
	private StringProperty rfc_socio;
	private StringProperty nombre_socio;
	private StringProperty ape1_socio;
	private StringProperty ape2_socio;
	private StringProperty telefono_socio;
	private StringProperty direccion_socio;
	private StringProperty email_socio;

	
	public Socio() {
		
	}
	
	public Socio(String rfc_socio, String nombre_socio, String ape1_socio, 
String ape2_socio, String telefono_socio, String direccion_socio, 
String email_socio) { 
		this.rfc_socio = new SimpleStringProperty(rfc_socio);
		this.nombre_socio = new SimpleStringProperty(nombre_socio);
		this.ape1_socio = new SimpleStringProperty(ape1_socio);
		this.ape2_socio = new SimpleStringProperty(ape2_socio);
		this.telefono_socio = new SimpleStringProperty(telefono_socio);
		this.direccion_socio = new SimpleStringProperty(direccion_socio);
		this.email_socio = new SimpleStringProperty(email_socio);
	}

	//Metodos atributo: rfc_socio
	public String getRfc_socio() {
		return rfc_socio.get();
	}
	public void setRfc_socio(String rfc_socio) {
		this.rfc_socio = new SimpleStringProperty(rfc_socio);
	}
	public StringProperty Rfc_socioProperty() {
		return rfc_socio;
	}
	//Metodos atributo: nombre_socio
	public String getNombre_socio() {
		return nombre_socio.get();
	}
	public void setNombre_socio(String nombre_socio) {
		this.nombre_socio = new SimpleStringProperty(nombre_socio);
	}
	public StringProperty Nombre_socioProperty() {
		return nombre_socio;
	}
	//Metodos atributo: ape1_socio
	public String getApe1_socio() {
		return ape1_socio.get();
	}
	public void setApe1_socio(String ape1_socio) {
		this.ape1_socio = new SimpleStringProperty(ape1_socio);
	}
	public StringProperty Ape1_socioProperty() {
		return ape1_socio;
	}
	//Metodos atributo: ape2_socio
	public String getApe2_socio() {
		return ape2_socio.get();
	}
	public void setApe2_socio(String ape2_socio) {
		this.ape2_socio = new SimpleStringProperty(ape2_socio);
	}
	public StringProperty Ape2_socioProperty() {
		return ape2_socio;
	}
	//Metodos atributo: telefono_socio
	public String getTelefono_socio() {
		return telefono_socio.get();
	}
	public void setTelefono_socio(String telefono_socio) {
		this.telefono_socio = new SimpleStringProperty(telefono_socio);
	}
	public StringProperty Telefono_socioProperty() {
		return telefono_socio;
	}
	//Metodos atributo: direccion_socio
	public String getDireccion_socio() {
		return direccion_socio.get();
	}
	public void setDireccion_socio(String direccion_socio) {
		this.direccion_socio = new SimpleStringProperty(direccion_socio);
	}
	public StringProperty Direccion_socioProperty() {
		return direccion_socio;
	}
	//Metodos atributo: email_socio
	public String getEmail_socio() {
		return email_socio.get();
	}
	public void setEmail_socio(String email_socio) {
		this.email_socio = new SimpleStringProperty(email_socio);
	}
	public StringProperty Email_socioProperty() {
		return email_socio;
	}
	public static void llenarTableSocio(Connection conect, ObservableList<Socio> listSocio) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT "
                    + "socio.rfc_socio, "
                    + "socio.nombre_socio, "
                    + "socio.ape1_socio, "
                    + "socio.ape2_socio, "
                    + "socio.telefono_socio, "
                    + "socio.direccion_socio, "
                    + "socio.email_socio "
                    + "FROM "
                    + "socio ");
            while (rs.next()) {
                listSocio.add(
                        new Socio(rs.getString("rfc_socio"),
                                rs.getString("nombre_socio"),
                                rs.getString("ape1_socio"),
                                rs.getString("ape2_socio"),
                                rs.getString("telefono_socio"),
                                rs.getString("direccion_socio"),
                                rs.getString("email_socio")
                                ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	 public int guardarSocio(Connection cn) {
	        try {
	            PreparedStatement consulta
	                    = cn.prepareStatement(
	                            "INSERT socio VALUES (?,?,?,?,?,?,?,DEFAULT)");
	            consulta.setString(1, rfc_socio.get());
	            consulta.setString(2, nombre_socio.get());
	            consulta.setString(3, ape1_socio.get());
	            consulta.setString(4, ape2_socio.get());
	            consulta.setString(5, telefono_socio.get());
	            consulta.setString(6, direccion_socio.get());
	            consulta.setString(7, email_socio.get());

	            return consulta.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
	    public int actualizarSocio(Connection cn) {
	        try {
	            PreparedStatement consulta = cn.prepareStatement("UPDATE socio SET "
	            + "socio.rfc_socio = ?, "
	            + "socio.nombre_socio = ?, "
	            + "socio.ape1_socio = ?, "
	            + "socio.ape2_socio = ?, "
	            + "socio.telefono_socio = ?, "
	            + "socio.telefono_socio = ?, "
	            + "socio.direccion_socio = ? "
	            + "WHERE socio.rfc_socio = ? ");
	            consulta.setString(1, rfc_socio.get());
	            consulta.setString(2, nombre_socio.get());
	            consulta.setString(3, ape1_socio.get());
	            consulta.setString(4, ape2_socio.get());
	            consulta.setString(5, telefono_socio.get());
	            consulta.setString(6, direccion_socio.get());
	            consulta.setString(7, email_socio.get());
	            consulta.setString(8, rfc_socio.get());
	            
	            return consulta.executeUpdate();
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	 }
	 public int eliminarSocio(Connection cn) {
	        try {
	            PreparedStatement consulta = cn.prepareStatement(
	                    "DELETE FROM socio "
	                    +"WHERE socio.rfc_socio = ? ");
	            consulta.setString(1, rfc_socio.get());
	            return consulta.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
	            return 0;
	        }
	    }
	 public String buscaSocio(Connection cn,String Socio) {
			
			String nombreS="";
			try {
				
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("SELECT DISTINCT rfc_socio from socio where rfc_socio='"+Socio+"'"+"and status_socio=1");
				while (rs.next()) {
					nombreS=rs.getString("rfc_socio");
				}
				
			}catch(Exception e) {
					
				}
			return nombreS;
		}
	 
	 
	 
	 
}
