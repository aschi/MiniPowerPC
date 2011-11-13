package ch.hszt.MiniPowerPC;

import ch.hszt.MiniPowerPC.helper.Helper;

public class MemoryEntry implements Cloneable{
	private char[] binaryString = new char[15];
	
	/**
	 * Get-er for binaryString
	 * @return binaryString (char array)
	 */
	public char[] getBinaryString(){
		return binaryString;
	}
	
	/**
	 * Clone object
	 */
	public MemoryEntry clone(){
		return new MemoryEntry(binaryString.clone());
	}
	
	/**
	 * Constructor allows chars array of length 16
	 * @param binaryString
	 * @throws IllegalArgumentException
	 */
	public MemoryEntry(char[] binaryString) throws IllegalArgumentException{
		if(binaryString.length != 16){
			throw new IllegalArgumentException();
		}
		this.binaryString = binaryString;
	}
	
	/**
	 * Builds the complement of two of an integer value
	 * @param value integer to be converted
	 * @return memoryEntry containing the binaryString
	 */
	public static MemoryEntry parseMemoryEntry(int value){
		return new MemoryEntry(Helper.intToBinaryCharArray(value, 16));
	}
	
	
	public int getNumericValue(){
		return Helper.binaryCharArrayToInt(binaryString, true);
	}
}
