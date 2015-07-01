package tournament;

import java.util.ArrayList;

import scouting_data.MatchData.location;

public class Match {
	private ArrayList<Stack> stacks = new ArrayList<Stack>();
	private boolean threeTote;
	private int cansGrabbed;
	
	public void setStack(int height, boolean can, boolean noodle, location origin){
		Stack stack = new Stack(height, can, noodle, origin);
		stacks.add(stack);
	}
	
	public ArrayList<Stack> getStacks(){
		return stacks;
	}
	
	public void setThreeTote(boolean threeTote){
		this.threeTote = threeTote;
	}
	
	public boolean getThreeTote(){
		return threeTote;
	}
	
	public void setCansGrabbed(int cansGrabbed){
		this.cansGrabbed = cansGrabbed;
	}
	
	public int getCansGrabbed(){
		return cansGrabbed;
	}
	
}
