package com.esmt.projet.finale.java.model;


public class Compte {
	
	
	private int cin ;
	private int code ;
	private int solde ;
	private String type ;
	
	
	/**
	 * @param cin
	 * @param code
	 * @param solde
	 * @param type
	 */
	public Compte(int code,String type, int solde, int cin){
		super();
		this.cin = cin;
		this.code = code;
		this.solde = solde;
		this.type = type;
	}


	public Integer getCin() {
		return cin;
	}


	public void setCin(Integer cin) {
		this.cin = cin;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public int getSolde() {
		return solde;
	}


	public void setSolde(int solde){
		this.solde = solde;
	}


	public String getType(){
		return type;
	}


	public void setType(String type){
		this.type = type;
	}


	/**
	 * 
	 */
	public Compte() {
		super();
	}


	/**
	 * @param client
	 * @param cin
	 */
	public Compte(Client client, int cin){
		super();
		this.cin = cin;
	}
	
	
	
	
	

}
