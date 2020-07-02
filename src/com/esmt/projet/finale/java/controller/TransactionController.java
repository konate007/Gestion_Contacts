package com.esmt.projet.finale.java.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.esmt.projet.finale.java.model.Compte;
import com.esmt.projet.finale.java.run.MainApp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TransactionController implements Initializable{
	
	@FXML 
    private TextField cinC ;
	
	@FXML 
    private TextField textFieldS ;
    @FXML
    private TextField codeC ;
    @FXML 
    private TextField soldeC ;
    @FXML 
    private TextField decouvertC ;
    
    @FXML 
    private TextField numCompteC ;
    
    @FXML
    private TableView<Compte> compteTable;
	
    @FXML
    private TableColumn<Compte, String> type;
    
    @FXML
    private TableColumn<Compte, Integer> code;

    @FXML
    private TableColumn<Compte, Integer> solde;
    
    @FXML
    private TableColumn<Compte, Integer> cin;
    
    @FXML 
    private Label cinL;
    @FXML 
    private Label codeL;
    @FXML 
    private Label soldeL;
    @FXML 
    private Label decouvertL;
    @FXML 
    private Label numCompteL;
    
    @FXML
    private Button creer;
    @FXML
    private Button ok;
    
    @FXML
    private ToggleButton bouton ;
    @FXML
    private RadioButton courant ;
    @FXML
    private RadioButton epargne ;
    
    @FXML
    ComboBox <String> combobox ;
    
    final ObservableList<Compte> data = FXCollections.observableArrayList();
    private IntegerProperty index = new SimpleIntegerProperty();
    
    private static PreparedStatement ps ;
    private static Connection con ;
    private static ResultSet resultset ;
    static String login= "root";
	static String pwd= "";
	static String db_connexion= "jdbc:mysql://localhost/banque?autoReconnect=true&amp;useSSL=false?";
    
    
	
	
	@FXML
	public void transfert(ActionEvent event){
		

		    if(FormValidation.textFieldNoEmpty(numCompteC) == true){
		    	try{
		    		
		    		Parent home_page_parent = FXMLLoader.load(MainApp.class.getResource("../view/TransactionInterface.fxml"));
		    		Scene  home_page_scene = new Scene(home_page_parent);
		    		home_page_scene.getStylesheets().add("css/style.css");
		    		Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    		app_stage.hide();
		    		app_stage.setScene(home_page_scene);
		    		app_stage.show();
		    		
		    	}catch(Exception e){
		    		System.err.println(e.getMessage());
		    	}
		    }else
		    	JOptionPane.showMessageDialog(null, " Ce compte n'existe pas ! ");
		
		
		 }

	
	@FXML
	public void creer(){
		if(FormValidation.textFieldNoEmpty(cinC) == true && 
				FormValidation.textFieldNoEmpty(soldeC) == true && (courant.isSelected()) || epargne.isSelected()){
			
			try
			{
				con = DriverManager.getConnection(db_connexion, login, pwd);
				String selected = recupererRadioBoutton();
				ps = con.prepareStatement("Insert into compte"
						+ "(type,solde,cin) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, selected);
				ps.setString(2, soldeC.getText());
				ps.setString(3, cinC .getText());
				ps.executeUpdate();
				
				resultset = ps.getGeneratedKeys();
				resultset.next();
				int generatedId = resultset.getInt(1);
				data.add(new Compte(generatedId,selected,Integer.parseInt(soldeC.getText()),Integer.parseInt(cinC.getText())));
				compteTable.setItems(data);
				//String date = recupererDate();
				JOptionPane.showMessageDialog(null, "Compte crée avec succès !");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
			
		}else
			JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs svp !");
		
	}
	
	public static String recupererDate(){
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return format.format(date);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		
		code.setCellValueFactory(new PropertyValueFactory<Compte,Integer>("code"));
		type.setCellValueFactory(new PropertyValueFactory<Compte,String>("type"));
		solde.setCellValueFactory(new PropertyValueFactory<Compte,Integer>("solde"));
		cin.setCellValueFactory(new PropertyValueFactory<Compte,Integer>("cin"));

		compteTable.setItems(data);
		
		//Obtenir l'indice quand on clique sur une ligne du tableau
		compteTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>()
				{
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue){
				try{
					index.set(data.indexOf(newValue));
					code.setText(String.valueOf(data.get(index.get()).getCode()));
					type.setText(data.get(index.get()).getType());
					solde.setText(String.valueOf(data.get(index.get()).getSolde()));
					cin.setText(String.valueOf(data.get(index.get()).getCin()));
					
				}catch (Exception e){
					System.err.println(e.getMessage());
				}
			}
			
			
	    });
	}
	
	
	public boolean retournerCodeCompte(int codeRechercher){
		boolean test = false ;
		
		try{
			con = DriverManager.getConnection(db_connexion, login, pwd);
			String query = "Select code from compte where code = ?";
			ps.setInt(1, codeRechercher);
			ps = con.prepareStatement(query);
			resultset =  ps.executeQuery();
			if(resultset != null)
				test = true ;
		}catch(Exception e){
		   System.err.println(e.getMessage()); 
	    }
		return test;
	}
	
	public String recupererRadioBoutton(){
		
		
		if(courant.isSelected())
			return "courant";
			
		else
			return "epargne";
	}

}
