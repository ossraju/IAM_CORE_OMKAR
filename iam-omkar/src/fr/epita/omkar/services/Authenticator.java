package fr.epita.omkar.services;
/**
 * @author omkar         
 */
public interface Authenticator {
	/**
     * Authenticate with username and password.
     * @param username 
     * @param password 
     */
    boolean authenticate(String username, String password);
}