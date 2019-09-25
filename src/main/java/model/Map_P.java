package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Map_P extends AbstractDAO{

	final static private String selectIDbyLoginsInformations = "SELECT ID FROM utilisateurs WHERE login = ? AND mdp = ?;";

	public static int selectIDbyloginPassword(String login, String password) {
		//return 0 - Error
		//return 1 - login fail
		//return 2 - mdp fail
		//return 3 - success
		final CallableStatement callStatement = prepareCall(selectIDbyLoginsInformations);
		ArrayList<Integer> response = new ArrayList<Integer>();
        try {
			callStatement.setString(1, login);
			callStatement.setString(2, password);
	        if (callStatement.execute()) {
	            final ResultSet result = callStatement.getResultSet();
	            while(result.next()) {
	            	response.add( result.getInt(1)); //TODO A CHANGER password / login sql
	            }
	            result.close();
	        }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0; //TODO a changer
	}
}
