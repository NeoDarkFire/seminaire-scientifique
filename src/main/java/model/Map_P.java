package model;

public class Map_P {

	public String selectIDbyloginPassword(String login, String password) {
		String rq = new String("SELECT 'ID' FROM 'utilisateurs' WHERE login = '" + login + "' AND mdp = '" + password + "';");
		return rq;
	}
}
