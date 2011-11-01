package ch.hszt.MiniPowerPC;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoryEntry m = new MemoryEntry((short)5);
		System.out.println(m.getBinaryString());
		m = new MemoryEntry((short)-5);
		System.out.println(MemoryEntry.parseMemoryEntry(m.getBinaryString()).getValue());
	}

}
