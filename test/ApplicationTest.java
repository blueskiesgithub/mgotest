import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.Json;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }

    @Test
    public void authenticate() {
    	running(fakeApplication(), new Runnable() {
    	       public void run() {
    	    	   String body = "{ \"name\": \"ash\", \"password\": \"blah\"}";
	    	       	JsonNode json = Json.parse(body);
	    	       	FakeRequest request = new FakeRequest("POST", "v1/authenticate").withJsonBody(json);
	    	       	Result result = callAction(
	    	       			controllers.routes.ref.Application.authenticate(), request
	    	       			);
	    	       	assertThat(play.test.Helpers.status(result)).isEqualTo(OK);
    	       }
    	    });
    	/*
    	//Verify it returns ok for a good user and password
    	String body = "{ \"name\": \"ash\", \"password\": \"blah\"}";
    	JsonNode json = Json.parse(body);
    	FakeRequest request = new FakeRequest("POST", "v1/authenticate").withJsonBody(json);
    	Result result = callAction(
    			controllers.routes.ref.Application.authenticate(), request
    			);
    	assertThat(play.test.Helpers.status(result)).isEqualTo(OK);
    	//Verify it DOES NOT return ok for a bad user and password
    	body = "{ \"name\": \"unknown\", \"unknown\": \"blah\"}";
    	json = Json.parse(body);
    	request = new FakeRequest("POST", "v1/authenticate").withJsonBody(json);
    	result = callAction(
    			controllers.routes.ref.Application.authenticate(), request
    			);
    	assertThat(play.test.Helpers.status(result)).isNotEqualTo(OK);*/
    }
    /*
    @Test
    public void allUsers() {
    	Result result = callAction(
    			controllers.routes.ref.Application.allUsers("male")
    			);
        String users = contentAsString(result);
    	assertThat(users.contains("ash"));
    	assertThat(users.contains("neena"));
    	assertThat(users.contains("vanessa"));
    	assertThat(users.contains("devin"));
    }
    
    @Test
    public void status() {
    	Result result = callAction(
    			controllers.routes.ref.Application.status()
    			);
    	assertThat(play.test.Helpers.status(result)).isEqualTo(OK);
    }
    
    @Test
    public void listFiles() {
    	Result result = callAction(
    			controllers.routes.ref.Application.listFiles()
    			);
    	String files = contentAsString(result);
    	assertThat(files.contains("one"));
    	assertThat(files.contains("two"));
    	assertThat(files.contains("three"));
    	assertThat(files.contains("four"));
    	
    }*/
    

}
