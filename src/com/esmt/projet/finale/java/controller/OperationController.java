package com.esmt.projet.finale.java.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.esmt.projet.finale.java.model.Operation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OperationController implements Initializable{
	
	@FXML
	private Label label ;
	@FXML
	private TextField textField ;
	@FXML
	private RadioButton retrait ;
	@FXML
	private RadioButton depot ;
	@FXML
	private Button effectuer ;
	@FXML
	private Button actualiser ;
	
	@FXML
    private TableView<Operation> operationTable;
	
    @FXML
    private TableColumn<Operation, Integer> id;
    
    @FXML
    private TableColumn<Operation, String> type;

    @FXML
    private TableColumn<Operation, Integer> montant;
    
    private static PreparedStatement ps ;
    private static Connection con ;
    private static ResultSet resultset ;
    static String login= "root";
	static String pwd= "";
	static String db_connexion= "jdbc:mysql://localhost/banque?autoReconnect=true&amp;useSSL=false?";
    final ObservableList<Operation> data = FXCollections.observableArrayList();    

	
	
	@FXML
	public void effectuerOperation(){
		
		if(FormValidation.textFieldNoEmpty(textField) && (retrait.isSelected() || depot.isSelected()))
		{
			try
			{
				con = DriverManager.getConnection(db_connexion, login, pwd);
				String selected = recupererRadioBoutton();
				ps = con.prepareStatement("Insert into transaction"
						+ "(type,montant) values (?,?)",Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, selected);
				ps.setString(2, textField.getText());
				ps.executeUpdate();
				
				resultset = ps.getGeneratedKeys();
				resultset.next();
				int generatedId = resultset.getInt(1);
				data.add(new Operation(generatedId,selected,Integer.parseInt(textField.getText()))); 
				operationTable.setItems(data);
				JOptionPane.showMessageDialog(null, "Operation effectué avec succès !");
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
			
		}else
			JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs svp !");
		
	}
	
    public String recupererRadioBoutton(){
		
		
		if(retrait.isSelected())
			return "retrait";
			
		else
			return "depot";
	}

	
	@FXML
	public void depotOperation(){
		label.setText("Dépot :");
	}
	
	@FXML
	public void retraitOperation(){
		label.setText("Retrait :");
	}
	
	@FXML
	public void atualiser(){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		id.setCellValueFactory(new PropertyValueFactory<Operation,Integer>("idT"));
		type.setCellValueFactory(new PropertyValueFactory<Operation,String>("typeT"));
		montant.setCellValueFactory(new PropertyValueFactory<Operation,Integer>("montantT"));
		operationTable.setItems(data);
		
	}

}
