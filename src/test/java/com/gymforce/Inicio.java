package com.gymforce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Inicio extends Application {
	public static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLprincipal.fxml"));
		Scene escena = new Scene(root);
		primaryStage.setScene(escena);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}

}