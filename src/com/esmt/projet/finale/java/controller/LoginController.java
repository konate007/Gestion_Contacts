package com.esmt.projet.finale.java.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import com.esmt.projet.finale.java.run.MainApp;

public class LoginController implements Initializable{
	
	@FXML
	private TextField textField ;
	
	@FXML
	private PasswordField password ;
	
	@FXML
	public void authentication(ActionEvent event) throws SQLException, IOException{
		
			String pwd = "admin";
			String login = "admin";
	
			if(pwd.equalsIgnoreCase(password.getText()) && login.equalsIgnoreCase(textField.getText())){
				try{
					Parent home_page_parent = FXMLLoader.load(MainApp.class.getResource("../view/InterfaceDeGestion.fxml"));
					Scene  home_page_scene = new Scene(home_page_parent);
					home_page_scene.getStylesheets().add("css/style.css");
					Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
					app_stage.hide();
					app_stage.setScene(home_page_scene);
					app_stage.show();
					
				}catch (Exception e){
					
					System.err.println(e.getMessage());
				}
			}else
				JOptionPane.showMessageDialog(null, "Sorry !.Connection denied");
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){}


}
