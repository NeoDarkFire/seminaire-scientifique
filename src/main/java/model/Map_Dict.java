package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Map_Dict extends AbstractDAO{
	
	final static private String selectWordMatch = "SELECT word FROM dico WHERE word = ?;";
	
	public static String selectWord(String word) {
		final CallableStatement callStatement = prepareCall(selectWordMatch);
    	String response = null;
        try {
			callStatement.setString(1, word);
	        if (callStatement.execute()) {
	            final ResultSet result = callStatement.getResultSet();
	            if (result.first()) {
	            	response = result.getString(1);
	            }
	            result.close();
	        }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
