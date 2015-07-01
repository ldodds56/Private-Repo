package scouting_data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScoutingData {
	private String filePath = "C:\\Users\\Laura\\Desktop\\midknight mayhem.csv";
	private String csvSplitBy = "\t";
	private ArrayList<MatchData> matches = new ArrayList<MatchData>();
	
	public void separateFile(){
		try{
			BufferedReader br = new BufferedReader(new FileReader( filePath ));
			String line = br.readLine();
			line = br.readLine();
			while(line != null){
				String[] data = line.split(csvSplitBy);
				MatchData match = new MatchData(data);
				matches.add(match);
				line = br.readLine();
			}
			br.close();
		} 
		catch(FileNotFoundException e){}
		catch(IOException e){ }
		finally { }
	}
	
	public ArrayList<MatchData> getMatchData(){
		return matches;
	}

}
