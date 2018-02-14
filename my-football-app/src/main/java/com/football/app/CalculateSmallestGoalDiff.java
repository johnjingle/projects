package com.football.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

/**
 * This class is simply used to call the class {@link ProcessFootballTableFile}
 *
 * @author john
 * @version 1.0
 * @since 19-08-2017
 */
public class CalculateSmallestGoalDiff 
{
    public static void main( String[] args )
    {            
        ProcessFootballTableFile processFile = new ProcessFootballTableFile();
        
        // The file should be of the agreed format and reside in the project home directory if running in Eclipse.
        // If run from the command line in the target directory using java -jar my-football-app-1.0-SNAPSHOT-shaded.jar
        // then the file should be present there.
        // The fileName (and path) could be passed as a parameter if necessary but is not.
        BufferedReader bufferedReader = null;
		
        try 
		{
			bufferedReader = processFile.openFile("football-results.txt");
		} 
        catch (FileNotFoundException e) 
        {

			System.out.println("The requested file cannot be found. Please ensure it is in the correct directory as described in README");
		}
        TreeMap<Integer, StringBuilder> results  = processFile.processFile(bufferedReader);
        
        try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println("The answer is " + results.get(results.firstKey()).toString() +" with goal difference of " + results.firstKey() );
    }
}
