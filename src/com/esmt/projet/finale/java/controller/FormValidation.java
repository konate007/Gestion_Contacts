package com.esmt.projet.finale.java.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormValidation {
	
	public static boolean textFieldNoEmpty(TextField text){
		boolean r = false ;
		if(text.getText() != null && !text.getText().isEmpty())
		{
			r = true ;
		}
		return r;
	}
	
	public static boolean textFieldNoEmpty(TextField text , Label label , String isValidationText){
		boolean r = true ;
		String c = null ;
		if(!textFieldNoEmpty(text)){
			r = false ;
			c = isValidationText ;
		}
		label.setText(c);
		return r ;
	}
	
	

}
