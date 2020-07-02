package com.esmt.projet.finale.java.run;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private Stage primaryStage;
    private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login System");
        try{
        	showPersonOverview();
		}catch(Exception e){
              System.err.println(e.getMessage());
        }

	}
	
	
	
	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview(){
	    try{
	    	
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("../view/LoginSystem.fxml"));
	        rootLayout = loader.load();
	        Scene scene = new Scene(rootLayout);
	        scene.getStylesheets().add("css/style.css");
	        primaryStage.setScene(scene);
	        primaryStage.show();


	    }catch(IOException e){
	        e.printStackTrace(); 
	    }
	}
	
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout(){}
    
    public static void main(String [] args){
    	launch(args);
    }
    
    

}
