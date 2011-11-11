package ch.hszt.MiniPowerPC.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.hszt.MiniPowerPC.MemoryEntry;

public class MemoryParser {

	/**
	 * Parse a text file formated as followed:
	 * 
	 * ADR BASEbVALUE
	 * 101 2b1000000000000001
	 * 500 10b150
	 * 502 10b200
	 * 
	 * @param f File to parse
	 * @return MemoryEntry array to be used by a MiniPowerPC
	 */
	public static MemoryEntry[] parseTextFile(File f){
		FileInputStream fis;
		MemoryEntry[] m = new MemoryEntry[1024];
		
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		String strLine;
		String memAddr;
		String base;
		String valueStr;
		MemoryEntry me;
		try {
			while ((strLine = in.readLine()) != null)   {
				memAddr = null; base = null; valueStr = null; //reset values
				
				//Parse
				memAddr = strLine.split(" ")[0];
				base = strLine.split(" ")[1].split("b")[0];
				valueStr = strLine.split(" ")[1].split("b")[1];
				
				//Check if parsing was successfull
				if(base != null && memAddr != null && valueStr != null){
					if(base == "2"){
						me = new MemoryEntry(valueStr.toCharArray());
					}else{
						me = MemoryEntry.parseMemoryEntry(Integer.parseInt(valueStr, Integer.parseInt(base)));
					}
					m[Integer.parseInt(memAddr)] = me;
				}else{
					System.out.println("Parsing error. Skipping row: '"+strLine+"'");
				}
			}
		} catch (IOException e) {
			return null;
		}
		return m;
	}
	
}
