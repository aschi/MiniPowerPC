package ch.hszt.MiniPowerPC;

public class Test {

	/**
	 * Baschdle =D
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MemoryEntry[] m = new MemoryEntry[1023];
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		
		m[500] = MemoryEntry.parseMemoryEntry(100);
		m[502] = MemoryEntry.parseMemoryEntry(150);
		
		MiniPowerPC emu = new MiniPowerPC(m);
		emu.run();
	}

}
