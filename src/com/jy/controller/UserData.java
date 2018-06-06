package com.jy.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.jy.bean.FoursquareUser;
import com.jy.web.VenuesListing;


public class UserData {
	
	final static Logger logger = LogManager.getLogger(VenuesListing.class.getName());
	

	public FoursquareUser getMyUser(String accessToken) {
		HttpClient client = HttpClientBuilder.create().build();
		String url = new String("https://api.foursquare.com/v2/users/self?oauth_token=" + accessToken + "&v=" + DataHelper.formatTime()); 
		HttpGet request = new HttpGet(url);		
		String jsonResult = null; 		
		JSONObject object = null;
		FoursquareUser myUser = new FoursquareUser();
		
        try{

        	HttpResponse response = client.execute(request);
            jsonResult = DataHelper.inputStreamToString(response.getEntity().getContent()).toString();

            object = new JSONObject(jsonResult);
            JSONObject aResponse = (JSONObject) object.get("response");
            
            if(aResponse.has("user")) {
            	JSONObject JSONObjectUser = (JSONObject) aResponse.getJSONObject("user");
        		String firstName = "";
        		String lastName = "";
        		
    			if (JSONObjectUser.has("firstName")) {
    				firstName = JSONObjectUser.getString("firstName");    		
    			}
    			if (JSONObjectUser.has("lastName")) {
    				lastName = JSONObjectUser.getString("lastName");
    			}
    			myUser.setFullName(firstName + " " + lastName );
    			
    			String photoURL = "";
    			
    			if(JSONObjectUser.has("photo")) {
    				photoURL = JSONObjectUser.getJSONObject("photo").getString("prefix");
    				photoURL += "100x100" + JSONObjectUser.getJSONObject("photo").getString("suffix");
    			}
    			myUser.setPhotoURL(photoURL);
                
        		if(logger.isDebugEnabled())
        			myUser.toString();
 
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
        return myUser;
    }
	
	
	
}
