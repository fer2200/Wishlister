/**
 * 
 */
package com.jaya.bean;

/**
 * @author ferna
 *
 */
public class Checkings {

	private String venueName = "";
	private String friendFullName = "";

	public void setVenueName(String venueName) {
		this.venueName = venueName;		
	}
	
	public String getFriendFullName() {
		return friendFullName;
	}

	public void setFriendFullName(String friendFullName) {
		this.friendFullName = friendFullName;
	}

	public String getVenueName() {
		return this.venueName;
	}
	
	public String toString() {
	    return  this.getClass().getSimpleName() + " -> " + 
	            this.getVenueName() + " -> " + 
	    		this.getFriendFullName();	  
	}
	

}
