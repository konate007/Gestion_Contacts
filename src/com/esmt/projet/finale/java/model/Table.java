package com.esmt.projet.finale.java.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
	private SimpleIntegerProperty cinT;
	private SimpleIntegerProperty idT;
	private SimpleStringProperty nomT;
	private SimpleStringProperty prenomT;
	private SimpleStringProperty telephoneT;
	private SimpleStringProperty passwordT;
	private SimpleStringProperty adresseT;
	private SimpleStringProperty emailT;
	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param email
	 */
	public Table(int id, String prenom, String nom, String email, String telephone, String adresse, String password,int cin){
		this.cinT = new SimpleIntegerProperty(cin);
		this.nomT = new SimpleStringProperty(nom);
		this.prenomT = new SimpleStringProperty(prenom);
		this.telephoneT = new SimpleStringProperty(telephone);
		this.emailT = new SimpleStringProperty(email);
		this.adresseT = new SimpleStringProperty(adresse);
		this.passwordT = new SimpleStringProperty(password);
		this.idT = new SimpleIntegerProperty(id);

	}
	
	
	
	
	
	
	/**
	 * @param nomT
	 * @param prenomT
	 * @param telephoneT
	 * @param emailT
	 */
	public Table(String prenom, String nom, String email, String telephone, String adresse, String password, Integer cin, Integer id){
		//this.idT = new SimpleIntegerProperty(id);
		this.nomT = new SimpleStringProperty(nom);
		this.prenomT = new SimpleStringProperty(prenom);
		this.telephoneT = new SimpleStringProperty(telephone);
		this.emailT = new SimpleStringProperty(email);
		this.adresseT = new SimpleStringProperty(adresse);
		this.passwordT = new SimpleStringProperty(password);
		this.cinT = new SimpleIntegerProperty(cin);
		this.idT = new SimpleIntegerProperty(id);
	}

	/**
	 * 
	 */
	public Table() {
		super();
	}




	public Integer getIdT()
	{
		return idT.get();
	}
	public void setIdT(Integer id)
	{
		idT.set(id);
	}
	
	public Integer getCinT()
	{
		return cinT.get();
	}
	public void setCinT(Integer cin)
	{
		cinT.set(cin);
	}
	
	
	public String getPrenomT()
	{
		return prenomT.get();
	}
	public void setPrenomT( String prenom)
	{
		prenomT.set(prenom);
	}
	
	public String getNomT()
	{
		return nomT.get();
	}
	public void setNomT( String nom)
	{
		nomT.set(nom);
	}
	
	public String getEmailT()
	{
		return emailT.get();
	}
	public void setEmailT(String email)
	{
		emailT.set(email);
	}
	
	public String getTelephoneT()
	{
		return telephoneT.get();
	}
	public void setTelephoneT(String telephone)
	{
		telephoneT.set(telephone);
	}
	
	
	public String getPasswordT()
	{
		return passwordT.get();
	}
	public void setPasswordT(String password)
	{
		passwordT.set(password);
	}
	
	public String getAdresseT()
	{
		return adresseT.get();
	}
	public void setAdresseT(String adresse)
	{
		adresseT.set(adresse);
	}
	
	
	
	
	

}
