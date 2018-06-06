package com.jy.bean;

public class FoursquareUser {

	private String fullName = "";
	private String photoURL = "";
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	
	public String toString() {
	    return  this.getClass().getSimpleName() + " -> " + 
	            this.getFullName() + " -> " + 
	    		this.getPhotoURL();	  
	}	

}
