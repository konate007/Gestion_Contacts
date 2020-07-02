package com.esmt.projet.finale.java.model;

import javafx.beans.property.SimpleStringProperty;

public class Utilisateur {
	
	protected SimpleStringProperty prenomT ;
	protected SimpleStringProperty nomT ;
	protected SimpleStringProperty emailT ;
	protected SimpleStringProperty adresseT ;
	protected SimpleStringProperty telephoneT ;
	protected SimpleStringProperty passwordT ;
	
	
	/**
	 * 
	 */
	public Utilisateur(){
		super();
	}


	/**
	 * @param prenom
	 * @param nom
	 * @param email
	 * @param adresse
	 * @param telephone
	 * @param password
	 */
	public Utilisateur(String prenom, String nom, String email, String adresse,
			String telephone, String password) {
		super();
		this.prenomT = new SimpleStringProperty(prenom);
		this.nomT = new SimpleStringProperty(nom);
		this.emailT = new SimpleStringProperty(email);
		this.adresseT = new SimpleStringProperty(adresse);
		this.telephoneT = new SimpleStringProperty(telephone);
		this.passwordT = new SimpleStringProperty(password);
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
