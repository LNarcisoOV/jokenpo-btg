package br.com.btg.constants;

import java.util.ArrayList;
import java.util.List;

public class Shot {
	public static final String STONE = "STONE";
	public static final String PAPER = "PAPER";
	public static final String SCISSOR = "SCISSOR";
	public static final String LIZARD = "LIZARD";
	public static final String SPOCK = "SPOCK";
	
	private static List<String> shotList = null;
	
	public static List<String> getAllKindOfShots(){
		if(shotList == null || shotList.isEmpty()) {
			shotList = new ArrayList<String>();
			shotList.add(STONE);
			shotList.add(PAPER);
			shotList.add(SCISSOR);
			shotList.add(LIZARD);
			shotList.add(SPOCK);
		} 
		
		return shotList;
	} 
}
