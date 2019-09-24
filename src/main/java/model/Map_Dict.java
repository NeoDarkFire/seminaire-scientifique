package model;

public class Map_Dict {
	
	public String selectWord(String word) {
		String rq = new String("SELECT 'word' FROM 'dictionnaire' WHERE word = '" + word + "'';");
		return rq;
	}
}
