package com.esmt.projet.finale.java.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.esmt.projet.finale.java.model.Table;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController implements Initializable{
	
	@FXML
	MenuBar myMenuBar;
	
//	@FXML
//	MenuItem menuItem ;
	
	@FXML 
    private TextField nomC ;
	
	@FXML 
    private TextField idC ;
	
	@FXML 
    private TextField adresseC ;
	
	@FXML 
    private TextField textFieldS ;
    @FXML
    private TextField prenomC ;
    @FXML 
    private TextField emailC ;
    @FXML 
    private TextField telephoneC ;
    @FXML 
    private TextField cinC ;
    
    @FXML 
    private PasswordField passwordC ;
    
    @FXML
    private TableView<Table> clientTable;
	
    @FXML
    private TableColumn<Table, String> nom;
    
    @FXML
    private TableColumn<Table, String> prenom;

    @FXML
    private TableColumn<Table, String> email;
    
    @FXML
    private TableColumn<Table, String> telephone;
    
    @FXML
    private TableColumn<Table, String> password;
    
    @FXML
    private TableColumn<Table, String> adresse;

    @FXML
    private TableColumn<Table, Integer> cin;
    
    @FXML
    private TableColumn<Table, String> ce;
    
    @FXML
    private TableColumn<Table, String> cc;
    
    @FXML
    private TableColumn<Table, Integer> id;
    
    @FXML 
    private Label cinL;
    @FXML 
    private Label nomL;
    @FXML 
    private Label prenomL;
    @FXML 
    private Label emailL;
    @FXML 
    private Label telephoneL;
    @FXML 
    private Label passwordL;
    @FXML 
    private Label adresseL;
    
    @FXML
    private ComboBox <String> combobox ;
    
    
    
    @FXML
    Button update;
    @FXML
    Button save; 
    @FXML
    Button delete;
    
    @FXML
    Button load;
    
   
    
    static PreparedStatement ps = null ;
    static ResultSet resultSet = null ; 
    private static Connection con ;
    static String login= "root";
	static String pwd= "";
	static String db_connexion= "jdbc:mysql://localhost/banque?autoReconnect=true&amp;useSSL=false?";
    final ObservableList<Table> data = FXCollections.observableArrayList();
    ObservableList<String>liste = FXCollections.observableArrayList("ID");
    private IntegerProperty index = new SimpleIntegerProperty();

	
	
	
	
	@FXML
	public void changerAClientInterface(ActionEvent event){
		
        try{
			
			Parent home_page_parent = FXMLLoader.load(MainApp.class.getResource("../view/InterfaceCompte.fxml"));
			Scene  home_page_scene = new Scene(home_page_parent);
			home_page_scene.getStylesheets().add("css/style.css");
			Stage app_stage = (Stage) myMenuBar.getScene().getWindow();
			//Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			app_stage.hide();
			app_stage.setScene(home_page_scene);
			app_stage.show();
        				
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	@FXML
	public void load(){
		try{
			con = DriverManager.getConnection(db_connexion, login, pwd);
			String query = "Select *from client";
			ps = con.prepareStatement(query);
			resultSet =  ps.executeQuery();
			
			while(resultSet.next())
			{
				int identifiant = resultSet.getInt("id");
				int cin = resultSet.getInt("cin");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				String telephone = resultSet.getString("telephone");
				String password = resultSet.getString("password");
				String adresse = resultSet.getString("adresse");
				
			    Table table = new  Table (identifiant,prenom, nom, email, telephone, adresse, password,cin);
			    data.add(table);
		    }
			clientTable.setItems(data);
			
		}catch(Exception e){
		   System.err.println(e.getMessage()); 
	    }
	}
	
	@FXML
	public void save(){
		
			if(FormValidation.textFieldNoEmpty(nomC) == true && FormValidation.textFieldNoEmpty(prenomC) == true && 
					FormValidation.textFieldNoEmpty(emailC) == true && FormValidation.textFieldNoEmpty(telephoneC) == true
					&& FormValidation.textFieldNoEmpty(adresseC) == true && FormValidation.textFieldNoEmpty(passwordC) == true
				&& FormValidation.textFieldNoEmpty(telephoneC) == true){
				try
				{
					con = DriverManager.getConnection(db_connexion, login, pwd);

					ps = con.prepareStatement("Insert into client"
							+ "(cin,prenom,nom,email,telephone,adresse,password) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, cinC.getText());
					ps.setString(2, prenomC.getText());
					ps.setString(3, nomC.getText());
					ps.setString(4, emailC.getText());
					ps.setString(5, telephoneC.getText());
					ps.setString(6, adresseC.getText());
					ps.setString(7, passwordC.getText());
					ps.executeUpdate();
			        
			        resultSet = ps.getGeneratedKeys();
			        resultSet.next();
			        int generatedId = resultSet.getInt(1);
			        data.add(new Table(generatedId, prenomC.getText(),nomC.getText(),emailC.getText(),telephoneC.getText(), adresseC.getText(),
			        		passwordC.getText(),Integer.parseInt(cinC.getText())));
			        clientTable.setItems(data);
			        JOptionPane.showMessageDialog(null, "Client ajouté avec succès !");
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
				
			}else
				JOptionPane.showMessageDialog(null, " veuillez renseigner tous les champs svp !");
		
	}
	
	
	@FXML
	public void search(){
		int rsr = Integer.parseInt(textFieldS.getText());
		for(int i = 0; i<data.size(); i ++){
			
			if(data.get(i).getIdT() == rsr){
				
				clientTable.getSelectionModel().select(i);
			}
		}
	}
	
	@FXML
	public void delete(){
		
		Table table = clientTable.getSelectionModel().getSelectedItem();
		int idT = table.getIdT();
		int i = index.get();
		
		
		try
		{
			con = DriverManager.getConnection(db_connexion,login,pwd);
			ps = con. prepareStatement("Delete From client where id = ?");
			ps.setInt(1, idT);
			ps.executeUpdate();
			if( i > -1)
			{
				try{
					data.remove(i);
					clientTable.getSelectionModel().clearSelection();
					JOptionPane.showMessageDialog(null, "Client supprimé avec succès !");
					clearForm();
					
				}catch (Exception e){
					
					System.err.println(e.getMessage());
					
				}
			}
			clearForm();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void update(){
		
			if(FormValidation.textFieldNoEmpty(nomC) == true && FormValidation.textFieldNoEmpty(prenomC) == true && 
					FormValidation.textFieldNoEmpty(emailC) == true && FormValidation.textFieldNoEmpty(telephoneC) == true){

				Table table = clientTable.getSelectionModel().getSelectedItem();
				int idT = table.getCinT();
				try
				{
					con = DriverManager.getConnection(db_connexion, login, pwd);
		
					ps = con.prepareStatement("Update client Set cin = ?, prenom = ?, nom = ?,email = ?, telephone = ?,"
							+ "adresse = ?, password = ? Where id = ?");
				    ps.setString(1, cinC.getText());
					ps.setString(2, prenomC.getText());
					ps.setString(3, nomC.getText());
					ps.setString(4, emailC.getText());
					ps.setString(5, telephoneC.getText());
					ps.setString(6, adresseC.getText());
					ps.setString(7, passwordC.getText());
					ps.setInt(8, idT);
					ps.executeUpdate();
				    
					
					data.add(clientTable.getSelectionModel().getSelectedIndex(), new Table(prenomC.getText(),nomC.getText(),
							emailC.getText(),telephoneC.getText(), adresseC.getText(), passwordC.getText(), Integer.parseInt(cinC.getText()), idT));
					data.remove(clientTable.getSelectionModel().getSelectedIndex());
			        clientTable.setItems(data);
			        JOptionPane.showMessageDialog(null, "Contact mis à jour avec succès !");
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
	     }	

	@Override
	public void initialize(URL location, ResourceBundle resources){
		id.setCellValueFactory(new PropertyValueFactory<Table,Integer>("idT"));
		prenom.setCellValueFactory(new PropertyValueFactory<Table,String>("prenomT"));
		nom.setCellValueFactory(new PropertyValueFactory<Table,String>("nomT"));
		email.setCellValueFactory(new PropertyValueFactory<Table,String>("emailT"));
		telephone.setCellValueFactory(new PropertyValueFactory<Table,String>("telephoneT"));
		adresse.setCellValueFactory(new PropertyValueFactory<Table,String>("adresseT"));
		password.setCellValueFactory(new PropertyValueFactory<Table,String>("passwordT"));
		cin.setCellValueFactory(new PropertyValueFactory<Table,Integer>("CinT"));
		clientTable.setItems(data);
		
		//Obtenir l'indice quand on clique sur une ligne du tableau
		clientTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>()
				{
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue){
				try{
					index.set(data.indexOf(newValue));
					//idC.setText(String.valueOf(data.get(index.get()).getIdT()));
					cinC.setText(String.valueOf(data.get(index.get()).getCinT()));
					prenomC.setText(data.get(index.get()).getPrenomT());
					nomC.setText(data.get(index.get()).getNomT());
					emailC.setText(data.get(index.get()).getEmailT());
					telephoneC.setText(data.get(index.get()).getTelephoneT());
					adresseC.setText(data.get(index.get()).getAdresseT());
					passwordC.setText(data.get(index.get()).getPasswordT());
				}catch (Exception e){
					System.err.println(e.getMessage());
				}
			}
			
			
	    });
		combobox.setItems(liste);
	}
	
	private void clearForm(){
		cinC.setText(""); 
		prenomC.setText("");
		nomC.setText("");
		emailC.setText("");
		telephoneC.setText("");
		passwordC.setText("");
		adresseC.setText("");
		
	}

	
	

}
