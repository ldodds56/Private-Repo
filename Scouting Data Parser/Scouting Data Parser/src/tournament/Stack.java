package tournament;

import scouting_data.MatchData.location;

public class Stack {
	private int height;
	private boolean can;
	private boolean noodle;
	private location origin; 
	
	public Stack(int height, boolean can, boolean noodle, location origin){
		this.height = height;
		this.can = can;
		this.noodle = noodle;
		this.origin = origin;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean getCan(){
		return can;
	}
	
	public boolean getNoodle(){
		return noodle;
	}
	
	public location getLocation(){
		return origin;
	}

}
