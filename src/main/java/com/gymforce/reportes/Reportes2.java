package com.gymforce.reportes;

import java.io.InputStream;
import java.sql.Connection;

import javax.swing.JFrame;

import com.gymforce.modelo.ConexionMySQL;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

 
public class Reportes2 {
	private String reporte;
	
	public Reportes2(String reporte) {
		this.reporte = reporte;
	}
	
	public void generarReporte() {
		ConexionMySQL cenexion = new ConexionMySQL();
        cenexion.establecerConexion();
		Connection conn = cenexion.getConnection();
		try {
			InputStream dir = getClass().getResourceAsStream("../reportes/"+this.reporte+".jrxml");
			JasperReport jr = JasperCompileManager.compileReport(dir);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
		    
			JRViewer test = new JRViewer(jp);
			JFrame frame = new JFrame("Reporte");
			frame.getContentPane().add(test);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			
		}catch(Exception ex) {
			System.out.println(this.reporte);
		}
		cenexion.cerrarConexion();
	}
}
