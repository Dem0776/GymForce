package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Empleado extends RecursiveTreeObject<Empleado> {
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

	public Empleado(String rfc_empleado, String nombre_empleado, String ape1_empleado, String ape2_empleado,
			String telefono_empleado, String email_empleado, String usuario_empleado, String password_empleado,
			TipoEmpleado clv_te) {
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
	
	public Empleado() {		
	}

	// Metodos atributo: rfc_empleado
	public String getRfc_empleado() {
		return rfc_empleado.get();
	}

	public void setRfc_empleado(String rfc_empleado) {
		this.rfc_empleado = new SimpleStringProperty(rfc_empleado);
	}

	public StringProperty Rfc_empleadoProperty() {
		return rfc_empleado;
	}

	// Metodos atributo: nombre_empleado
	public String getNombre_empleado() {
		return nombre_empleado.get();
	}

	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = new SimpleStringProperty(nombre_empleado);
	}

	public StringProperty Nombre_empleadoProperty() {
		return nombre_empleado;
	}

	// Metodos atributo: ape1_empleado
	public String getApe1_empleado() {
		return ape1_empleado.get();
	}

	public void setApe1_empleado(String ape1_empleado) {
		this.ape1_empleado = new SimpleStringProperty(ape1_empleado);
	}

	public StringProperty Ape1_empleadoProperty() {
		return ape1_empleado;
	}

	// Metodos atributo: ape2_empleado
	public String getApe2_empleado() {
		return ape2_empleado.get();
	}

	public void setApe2_empleado(String ape2_empleado) {
		this.ape2_empleado = new SimpleStringProperty(ape2_empleado);
	}

	public StringProperty Ape2_empleadoProperty() {
		return ape2_empleado;
	}

	// Metodos atributo: telefono_empleado
	public String getTelefono_empleado() {
		return telefono_empleado.get();
	}

	public void setTelefono_empleado(String telefono_empleado) {
		this.telefono_empleado = new SimpleStringProperty(telefono_empleado);
	}

	public StringProperty Telefono_empleadoProperty() {
		return telefono_empleado;
	}

	// Metodos atributo: email_empleado
	public String getEmail_empleado() {
		return email_empleado.get();
	}

	public void setEmail_empleado(String email_empleado) {
		this.email_empleado = new SimpleStringProperty(email_empleado);
	}

	public StringProperty Email_empleadoProperty() {
		return email_empleado;
	}

	// Metodos atributo: usuario_empleado
	public String getUsuario_empleado() {
		return usuario_empleado.get();
	}

	public void setUsuario_empleado(String usuario_empleado) {
		this.usuario_empleado = new SimpleStringProperty(usuario_empleado);
	}

	public StringProperty Usuario_empleadoProperty() {
		return usuario_empleado;
	}

	// Metodos atributo: password_empleado
	public String getPassword_empleado() {
		return password_empleado.get();
	}

	public void setPassword_empleado(String password_empleado) {
		this.password_empleado = new SimpleStringProperty(password_empleado);
	}

	public StringProperty Password_empleadoProperty() {
		return password_empleado;
	}

	// Metodos atributo: clv_te
	public TipoEmpleado getClv_te() {
		return clv_te;
	}

	public void setClv_te(TipoEmpleado clv_te) {
		this.clv_te = clv_te;
	}

	// Metodos atributo: status_empleado
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
			ResultSet rs = st.executeQuery(
					"SELECT * FROM empleado " + "JOIN tipo_empleado ON empleado.clv_te = tipo_empleado.clv_te "
							+ "WHERE tipo_empleado.clv_te = 2 AND empleado.status_empleado = 1");
			while (rs.next()) {
				listEmpl.add(new Empleado(rs.getString("rfc_empleado"), rs.getString("nombre_empleado")));

			}
		} catch (SQLException ex) {
			Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void llenarTableEmpleado(Connection conect, ObservableList<Empleado> listEmpleado) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT "
                    + "empleado.rfc_empleado, "
                    + "empleado.nombre_empleado, "
                    + "empleado.ape1_empleado, "
                    + "empleado.ape2_empleado, "
                    + "empleado.telefono_empleado, "
                    + "empleado.email_empleado, "
                    + "empleado.usuario_empleado, "
                    + "empleado.password_empleado, "
                    + "tipo_empleado.clv_te, "
                    + "tipo_empleado.desc_te "
                    + "FROM "
                    + "empleado "
                    + "INNER JOIN tipo_empleado ON empleado.clv_te = tipo_empleado.clv_te");
            while (rs.next()) {
                listEmpleado.add(
                        new Empleado(rs.getString("rfc_empleado"),
                                rs.getString("nombre_empleado"),
                                rs.getString("ape1_empleado"),
                                rs.getString("ape2_empleado"),
                                rs.getString("telefono_empleado"),
                                rs.getString("email_empleado"),
                                rs.getString("usuario_empleado"),
                                rs.getString("password_empleado"),
                                new TipoEmpleado(rs.getInt("clv_te"), rs.getString("desc_te"))
                        ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
    }
    public int guardarEmpleado(Connection cn) {
        try {
            PreparedStatement consulta
                    = cn.prepareStatement(
                            "INSERT empleado VALUES (?,?,?,?,?,?,?,?,?,DEFAULT)");
            consulta.setString(1, rfc_empleado.get());
            consulta.setString(2, nombre_empleado.get());
            consulta.setString(3, ape1_empleado.get());
            consulta.setString(4, ape2_empleado.get());
            consulta.setString(5, telefono_empleado.get());
            consulta.setString(6, email_empleado.get());
            consulta.setString(7, usuario_empleado.get());
            consulta.setString(8, password_empleado.get());
            consulta.setInt(9, clv_te.getClv_te());

            return consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int actualizarEmpleado(Connection cn) {
        try {
            PreparedStatement consulta = cn.prepareStatement("UPDATE empleado SET "
            + "empleado.rfc_empleado = ?, "
            + "empleado.nombre_empleado = ?, "
            + "empleado.ape1_empleado = ?, "
            + "empleado.ape2_empleado = ?, "
            + "empleado.telefono_empleado = ?, "
            + "empleado.email_empleado = ?, "
            + "empleado.usuario_empleado = ?, "
            + "empleado.password_empleado = ?, "
            + "empleado.clv_te = ? "
            + "WHERE empleado.rfc_empleado = ? ");
            consulta.setString(1, rfc_empleado.get());
            consulta.setString(2, nombre_empleado.get());
            consulta.setString(3, ape1_empleado.get());
            consulta.setString(4, ape2_empleado.get());
            consulta.setString(5, telefono_empleado.get());
            consulta.setString(6, email_empleado.get());
            consulta.setString(7, usuario_empleado.get());
            consulta.setString(8, password_empleado.get());
            consulta.setInt(9, clv_te.getClv_te());
            consulta.setString(10, rfc_empleado.get());
            
            return consulta.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
 }
	
=======
    } 
 
 
>>>>>>> ec609d323f73b9c63448221915dc6d9f21eab5dd
	public int validarUsuario(Connection conect, String usuario, String password) {
		String resultado = "";
		try {
			PreparedStatement consulta = conect.prepareStatement("SELECT empleado.usuario_empleado, "
					+ "empleado.password_empleado " + "FROM " + "empleado " 
					+ "WHERE empleado.usuario_empleado = '" + usuario + "' AND  empleado.password_empleado = '"
					+ password + "'");
			ResultSet rs = consulta.executeQuery();
			while(rs.next()) {
				resultado = rs.getString("usuario_empleado");
			}
			if(resultado.equals("")) {
				return 0;
			} else {
				return 1;
			}
			
		} catch (Exception e) {
			return 0;
		}
	}
	

	public int eliminarEmpleado(Connection cn) {
		try {
			PreparedStatement consulta = cn
					.prepareStatement("DELETE FROM empleado " + "WHERE empleado.rfc_empleado = ?");
			consulta.setString(1, rfc_empleado.get());
			return consulta.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
	}
}
