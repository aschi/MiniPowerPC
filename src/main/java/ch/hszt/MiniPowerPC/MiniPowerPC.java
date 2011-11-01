package ch.hszt.MiniPowerPC;

public class MiniPowerPC {
	int carryFlag = 0;
	MemoryEntry[] memory = new MemoryEntry[1023];
	short[] register = new short[3]; //register[0] = akku, 1-3 register
	int instructionCounter;
	
	public MiniPowerPC(MemoryEntry[] memory){
		this.memory = memory;
	}
	
	public void run(){
		instructionCounter = 100;
		
	}
	
	/**
	 * Register rnr := 0
	 * @param rnr Registernummer (0 - 3)
	 */
	private void clr(short rnr){
		register[rnr] = 0;
	}
	
	/**
	 * Akku := Akku + Register rnr
	 * @param rnr Registernummer 0-2
	 */
	private void add(short rnr){
		register[0]+=register[rnr];
	}
	
	/**
	 * Akku := Akku + zahl
	 * @param zahl Zahl die hinzugefügt wird
	 */
	private void addd(short no){
		register[0]+=no;
	}
	
	/**
	 * Akku++;
	 */
	private void inc(){
		register[0]++;
	}
	
	/**
	 * Akku--;
	 */
	private void dec(){
		register[0]--;
	}
	
	/**
	 * Register rnr := Speicher(adr)
	 * @param rnr register nummer
	 * @param adr memory address
	 */
	private void lwdd(short rnr, short adr){
		register[rnr] = memory[adr].getValue();
	}
	
	/**
	 * Speicher(adr) := Register rnr
	 * @param rnr register nummer
	 * @param adr speicheradresse
	 */
	private void swdd(short rnr, short adr){
		memory[adr] = new MemoryEntry(register[rnr]);
	}
	
	/**
	 * Akku := Akku /2
	 */
	private void sra(){
		String b = Integer.toBinaryString((int)register[0]);
		carryFlag = Short.parseShort(b.substring(b.length()-1));
		register[0]>>=1;
	}
	
	/**
	 * Akku := Akku x 2
	 */
	private void sla(){
		register[0]<<=1;
	}
}
