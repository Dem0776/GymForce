package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Empleado {
	private StringProperty rfc_empleado;
	private StringProperty nombre_empleado;
	private StringProperty ape1_empleado;
	private StringProperty ape2_empleado;
	private StringProperty telefono_empleado;
	private StringProperty email_empleado;
	private StringProperty usuario_empleado;
	private StringProperty password_empleado;
	private TipoEmpleado clv_te;
	private StringProperty status_empleado;

	public Empleado(String rfc_empleado, String nombre_empleado, String ape1_empleado, 
String ape2_empleado, String telefono_empleado, String email_empleado, 
String usuario_empleado, String password_empleado, TipoEmpleado clv_te) { 
		this.rfc_empleado = new SimpleStringProperty(rfc_empleado);
		this.nombre_empleado = new SimpleStringProperty(nombre_empleado);
		this.ape1_empleado = new SimpleStringProperty(ape1_empleado);
		this.ape2_empleado = new SimpleStringProperty(ape2_empleado);
		this.telefono_empleado = new SimpleStringProperty(telefono_empleado);
		this.email_empleado = new SimpleStringProperty(email_empleado);
		this.usuario_empleado = new SimpleStringProperty(usuario_empleado);
		this.password_empleado = new SimpleStringProperty(password_empleado);
		this.clv_te = clv_te;
	}
	
	public Empleado(String rfc_empleado, String nombre_empleado) {
		this.rfc_empleado = new SimpleStringProperty(rfc_empleado);
		this.nombre_empleado = new SimpleStringProperty(nombre_empleado);
	}
	
	public Empleado(String nombre_empleado) {
		this.nombre_empleado = new SimpleStringProperty(nombre_empleado);
	}
	//Metodos atributo: rfc_empleado
	public String getRfc_empleado() {
		return rfc_empleado.get();
	}
	public void setRfc_empleado(String rfc_empleado) {
		this.rfc_empleado = new SimpleStringProperty(rfc_empleado);
	}
	public StringProperty Rfc_empleadoProperty() {
		return rfc_empleado;
	}
	//Metodos atributo: nombre_empleado
	public String getNombre_empleado() {
		return nombre_empleado.get();
	}
	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = new SimpleStringProperty(nombre_empleado);
	}
	public StringProperty Nombre_empleadoProperty() {
		return nombre_empleado;
	}
	//Metodos atributo: ape1_empleado
	public String getApe1_empleado() {
		return ape1_empleado.get();
	}
	public void setApe1_empleado(String ape1_empleado) {
		this.ape1_empleado = new SimpleStringProperty(ape1_empleado);
	}
	public StringProperty Ape1_empleadoProperty() {
		return ape1_empleado;
	}
	//Metodos atributo: ape2_empleado
	public String getApe2_empleado() {
		return ape2_empleado.get();
	}
	public void setApe2_empleado(String ape2_empleado) {
		this.ape2_empleado = new SimpleStringProperty(ape2_empleado);
	}
	public StringProperty Ape2_empleadoProperty() {
		return ape2_empleado;
	}
	//Metodos atributo: telefono_empleado
	public String getTelefono_empleado() {
		return telefono_empleado.get();
	}
	public void setTelefono_empleado(String telefono_empleado) {
		this.telefono_empleado = new SimpleStringProperty(telefono_empleado);
	}
	public StringProperty Telefono_empleadoProperty() {
		return telefono_empleado;
	}
	//Metodos atributo: email_empleado
	public String getEmail_empleado() {
		return email_empleado.get();
	}
	public void setEmail_empleado(String email_empleado) {
		this.email_empleado = new SimpleStringProperty(email_empleado);
	}
	public StringProperty Email_empleadoProperty() {
		return email_empleado;
	}
	//Metodos atributo: usuario_empleado
	public String getUsuario_empleado() {
		return usuario_empleado.get();
	}
	public void setUsuario_empleado(String usuario_empleado) {
		this.usuario_empleado = new SimpleStringProperty(usuario_empleado);
	}
	public StringProperty Usuario_empleadoProperty() {
		return usuario_empleado;
	}
	//Metodos atributo: password_empleado
	public String getPassword_empleado() {
		return password_empleado.get();
	}
	public void setPassword_empleado(String password_empleado) {
		this.password_empleado = new SimpleStringProperty(password_empleado);
	}
	public StringProperty Password_empleadoProperty() {
		return password_empleado;
	}
	//Metodos atributo: clv_te
	public TipoEmpleado getClv_te() {
		return clv_te;
	}
	public void setClv_te(TipoEmpleado clv_te) {
		this.clv_te = clv_te;
	}
	//Metodos atributo: status_empleado
	public String getStatus_empleado() {
		return status_empleado.get();
	}
	public void setStatus_empleado(String status_empleado) {
		this.status_empleado = new SimpleStringProperty(status_empleado);
	}
	public StringProperty Status_empleadoProperty() {
		return status_empleado;
	}
	
	@Override
    public String toString() {
        return nombre_empleado.get();
    }
	
	public static void llenarComboInstructor(Connection conect, ObservableList<Empleado> listEmpl) {
		try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM empleado "
            		+ "JOIN tipo_empleado ON empleado.clv_te = tipo_empleado.clv_te "
            		+ "WHERE tipo_empleado.clv_te = 3 AND empleado.status_empleado = 1");
            while (rs.next()) {
            	listEmpl.add(
                        new Empleado(rs.getString("rfc_empleado"),
                                rs.getString("nombre_empleado")
                        ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
	}		
}
