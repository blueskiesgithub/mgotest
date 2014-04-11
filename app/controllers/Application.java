package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("MGO Test Application."));
    }
    
    public static Result authenticate() {
    	
    }
    
    public static Result allUsers() {
    	
    }
    
    public static Result listFiles() {
    	
    }
    
    public static Result status() {
    	
    }

}
