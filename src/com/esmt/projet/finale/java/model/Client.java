package com.esmt.projet.finale.java.model;

public class Client extends Utilisateur{
	
	private Integer cin ;
	

	/**
	 * 
	 */
	public Client(){
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
	public Client(String prenom, String nom, String email, String adresse,
			String telephone, String password, Integer cin){
		super(prenom, nom, email, telephone, adresse, password);
		this.cin = cin ;
		
	}


	public Integer getCin() {
		return cin;
	}


	public void setCin(Integer cin) {
		this.cin = cin;
	}
	
	

	
	
	

}
