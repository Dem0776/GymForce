package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TipoEmpleado extends RecursiveTreeObject<TipoEmpleado>{
	private IntegerProperty clv_te;
	private StringProperty desc_te;
	private StringProperty status_te;

	public TipoEmpleado(int clv_te, String desc_te) { 
		this.clv_te = new SimpleIntegerProperty(clv_te);
		this.desc_te = new SimpleStringProperty(desc_te);
	}
	public TipoEmpleado(String desc_te) {
		this.desc_te = new SimpleStringProperty(desc_te);
	}

	//Metodos atributo: clv_te
	public int getClv_te() {
		return clv_te.get();
	}
	public void setClv_te(int clv_te) {
		this.clv_te = new SimpleIntegerProperty(clv_te);
	}
	public IntegerProperty Clv_teProperty() {
		return clv_te;
	}
	//Metodos atributo: desc_te
	public String getDesc_te() {
		return desc_te.get();
	}
	public void setDesc_te(String desc_te) {
		this.desc_te = new SimpleStringProperty(desc_te);
	}
	public StringProperty Desc_teProperty() {
		return desc_te;
	}
	//Metodos atributo: status_te
	public String getStatus_te() {
		return status_te.get();
	}
	public void setStatus_te(String status_te) {
		this.status_te = new SimpleStringProperty(status_te);
	}
	public StringProperty Status_teProperty() {
		return status_te;
	}
	
	@Override
    public String toString() {
        return desc_te.get();
    }
	
	public static void llenarComboClasif(Connection conect, ObservableList<TipoEmpleado> listTipoE) {
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tipo_empleado");
            while (rs.next()) {
            	listTipoE.add(
                        new TipoEmpleado(rs.getInt("clv_te"),
                                rs.getString("desc_te")
                        ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	public int guardarTipoEmpleado(Connection cn) {
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT tipo_empleado VALUES (DEFAULT,?,DEFAULT)");
			consulta.setString(1, desc_te.get());
			return consulta.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		}
	}
	public static void llenarTableTipoEmpleado(Connection conect, ObservableList<TipoEmpleado> listTipoEmpleado) {
		try {
			Statement st = conect.createStatement();
			ResultSet rs = st.executeQuery("SELECT tipo_empleado.desc_te FROM tipo_empleado");

			while (rs.next()) {
				listTipoEmpleado.add(new TipoEmpleado(
						rs.getString("desc_te")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(Clase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
 public int eliminarTipoEmpleado(Connection cn) {
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "DELETE FROM tipo_empleado "
                    + "WHERE tipo_empleado.desc_te = ?");
            consulta.setString(1, desc_te.get());
            return consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
