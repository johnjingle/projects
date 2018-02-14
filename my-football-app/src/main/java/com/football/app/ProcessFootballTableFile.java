package com.football.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * The ProcessFootballTableFile class implements a method
 * that reads a football table file calculates the absolute goal difference
 * and returns a TreeMap with the goal difference and the team.
 * 
 * @author john
 * @version 1.0
 * @since 19-08-2017
 *
 */
public class ProcessFootballTableFile {

	 static final int ONE = 1;
	 static final int TWO = 2;
	 
	 // long enough for team  name
	 static final int FIFTEEN = 15;
	  
/**
 * Process the given file calculating the absolute goal difference and putting the goal difference
 * and the team in a TreeMap which is returned.
 * For the purposes of this exercise assumes only one team has the smallest goal difference.
 * The file to be processed has a predefined format and the position of Team, F and A in the header
 * is used to find the position of the actual values.
 *
 * @param filename the name of the file to be processed. Must exist in the project home directory
 * in the predefined format.
 * @return teams {@link TreeMap}
 * @throws FileNotFoundException 
 */
	
protected BufferedReader openFile (String fileName) throws FileNotFoundException	 
{
	 FileReader reader = new FileReader(fileName);
	 BufferedReader bufferedReader = new BufferedReader(reader);
	 return bufferedReader;
}
	 
protected TreeMap<Integer, StringBuilder> processFile(BufferedReader bufferedReader)
{ 	
	 TreeMap<Integer, StringBuilder> teams = new TreeMap<Integer, StringBuilder>();
	 
	 int forGoalsIndex =0;
	 int againstGoalsIndex =0;
	 int teamIndex =0;
	 
	 StringBuilder forGoalsString = new StringBuilder();
	 StringBuilder againstGoalsString = new StringBuilder();
	 
	 StringBuilder team = new StringBuilder();
	 
	 int forGoals =0;
	 int againstGoals =0;	 
	 int goalsDiff=0;;
	 	 
	 try
	 {
	     
	     String line = null;
	     boolean firstLine = true;
	     
	     while ((line = bufferedReader.readLine()) != null) 
	     {
	        if(firstLine)
	        {
	        	teamIndex = line.indexOf('T');
	        	forGoalsIndex = line.indexOf('F');
	        	againstGoalsIndex = line.indexOf('A');
	        	firstLine = false;
	        } 
	        else 
	        {
	        	team.append(line.substring(teamIndex , teamIndex +FIFTEEN).trim());
		        forGoalsString.append(line.substring(forGoalsIndex , forGoalsIndex +TWO ));
		        againstGoalsString.append(line.substring(againstGoalsIndex , againstGoalsIndex +TWO ));
		    	
		        //ignore the ---- line.
		        if(!("--").equals(forGoalsString.toString()))
		        {
			    	forGoals = Integer.parseInt(forGoalsString.toString());
			    	againstGoals = Integer.parseInt(againstGoalsString.toString());			    	
			    	goalsDiff = Math.abs(forGoals - againstGoals);
			    	teams.put(goalsDiff, new StringBuilder().append(team.toString()));
			    				    	
		        }
		        team.delete(0, team.length());
		        forGoalsString.delete(0, forGoalsString.length());
		        againstGoalsString.delete(0, againstGoalsString.length());
		    	
	        }
	     }
	 
	     
	 } catch (IOException x) 
	 {
	     System.err.println(x);
	 }
	return teams;
   }
}
