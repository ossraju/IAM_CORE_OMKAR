package fr.epita.omkar.services;
/**
 * @author omkar         
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.omkar.configuration.Configuration;
import fr.epita.omkar.datamodel.Identity;
import fr.epita.omkar.exceptions.DAOCreateException;
import fr.epita.omkar.exceptions.DAORemoveException;
import fr.epita.omkar.exceptions.DAOSearchException;
import fr.epita.omkar.exceptions.DAOModifyException;

public class JDBCIdentityDAO implements IdentityDAO {
	private Connection conn;

    public JDBCIdentityDAO() throws SQLException {
        this.conn = DriverManager.getConnection(
                Configuration.jdbcURL,
                Configuration.jdbcUsername,
                Configuration.jdbcPassword
        );
    }

    /**
     * Stores the Identity 
     */
    public void create(Identity identity) throws DAOCreateException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL) VALUES (?, ?)"
            );
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
            statement.execute();
        } catch (SQLException sqle) {
            DAOCreateException e = new DAOCreateException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * Search the Identity.
     */
    public List<Identity> search(Identity criteria) throws DAOSearchException {
        List<Identity> results = new ArrayList<>();
        String sql = "SELECT * FROM IDENTITIES";
        boolean condition = false;

        if (criteria.getUid() != null) {
            sql += " WHERE IDENTITY_UID=" + criteria.getUid();
            condition = true;
        }

        if (criteria.getDisplayName() != null) {
            if (condition) {
                sql += " AND IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            } else {
                sql += " WHERE IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            }
            condition = true;
        }

        if (criteria.getEmail() != null) {
            if (condition) {
                sql += " AND IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            } else {
                sql += " WHERE IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            }
        }

        try {
            PreparedStatement statement = this.conn.prepareStatement(sql);
            ResultSet statementResult = statement.executeQuery();
            while (statementResult.next()) {
                String displayname = statementResult.getString("IDENTITY_DISPLAYNAME");
                String email = statementResult.getString("IDENTITY_EMAIL");
                int uid = statementResult.getInt("IDENTITY_UID");
                results.add(new Identity(displayname, email, uid));
            }
        } catch (SQLException sqle) {
            DAOSearchException e = new DAOSearchException();
            e.initCause(sqle);
            e.setFaultObject(criteria);
            throw e;
        }

        return results;
    }

    /**
     * Update Identity.
     */
    public void update(Identity identity) throws DAOModifyException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME=?, IDENTITY_EMAIL=? WHERE IDENTITY_UID=?"
            );
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
            statement.setInt(3, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            DAOModifyException e = new DAOModifyException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * Delete Identity.
     */
    public void delete(Identity identity) throws DAORemoveException {
        try {
            PreparedStatement statement = this.conn.prepareStatement(
                    "DELETE FROM IDENTITIES WHERE IDENTITY_UID=?"
            );
            statement.setInt(1, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            DAORemoveException e = new DAORemoveException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
    }

    /**
     * JDBC closed.
     */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
