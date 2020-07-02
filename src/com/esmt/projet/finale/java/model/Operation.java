package com.esmt.projet.finale.java.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Operation{
	

	protected SimpleIntegerProperty montantT;
	protected SimpleIntegerProperty idT ;
	protected SimpleStringProperty typeT;
	protected SimpleStringProperty dateT;
	
	
	
	/**
	 * 
	 */
	public Operation() {
		super();

	}



	/**
	 * @param type
	 * @param montant
	 * @param id
	 */
	public Operation(int id,String type, int montant){
		super();
		this.idT = new SimpleIntegerProperty(id);
		this.montantT = new SimpleIntegerProperty(montant);
		this.typeT = new SimpleStringProperty(type);
	}
	
	public Integer getMontantT()
	{
		return montantT.get();
	}
	public void setMontantT(int montant)
	{
		montantT.set(montant);
	}
	
	public String getTypeT()
	{
		return typeT.get();
	}
	public void setTypeT( String type)
	{
		typeT.set(type);
	}
	
	public Integer getIdT()
	{
		return idT.get();
	}
	public void setIdT(Integer id)
	{
		idT.set(id);
	}
	

}
