package fr.epita.omkar.services;
/**
 * @author omkar         
 */
import fr.epita.omkar.configuration.Configuration;

public class Validation implements Authenticator {
	 private String username;
	    private String password;

	    public Validation() {
	        this.username = Configuration.authenticateUsername;
	        this.password = Configuration.authenticatePassword;
	    }

	    /**
	     * @param username 
	     * @param password 
	     */
	    public boolean authenticate(String username, String password) {
	        return username.equals(this.username) && password.equals(this.password);
	    }

}
