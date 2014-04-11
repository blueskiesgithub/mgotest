package models;
import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Users extends Model {
	
	public static Finder<Long, Users> find
    = new Model.Finder<Long, Users>(Long.class, Users.class);
	
    @Id
    public Long id;
    
	@Required
	public String name;
	
	@Required
	public Integer age;
	
	@Required
	public String password;
	
	@Required
	public String gender;

}
