package fr.epita.omkar.services;
/**
 * @author omkar         
 */
import java.util.List;

import fr.epita.omkar.datamodel.Identity;
import fr.epita.omkar.exceptions.DAOCreateException;
import fr.epita.omkar.exceptions.DAORemoveException;
import fr.epita.omkar.exceptions.DAOSearchException;
import fr.epita.omkar.exceptions.DAOModifyException;


public interface IdentityDAO {
	 /**
     * Creates the Identity 
     */
    void create(Identity identity) throws DAOCreateException;

    /**
     * Search Identity 
     */
    List<Identity> search(Identity criteria) throws DAOSearchException;

    /**
     * Update Identity 
     */
    void update(Identity identity) throws DAOModifyException;

    /**
     * Delete Identity 
     */
    void delete(Identity identity) throws DAORemoveException;

    /**
     * Close Database.
     */
    void close();


}
