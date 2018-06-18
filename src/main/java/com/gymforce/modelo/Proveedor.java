package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proveedor{
	private StringProperty rfc_proveedor;
	private StringProperty razon_social;
	private StringProperty email_proveedor;
	private StringProperty telefono_proveedor;
	private StringProperty contacto_proveedor;
	private StringProperty status_proveedor;

	public Proveedor(String rfc_proveedor, String razon_social, String email_proveedor, 
String telefono_proveedor, String contacto_proveedor ) { 
		this.rfc_proveedor = new SimpleStringProperty(rfc_proveedor);
		this.razon_social = new SimpleStringProperty(razon_social);
		this.email_proveedor = new SimpleStringProperty(email_proveedor);
		this.telefono_proveedor = new SimpleStringProperty(telefono_proveedor);
		this.contacto_proveedor = new SimpleStringProperty(contacto_proveedor);
		
	}

	//Metodos atributo: rfc_proveedor
	public String getRfc_proveedor() {
		return rfc_proveedor.get();
	}
	public void setRfc_proveedor(String rfc_proveedor) {
		this.rfc_proveedor = new SimpleStringProperty(rfc_proveedor);
	}
	public StringProperty Rfc_proveedorProperty() {
		return rfc_proveedor;
	}
	//Metodos atributo: razon_social
	public String getRazon_social() {
		return razon_social.get();
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = new SimpleStringProperty(razon_social);
	}
	public StringProperty Razon_socialProperty() {
		return razon_social;
	}
	//Metodos atributo: email_proveedor
	public String getEmail_proveedor() {
		return email_proveedor.get();
	}
	public void setEmail_proveedor(String email_proveedor) {
		this.email_proveedor = new SimpleStringProperty(email_proveedor);
	}
	public StringProperty Email_proveedorProperty() {
		return email_proveedor;
	}
	//Metodos atributo: telefono_proveedor
	public String getTelefono_proveedor() {
		return telefono_proveedor.get();
	}
	public void setTelefono_proveedor(String telefono_proveedor) {
		this.telefono_proveedor = new SimpleStringProperty(telefono_proveedor);
	}
	public StringProperty Telefono_proveedorProperty() {
		return telefono_proveedor;
	}
	//Metodos atributo: contacto_proveedor
	public String getContacto_proveedor() {
		return contacto_proveedor.get();
	}
	public void setContacto_proveedor(String contacto_proveedor) {
		this.contacto_proveedor = new SimpleStringProperty(contacto_proveedor);
	}
	public StringProperty Contacto_proveedorProperty() {
		return contacto_proveedor;
	}
	//Metodos atributo: status_proveedor
	public String getStatus_proveedor() {
		return status_proveedor.get();
	}
	public void setStatus_proveedor(String status_proveedor) {
		this.status_proveedor = new SimpleStringProperty(status_proveedor);
	}
	public StringProperty Status_proveedorProperty() {
		return status_proveedor;
	}
	
	public int guardarProveedor(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT proveedor VALUES (?,?,?,?,?,DEFAULT)");
			consulta.setString(1, rfc_proveedor.get());
			consulta.setString(2, razon_social.get());
			consulta.setString(3, email_proveedor.get());
			consulta.setString(4, telefono_proveedor.get());
			consulta.setString(5, contacto_proveedor.get());
			
			
			
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	
	
}
