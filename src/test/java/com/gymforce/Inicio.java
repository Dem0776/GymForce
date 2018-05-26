package com.gymforce;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Inicio extends javafx.application.Application{
 
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root =FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLsplash.fxml"));
		Scene escena = new Scene(root);
		primaryStage.setScene(escena);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		
	}

}
