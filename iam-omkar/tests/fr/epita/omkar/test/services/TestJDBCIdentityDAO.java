package fr.epita.omkar.test.services;
/**
 * @author omkar         
 */
import java.sql.SQLException;
import java.util.List;

import fr.epita.omkar.datamodel.Identity;
import fr.epita.omkar.exceptions.DAOCreateException;
import fr.epita.omkar.exceptions.DAOSearchException;
import fr.epita.omkar.services.IdentityDAO;
import fr.epita.omkar.services.JDBCIdentityDAO;

public class TestJDBCIdentityDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            IdentityDAO identityDAO = new JDBCIdentityDAO();
	            Identity identity = new Identity("thomas", "broussard@gmail.com", null);

	            try {
	                identityDAO.create(identity);
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            }

	            try {
	                List<Identity> results = identityDAO.search(new Identity(null, null, null));
	                for (Identity result : results) {
	                    System.out.println(result);
	                }
	            } catch (DAOSearchException e) {
	                e.printStackTrace();
	            }

	            identityDAO.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
