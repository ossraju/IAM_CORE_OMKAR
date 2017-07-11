package fr.epita.omkar.launcher;
/**
 * @author omkar         
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.omkar.datamodel.Identity;
import fr.epita.omkar.exceptions.DAOCreateException;
import fr.epita.omkar.exceptions.DAORemoveException;
import fr.epita.omkar.exceptions.DAOSearchException;
import fr.epita.omkar.exceptions.DAOModifyException;
import fr.epita.omkar.services.Validation;
import fr.epita.omkar.services.Authenticator;
import fr.epita.omkar.services.IdentityDAO;
import fr.epita.omkar.services.JDBCIdentityDAO;


public class Launcher {
	private static Scanner scan;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 scan = new Scanner(System.in);

	        System.out.println("Welcome to Identity Project");

	        if (!authenticate()) {
	            System.out.println("Authenticate failed.");
	            System.exit(0);
	        }

	        System.out.println("Authenticate success.");

	        mainProgram();
	    }

	    private static boolean authenticate() {
	        Authenticator authenticator = new Validation();

	        System.out.println("Please enter username and password to proceed:");
	        System.out.println("Username: ");
	        String username = scan.nextLine();
	        System.out.println("Password: ");
	        String password = scan.nextLine();

	        return authenticator.authenticate(username, password);
	    }

	    private static void mainProgram() {
	        System.out.println("");
	        System.out.println("1) Create new Identity");
	        System.out.println("2) Search Identities");
	        System.out.println("3) Update Identity");
	        System.out.println("4) Delete Identity");
	        System.out.println("5) Exit");

	        System.out.println("Choose task: ");

	        String task = scan.nextLine().trim();

	        switch (task) {
	            case "1":
	                createIdentityMenu();
	                break;
	            case "2":
	                searchIdentityMenu();
	                break;
	            case "3":
	                updateIdentityMenu();
	                break;
	            case "4":
	                deleteIdentityMenu();
	                break;
	            case "5":
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Unrecognised task.");
	                mainProgram();
	        }
	    }

	    private static void createIdentityMenu() {
	        System.out.println("Create new identity: ");
	        System.out.println("Display Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(displayname, email, null);

	        createIdentity(identity);
	        mainProgram();
	    }

	    private static void searchIdentityMenu() {
	        System.out.println("Search identities ");
	        System.out.println("UID: ");
	        String uid = scan.nextLine();
	        System.out.println("Display Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(null, null, null);

	        if (uid.length() != 0) {
	            identity.setUid(Integer.parseInt(uid));
	        }

	        if (displayname.length() != 0) {
	            identity.setDisplayName(displayname);
	        }

	        if (email.length() != 0) {
	            identity.setEmail(email);
	        }

	        searchIdentity(identity);
	        mainProgram();
	    }

	    private static void updateIdentityMenu() {
	        System.out.println("Update Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("choose Identity UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);
	        System.out.println("New Display Name:");
	        identity.setDisplayName(scan.nextLine());
	        System.out.println("New Email:");
	        identity.setEmail(scan.nextLine());

	        updateIdentity(identity);

	        mainProgram();
	    }

	    private static void deleteIdentityMenu() {
	        System.out.println("Delete Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("choose Identity UID:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);

	        deleteIdentity(identity);

	        mainProgram();
	    }

	    private static void createIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.create(identity);
	                System.out.println("Successfully created new identity!");
	            } catch (DAOCreateException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void searchIdentity(Identity criteria) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                List<Identity> results = dao.search(criteria);
	                for (Identity result: results) {
	                    System.out.println(result);
	                }
	            } catch (DAOSearchException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void updateIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.update(identity);
	                System.out.println("Successfully updated identity!");
	            } catch (DAOModifyException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void deleteIdentity(Identity identity) {
	        try {
	            IdentityDAO dao = new JDBCIdentityDAO();
	            try {
	                dao.delete(identity);
	                System.out.println("Successfully deleted identity!");
	            } catch (DAORemoveException e) {
	                e.printStackTrace();
	            } finally {
	                dao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



}

