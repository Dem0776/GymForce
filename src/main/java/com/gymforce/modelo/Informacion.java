package com.gymforce.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Informacion {
	
	public static void escribir(String cadena) {	
		File file = new File(
				"C:/Users/Gus/eclipse-jee-workspace/GymForce/src/main/java/com/gymforce/reportes/informacion.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				BufferedWriter escribir = new BufferedWriter(new FileWriter(file));
				escribir.write(cadena);
				escribir.close();
			} catch (Exception e) {
				Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public static String leer() {
		File fichero = new File("C:/Users/Gus/eclipse-jee-workspace/GymForce/src/main/java/com/gymforce/reportes/informacion.txt");
		String resultado="";
		try {
			BufferedReader leerParametro = new BufferedReader(new FileReader(fichero));			
			resultado = leerParametro.readLine();
			leerParametro.close();			
			return resultado;			
		} catch (IOException e) {
			Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, e);
			return "ERROR";			
		}
	}
}
