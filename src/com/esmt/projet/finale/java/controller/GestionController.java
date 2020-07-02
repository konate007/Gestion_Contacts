package com.esmt.projet.finale.java.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.esmt.projet.finale.java.run.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionController implements Initializable{
	
	
	@FXML
	public void gestionClient(ActionEvent event) throws IOException{
		
		try{
			
			Parent home_page_parent = FXMLLoader.load(MainApp.class.getResource("../view/InterfaceClient.fxml"));
			Scene  home_page_scene = new Scene(home_page_parent);
			home_page_scene.getStylesheets().add("css/style.css");
			Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			app_stage.hide();
			app_stage.setScene(home_page_scene);
			app_stage.show();
			
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	
	@FXML
	public void gestionCompte(ActionEvent event) throws IOException{
		
		try{
			Parent home_page_parent = FXMLLoader.load(MainApp.class.getResource("../view/CompteInterface.fxml"));
			Scene  home_page_scene = new Scene(home_page_parent);
			home_page_scene.getStylesheets().add("css/style.css");
			Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			app_stage.hide();
			app_stage.setScene(home_page_scene);
			app_stage.show();
			
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){}

}
