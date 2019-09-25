package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Map_P extends AbstractDAO{

	final static private String selectIDbyLogin = "SELECT ID FROM utilisateurs WHERE login = ?;";
	final static private String selectIDbyLoginsAndPassword = "SELECT ID FROM utilisateurs WHERE login = ? AND mdp = ?;";

	public static int selectIDbyloginPassword(String login, String password) {
		//return 2 - mdp fail
		//return 3 - success
		final CallableStatement callStatement = prepareCall(selectIDbyLogin);
		ArrayList<Integer> response = new ArrayList<Integer>();
        try {
			callStatement.setString(1, login);
	        if (callStatement.execute()) {
	            final ResultSet result = callStatement.getResultSet();
	            while(result.next()) {
	            	response.add( result.getInt(1)); //TODO A CHANGER password / login sql
	            }
	            if (response.size() > 0) {
	            	final CallableStatement callStatements = prepareCall(selectIDbyLoginsAndPassword);
	        		ArrayList<Integer> responses = new ArrayList<Integer>();
	                try {
	        			callStatements.setString(1, login);
	        			callStatements.setString(2, password);
	        	        if (callStatements.execute()) {
	        	            final ResultSet results = callStatements.getResultSet();
	        	            while(results.next()) {
	        	            	responses.add( results.getInt(1)); //TODO A CHANGER password / login sql
	        	            }
	        	            if (responses.size() == 1) {
	        	            	return 3; //success
	        	            } else {
	        	            	return 2; //mdp fail
	        	            }
	        	        }
	                } catch (SQLException e) {
	                	
	                }
	            } else {
	            	return 1; //login fail
	            }
	            result.close();
	        }
        } catch (SQLException e) {
		}
		return 0; // Error
	}
}
