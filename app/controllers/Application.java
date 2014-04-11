package controllers;

import models.Users;
import play.*;
import play.mvc.*;

import views.html.*;
import com.fasterxml.jackson.databind.JsonNode;

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
    	JsonNode json = request().body().asJson();
    	if (json != null)
    	{
    		String name = json.findPath("name").textValue();
    		System.out.println(name);
    	    if(name != null) {
    	      String password = json.findPath("password").textValue();
    	      System.out.println(password);
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
    
    public static Result allUsers() {
    	return badRequest();
    }
    
    public static Result listFiles() {
    	return badRequest();
    }
    
    public static Result status() {
    	return badRequest();
    }

}
