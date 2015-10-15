package main;

import scouting_data.ProcessedData;
import scouting_data.ScoutingData;
import tournament.Tournament;

public class Main {
	public static void main(String[] args){
		try{
		ScoutingData data = new ScoutingData();
		data.separateFile();
				
		Tournament tournament = new Tournament();
		tournament.setTeams();
		tournament.setData(data.getMatchData());
		
		ProcessedData pData = new ProcessedData(tournament);
		pData.writeProcessedData();
		}
		catch(Exception e){System.out.println("Failed"); }
		System.out.println("Done");
	}
}
