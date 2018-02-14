/**
 * 
 */
package com.football.app;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author john
 *
 */
public class ProcessFootballTableFileTest {

	/**
	 * Test method for {@link com.football.app.ProcessFootballTableFile#OpenFile(java.lang.String)}.
	 */
	@Test
	public void testOpenFile() {
		
		ProcessFootballTableFile processFootballTableFile = new ProcessFootballTableFile();
		BufferedReader reader = null;
		try {
			reader = processFootballTableFile.openFile("football-results.txt");
		} catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		assertNotNull(reader);
	}
	
	/**
	 * Test method for {@link com.football.app.ProcessFootballTableFile#OpenFile(java.lang.String)}.
	 */
	@Test
	public void testOpenFileException() {
		
		ProcessFootballTableFile processFootballTableFile = new ProcessFootballTableFile();
		BufferedReader reader = null;
		try 
		{
			reader = processFootballTableFile.openFile("fffootball-results.txt");
		}
	    catch (FileNotFoundException e1) 
		{
	    	// do nothing
		}	
		assertNull(reader);
	}
	
	/**
	 * Test method for {@link com.football.app.ProcessFootballTableFile#processFile(BufferedReader)}.
	 */
	@Test
	public void testProcessFile() {
		
		ProcessFootballTableFile processFootballTableFile = new ProcessFootballTableFile();
		FileReader reader = null;
		try 
		{
			reader = new FileReader("football-results.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		TreeMap<Integer, StringBuilder> results = processFootballTableFile.processFile(bufferedReader);
			
		assertEquals(1, results.firstKey(), 0);
		assertEquals("Aston_Villa", results.get(results.firstKey()).toString());
	}

}
