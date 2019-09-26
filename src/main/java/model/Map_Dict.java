package model;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

abstract class Map_Dict extends AbstractDAO{
	
	final static private String selectWordMatch = "SELECT word FROM dico WHERE BINARY word = BINARY ?;";
	final static private String selectAllWords = "SELECT word FROM dico;";
	
	static boolean hasWord(String word) {
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
			e.printStackTrace();
		}
		return response != null;
	}

	static Set<String> allWords() throws SQLException {
		final CallableStatement callStatement = prepareCall(selectAllWords);
		if (callStatement.execute()) {
			final ResultSet result = callStatement.getResultSet();
			final Set<String> set = new HashSet<>(result.getFetchSize());
			while (result.next()) {
				final String response = result.getString(1);
				set.add(response);
			}
			result.close();
			return set;
		} else {
			throw new SQLException("Failed to execute statement");
		}
	}
}
