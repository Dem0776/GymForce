package com.gymforce.modelo;

import java.sql.Connection;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;


public abstract class Reporte {
	private static JasperReport reporte;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;
	
	public static void crearReporte(Connection conn, String path) {
		try {
			reporte = (JasperReport)JRLoader.loadObjectFromFile(path);
            reportFilled = JasperFillManager.fillReport(reporte, null, conn);		
		}catch(JRException ex) {
			
		}
	}
	
	public static void showReporte() {
		viewer = new JasperViewer(reportFilled);
		viewer.setVisible(true);
	}
	public static void exportarPDF( String destino) {
		try {
			JasperExportManager.exportReportToHtmlFile(reportFilled, destino);
		}catch(JRException ex) {
			ex.printStackTrace();
		}
	}

}
