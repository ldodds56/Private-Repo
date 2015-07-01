package tournament;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import scouting_data.MatchData;

public class Tournament {
	private ArrayList<Team> teams = new ArrayList<Team>();
	private static String filePath = "C:\\Users\\Laura\\Desktop\\TeamList.csv";
	
	public void setData(ArrayList<MatchData> matches){
		for(MatchData data : matches){
			for(Team team: teams){
				if(team.getTeamNumber() == data.getTeamNumber()){
					team.setMatch(data);
				}
			}
		}
	}
	
	public void setTeams(){
		try{
			ArrayList<Team> teamList = new ArrayList<Team>();
			BufferedReader br = new BufferedReader(new FileReader( filePath ));
			String line = br.readLine();
			
			while(line != null){
				teamList.add(new Team(Integer.parseInt(line)));
				line = br.readLine();
			}
			
			for(Team team : teamList){
				if(team != null) { teams.add(team);	}
			}
			
			br.close();
		}
		catch(FileNotFoundException e){ }
		catch(IOException e){ }
		finally { }
	}
	
	public ArrayList<Team> getTeams(){
		return teams;
	}
	
}
