package controllers;

import models.SystemStatus;
import models.Users;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;
import java.io.*;

import play.db.DB;
import javax.sql.DataSource;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("MGO Test Application."));
    }
    
    public static Result populate()
    {
    	Users u = new Users();
    	u.name = "ash";
    	u.password = "blah";
    	u.age = 40;
    	u.gender = "male";
    	u.save();
    	
    	u = new Users();
    	u.name = "devin";
    	u.password = "blah";
    	u.age = 30;
    	u.gender = "male";
    	u.save();
    	
    	u = new Users();
    	u.name = "vanessa";
    	u.password = "blah";
    	u.age = 20;
    	u.gender = "female";
    	u.save();
    	
    	u = new Users();
    	u.name = "neena";
    	u.password = "blah";
    	u.age = 10;
    	u.gender = "female";
    	u.save();
    	
    	return ok();
    }
    
    public static Result authenticate() {
    	//Cast the body to json and check for the name and password fields
    	JsonNode json = request().body().asJson();
    	if (json != null)
    	{
    		String name = json.findPath("name").textValue();
    
    	    if(name != null) {
    	      String password = json.findPath("password").textValue();
    	      
    	      if (password != null)
    	      {
    	    	  Users u = Users.find.where().eq("name", name).findUnique();
    	    	  if (u != null)
    	    	  {
    	    		  if (password.equals(u.password))
    	    			  return ok("Authentication successful.");
    	    	  }
    	      }
    	    } 
    	}
    	
    	return badRequest("Authentication failure.");
    }
    
    public static Result allUsers(String gender) {
    	//Return all users whose gender matches.
    	
    	List<Users> uList = Users.find.where().eq("gender", gender).findList();
    	JsonNode json = Json.toJson(uList);
    	return ok(json.toString());
    }
    
    public static Result listFiles(String directory) {
    	File folder = new File(directory);
    	//Attempt to list the files in the directory.
    	//We ignore directories and return bad request upon failure
    	try
    	{
    		File[] listOfFiles = folder.listFiles();
    		List<String> filesOnlyList = new ArrayList<String>(); 
    		for (File entry : listOfFiles)
    		{
    			if (entry.isFile())
    				filesOnlyList.add(entry.getName());
    		}
    		
    		JsonNode json = Json.toJson(filesOnlyList);
    		return ok(json.toString());
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	    
    	return badRequest();
    }
    
    public static Result status() {
    	//For the purposes of this test project we simply test to make sure
    	//that we can connect the the database.
    	List<SystemStatus> statusList = new ArrayList<SystemStatus>();
    	DataSource ds = DB.getDataSource();
    	if (ds != null)
    	{
    		SystemStatus dbStatus = new SystemStatus();
    		dbStatus.name = "database";
    		dbStatus.state = "ok";
    		statusList.add(dbStatus);
    	}
    	JsonNode json = Json.toJson(statusList);
    	return ok(json.toString());
    }

}
