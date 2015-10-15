package scouting_data;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import scouting_data.MatchData.location;
import tournament.Match;
import tournament.Stack;
import tournament.Team;
import tournament.Tournament;

public class ProcessedData {
	private Tournament tournament;
	
	public ProcessedData(Tournament fullData){
		tournament = fullData;
	}
	
	public void writeProcessedData(){
		try{
			String filePath = "C:\\Users\\Laura\\Desktop\\FilteredData.csv";
			File f = new File(filePath);
			if(f.exists()){
				f.delete();
			}
			f.createNewFile();
			FileWriter file = new FileWriter(f, true);
			file.write("Team number \t");
			file.write("Average totes per match \t");
			file.write("Average stacks per match \t");
			file.write("Standard deviation of totes used per match \t");
			file.write("Use cans more than 60% \t");
			file.write("Percentage of stacks with cans \t");
			file.write("Percentage of stacks with noodles \t");
			file.write("Most used location \t");
			file.write("Successful three tote? \t");
			file.write("Average Cans Grabbed \t");
			file.write("Max Cans Grabbed \t");
			file.write("Number of Can Grabbings \n");
			for(Team team : tournament.getTeams()){
				file.write(Integer.toString(team.getTeamNumber()) + "\t");
				file.write(Double.toString(getAverageTotesPerMatch(team)) + "\t");
				file.write(Double.toString(getStacksPerMatch(team)) + "\t");
				file.write(Double.toString(getPointsPerMatch(team)) + "\t");
				file.write(Integer.toString(getMaxStackHeight(team)) + "\t");
				file.write(Boolean.toString(getCans(team) > .6) + "\t");
				file.write(Double.toString(getCans(team)) + "\t");
				file.write(Double.toString(getNoodles(team)) + "\t");
				file.write(getLocation(team) + "\t");
				file.write((getThreeTote(team) != 0 ? "Yes: " + getThreeTote(team) : "No") + "\t");
				file.write(Double.toString(getAverageCansGrabbed(team)) + "\t");
				file.write(Integer.toString(getMaxCansGrabbed(team)) + "\t");
				file.write(Integer.toString(getCanGrabbings(team)) + "\n");
				file.flush();
			}
			file.close();
			
		}catch(Exception e){ System.out.println(e); }
		
		//TODO: add "max" functions (maxCansGrabbed, maxStackHeight, maxStacksPerMatch)
	}
	
	//return average stack height
	private double getAverageTotesPerMatch(Team team){
		int matches = 0;
		int totalTotes = 0;
		for(Match match : team.getMatches()){
			matches++;
			for(Stack stack : match.getStacks()){
				if(stack.getHeight() != 0) {
					totalTotes += stack.getHeight();
				}
			}
		}
		if(matches == 0) return 0;
		return (double) totalTotes / matches;
	}
	
	//return average stacks per match
	private double getStacksPerMatch(Team team){
		int matches = 0;
		int stacksPerMatch = 0;
		for(Match match : team.getMatches()){
			matches++;
			for(Stack stack : match.getStacks()){
				if(stack.getHeight() != 0) {
					stacksPerMatch++;
				}
			}
		}
		if(matches == 0) return 0;
		return (double) stacksPerMatch / matches;
	}
	
	//return average stacks per match
	private double getPointsPerMatch(Team team){
		int matches = 0;
		int totesWithCan = 0;
		int totes = 0;
		for(Match match : team.getMatches()){
			matches++;
			for(Stack stack : match.getStacks()){
				if(stack.getCan()) {
					totesWithCan += stack.getHeight();
				}
				else{
					totes += stack.getHeight();
				}
			}
		}
		if(matches == 0) return 0;
		return (double) (6 * totesWithCan + 2 * totes) / matches;
	}
		
	//return standard dev of totes used per match
	private int getMaxStackHeight(Team team){
		int maxStack = 0;
		for(Match match : team.getMatches()){
			for(Stack stack : match.getStacks()){
				maxStack = Math.max(maxStack, stack.getHeight());
			}
		}
		return maxStack;
	}
	
	//return % use of can
	private double getCans(Team team){
		int totalCans = 0;
		int totalStacks = 0;
		for(Match match : team.getMatches()){
			for(Stack stack : match.getStacks()){
				if(stack.getHeight() != 0){
					totalStacks++;
					if(stack.getCan()) totalCans++;
				}
			}
		}
		return (double) totalCans / totalStacks;
	}
	
	//return % use of noodles
	private double getNoodles(Team team){
		int totalNoodles = 0;
		int totalStacks = 0;
		for(Match match : team.getMatches()){
			for(Stack stack : match.getStacks()){
				if(stack.getHeight() != 0){
					totalStacks++;
					if(stack.getNoodle()) totalNoodles++;
				}
			}
		}
		return (double) totalNoodles / totalStacks;
	}
	
	private location getLocation(Team team){
		int HPStacks = 0;
		int LFStacks = 0;
		location trend = location.EMPTY;
		for(Match match : team.getMatches()){
			for(Stack stack : match.getStacks()){
				if(stack.getLocation() != location.NOT_STACK){
					if(stack.getLocation() == location.HP){ 
						HPStacks++;
						trend = location.HP;
					}
					else if(stack.getLocation() == location.LF) {
						LFStacks++;
						trend = location.LF;
					}
					else {
						if(trend == location.HP) HPStacks++;
						else if(trend == location.LF) LFStacks++;
					}
				}
			}
		}
		if(HPStacks != 0 || LFStacks != 0){
			if(HPStacks > LFStacks) return location.HP;
			return location.LF;
		}
		return location.EMPTY;
	}
	
	//have they ever completed three tote?
	private int getThreeTote(Team team){
		int threeTote = 0;
		for(Match match : team.getMatches())
			if(match.getThreeTote())
				threeTote++;
		return threeTote;
	}
	
	//returns average cans grabbed per match
	private double getAverageCansGrabbed(Team team){
		int cansGrabbed = 0;
		int matches = 0;
		for(Match match : team.getMatches()){
			if(match.getCansGrabbed() != 0){
				cansGrabbed += match.getCansGrabbed();
				matches++;
			}
		}
		if(matches == 0) { return 0; }
		return (double) cansGrabbed / matches;
	}
	
	private int getMaxCansGrabbed(Team team){
		int cansGrabbed = 0;
		for(Match match : team.getMatches()){
			cansGrabbed = Math.max(cansGrabbed,	match.getCansGrabbed());
		}
		return cansGrabbed;
	}
	
	private int getCanGrabbings(Team team){
		int canGrabbings = 0;
		for(Match match : team.getMatches()){
			if(match.getCansGrabbed() != 0) canGrabbings++;
		}
		return canGrabbings;
	}
}
