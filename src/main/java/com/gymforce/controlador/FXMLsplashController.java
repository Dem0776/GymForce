package com.gymforce.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gymforce.Inicio;
import com.gymforce.modelo.AnimationGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FXMLsplashController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	private AnchorPane parent;

	public void makeStageDrageable() {
		parent.setOnMousePressed((MouseEvent event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		parent.setOnMouseDragged((MouseEvent event) -> {
			Inicio.stage.setX(event.getScreenX() - xOffset);
			Inicio.stage.setY(event.getScreenY() - yOffset);
			Inicio.stage.setOpacity(0.7);
		});
		parent.setOnDragDone((e) -> {
			Inicio.stage.setOpacity(1.0f);
		});
		parent.setOnMouseReleased((e) -> {
			Inicio.stage.setOpacity(1.0f);
		});
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/com/gymforce/vista/FXMLlogin1.fxml"));
			makeStageDrageable();
			AnimationGenerator animationGenerator = new AnimationGenerator();
			animationGenerator.applyFadeAnimationOn01(parent, 5000, 1, 0.8, 1, (e) -> {
				animationGenerator.applyFadeAnimationOn02(parent, 4000, 0.8, 1, 1, (e2) -> {
					parent.getChildren().removeAll();
					parent.getChildren().setAll(fxml);
				});
			});
		} catch (IOException ex) {
			Logger.getLogger(FXMLsplashController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
