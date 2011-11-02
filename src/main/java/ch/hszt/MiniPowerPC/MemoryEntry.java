package ch.hszt.MiniPowerPC;

public class MemoryEntry {
	private short value;
	private String binaryString;
	
	public MemoryEntry(short value){
		this.value = value;
	}
	
	/**
	 * Get-er for the value
	 * @return return the value as short
	 */
	public short getValue(){
		return value;
	}
	
	/**
	 * Builds the complement of two of our number
	 * @return string representing the complement of two
	 */
	public String getBinaryString(){
		String outputString;
		StringBuffer output = new StringBuffer("");
		
		//calculate binary for value
		int n = Math.abs(value);
		while(n > 0){		
			output.append(n%2);
			n/=2;
		}
		
		//reverse / normalize string length to 16
		output.append("0000000000000000");
		outputString = output.reverse().substring(output.length()-16);
		//build complement
		if(value < 0){
			System.out.println("below 0");

			//invert
			char[] ca = outputString.toCharArray();
			for(int i = 0; i < ca.length;i ++){
				ca[i] = (ca[i] == '1' ? '0' : '1');				
			}
			
			System.out.println(ca);
			
			//add 1
			int i = ca.length-1;	
			while(ca[i] == 0 && i > 0){
				ca[i] = '0';
				i--;
			}
			ca[i] = '1';
			
			outputString = String.valueOf(ca);
		}
				
		return outputString;
	}
	
	/**
	 * Parses a complements of two and returns the MemoryEntry containing our short equivalent
	 * @param input complements of two. maximum length: 16 bit
	 * @return MemoryEntry containing the short equivalent of our input
	 */
	public static MemoryEntry parseMemoryEntry(String input) throws IllegalArgumentException{
		if(input.length() > 16){
			throw new IllegalArgumentException();
		}
		
		//check for positive number
		if(input.substring(1, 1) == "0"){
			// > 0 => Parse it as usual
			return new MemoryEntry(Short.parseShort(input, 2));
		}else{
			// < 0 => convert
			char[] ca = input.toCharArray();
			
			// --1
			int i = ca.length-1;
			while(ca[i] == '0' && i > 0){
				i--;
			}
			ca[i] = '0';
			
			//invert
			for(i = 0; i < ca.length;i ++){
				ca[i] = (ca[i] == '1' ? '0' : '1');				
			}
			return new MemoryEntry(Short.parseShort("-"+String.valueOf(ca), 2));
		}
	}
}
