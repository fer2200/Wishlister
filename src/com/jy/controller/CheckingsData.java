package com.jy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jy.bean.Checkings;
import com.jy.web.VenuesListing;


public class CheckingsData {
	
	final static Logger logger = LogManager.getLogger(VenuesListing.class.getName());
	List<Checkings> allFriendsCheckings = new ArrayList<>();

	public List<Checkings> getFriendsCheckings(String accessToken) {
		HttpClient client = HttpClientBuilder.create().build();
		String url = new String("https://api.foursquare.com/v2/checkins/recent?oauth_token=" + accessToken + "&v=" + DataHelper.formatTime()); 
		HttpGet request = new HttpGet(url);		
		String jsonResult = null; 		
		JSONObject object = null;

        try{

        	HttpResponse response = client.execute(request);
            jsonResult = DataHelper.inputStreamToString(response.getEntity().getContent()).toString();

            object = new JSONObject(jsonResult);
            JSONObject aResponse = (JSONObject) object.get("response");
            
            if(aResponse.has("recent")) {
        		JSONArray venues = (JSONArray) aResponse.getJSONArray("recent");        		
        		Checkings checkings = null;
        		for (int i=0; i < venues.length(); i++) {
        			if (venues.getJSONObject(i).getJSONObject("venue").has("name")) {
        				String name = venues.getJSONObject(i).getJSONObject("venue").getString("name");
        				checkings = new Checkings();
        				checkings.setVenueName(name);
        				String friendFirstName = venues.getJSONObject(i).getJSONObject("user").getString("firstName");
        				String friendLastName = venues.getJSONObject(i).getJSONObject("user").getString("lastName");        				
        				checkings.setFriendFullName(friendFirstName + " " + friendLastName);    		
        			}
        			if (venues.getJSONObject(i).getJSONObject("user").getJSONObject("photo").has("prefix")) {
        				String friendPhotoURL = venues.getJSONObject(i).getJSONObject("user").getJSONObject("photo").getString("prefix");
        				friendPhotoURL += "100x100" + venues.getJSONObject(i).getJSONObject("user").getJSONObject("photo").getString("suffix");
        				checkings.setFriendPhotoURL(friendPhotoURL);
        			}
        			 	
        			this.allFriendsCheckings.add(checkings);      
                }
        		if(logger.isDebugEnabled())
        			allFriendsCheckings.forEach(theCheckings -> logger.debug(theCheckings));
        		//for (Checkings c : allFriendsCheckings) {
        		//	System.out.println(c);
        		//}
            }             
         }
        catch(ConnectTimeoutException e){
            logger.error(e.getMessage());
        }
        catch (ClientProtocolException e) {
        	logger.error(e.getMessage());
        } catch (IOException e) {
        	logger.error(e.getMessage());
        	logger.error(e.getMessage());
        } catch (JSONException e) {
        	logger.error(e.getMessage());
        }
        return allFriendsCheckings;
    }
	
	
	
}
