package ch.hszt.MiniPowerPC.helper;

public class Helper {

	/**
	 * Builds a fixed length, binary complements of two charArray representing the given input value
	 * @param input input value to be converted
	 * @param length fixed length
	 * @return
	 */
	public static char[] intToBinaryCharArray(int input, int length) throws IllegalArgumentException{
		//check if input is in range of a 'length'-bit binary complements of two
		if(input < (-1*Math.pow(2, length)) || input > (Math.pow(2, length)-1)){
			throw new IllegalArgumentException();
		}
		
		String outputString;
		StringBuffer output = new StringBuffer("");

		// calculate binary for value
		int n = Math.abs(input);
		while (n > 0) {
			output.append(n % 2);
			n /= 2;
		}

		// reverse / normalize string length
		for (int x = 0; x < length; x++) {
			output.append("0");
		}
		outputString = output.reverse().substring(output.length() - length);
		// build complement
		if (input < 0) {
			// invert
			char[] ca = outputString.toCharArray();
			for (int i = 0; i < ca.length; i++) {
				ca[i] = (ca[i] == '1' ? '0' : '1');
			}

			// add 1
			int i = ca.length - 1;
			while (ca[i] == '1' && i > 0) {
				ca[i] = '0';
				i--;
			}
			ca[i] = '1';

			outputString = String.valueOf(ca);
		}
		return outputString.toCharArray();
	}

	/**
	 * Parses a complements of two and returns the integer equivalent
	 * @param input charArray representing an binary string
	 * @return integer value of the input
	 */
	public static int binaryCharArrayToInt(char[] input, boolean signed) {
		// check if it's positive
		//clone chararray
		char[] ca = input.clone();
		
		if (ca[0] == '0' || !signed) {
			// > 0 => Parse it as usual
			return binToDec(ca);
		} else {
			// < 0 => convert
			// --1
			int i = ca.length - 1;
			while (ca[i] == '0' && i > 0) {
				ca[i] = '1';
				i--;
			}
			ca[i] = '0';

			// invert
			for (i = 0; i < ca.length; i++) {
				ca[i] = (ca[i] == '1' ? '0' : '1');
			}
			return -1*binToDec(ca);
		}
	}
	
	public static int binToDec(char[] input){
		//clone chararray
		char[] ca = input.clone();
		
		int sum = 0;
		for(int n=0; n < ca.length;n++){
			sum+=(ca[n]=='1' ? Math.pow(2, ca.length-n-1) : 0);
		}
		return sum;
	}
}
