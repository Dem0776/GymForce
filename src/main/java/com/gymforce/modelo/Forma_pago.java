package com.gymforce.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Forma_pago{
	private IntegerProperty clv_fp;
	private StringProperty desc_fp;
	private StringProperty status_fp;
  public Forma_pago() {
	  
  }
  
 public Forma_pago(String desc_fp) {
	 this.desc_fp = new SimpleStringProperty(desc_fp);
  }
	public Forma_pago(int clv_fp, String desc_fp, String status_fp) { 
		this.clv_fp = new SimpleIntegerProperty(clv_fp);
		this.desc_fp = new SimpleStringProperty(desc_fp);
		this.status_fp = new SimpleStringProperty(status_fp);
	}

	//Metodos atributo: clv_fp
	public int getClv_fp() {
		return clv_fp.get();
	}
	public void setClv_fp(int clv_fp) {
		this.clv_fp = new SimpleIntegerProperty(clv_fp);
	}
	public IntegerProperty Clv_fpProperty() {
		return clv_fp;
	}
	//Metodos atributo: desc_fp
	public String getDesc_fp() {
		return desc_fp.get();
	}
	public void setDesc_fp(String desc_fp) {
		this.desc_fp = new SimpleStringProperty(desc_fp);
	}
	public StringProperty Desc_fpProperty() {
		return desc_fp;
	}
	//Metodos atributo: status_fp
	public String getStatus_fp() {
		return status_fp.get();
	}
	public void setStatus_fp(String status_fp) {
		this.status_fp = new SimpleStringProperty(status_fp);
	}
	public StringProperty Status_fpProperty() {
		return status_fp;
	}
	
    @Override
    public String toString() {
        return desc_fp.get();
    }

	
public static void llenarComboFP(Connection cn, ObservableList<Forma_pago> listFP) {
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT "+"DISTINCT "+ "desc_fp "+ "FROM "+ "forma_pago where status_fp='1'");
        while (rs.next()) {
        	listFP.add(
                    new Forma_pago(rs.getString("desc_fp")
                            
                    ));

        }
    } catch (SQLException ex) {
        Logger.getLogger(TipoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
    }

}
	
	
}
