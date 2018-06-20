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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLsplashController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	private AnchorPane parent;	

	public void initialize(URL arg0, ResourceBundle arg1) {
		FXMLLoader principal = new FXMLLoader(getClass().getResource("/com/gymforce/vista/FXMLlogin.fxml"));
		AnimationGenerator animationGenerator = new AnimationGenerator();
		animationGenerator.applyFadeAnimationOn01(parent, 5000, 1, 0.8, 1, (e) -> {
			animationGenerator.applyFadeAnimationOn02(parent, 4000, 0.8, 1, 1, (e2) -> {					
		    	Parent root;
		    	Stage stage = new Stage();		        	
		    	parent.getScene().getWindow().hide();
		    	try {
					root = (Parent) principal.load();
					stage.setScene(new Scene(root));
			    	stage.initStyle(StageStyle.UNDECORATED);
			    	//stage.setMaximized(true);    	
			    	stage.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		    	
			});
		});

	}

}
