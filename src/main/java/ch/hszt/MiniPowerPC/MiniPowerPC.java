package ch.hszt.MiniPowerPC;

public class MiniPowerPC {
	short carryFlag = 0;
	MemoryEntry[] memory = new MemoryEntry[1023];
	MemoryEntry[] register = new MemoryEntry[3]; //register[0] = akku, 1-3 register
	short instructionCounter;
	
	public MiniPowerPC(MemoryEntry[] memory){
		this.memory = memory;
	}
	
	public void run(){
		instructionCounter = 100;
		Instruction i;
		
		
		while(instructionCounter < 500){
			if(memory[instructionCounter] != null){
				i = Instruction.parseInstruction(memory[instructionCounter]);
				i.run(this);
			}
			instructionCounter++;
		}
	}
	
	/**
	 * Register rnr := 0
	 * @param rnr Registernummer (0 - 3)
	 */
	public void clr(short rnr){
		register[rnr].setValue(0);
	}
	
	/**
	 * Akku := Akku + Register rnr
	 * @param rnr Registernummer 0-2
	 */
	public void add(short rnr){
		register[0]+=register[rnr];
	}
	
	/**
	 * Akku := Akku + zahl
	 * @param zahl Zahl die hinzugefügt wird
	 */
	public void addd(short no){
		register[0]+=no;
	}
	
	/**
	 * Akku++;
	 */
	public void inc(){
		register[0]++;
	}
	
	/**
	 * Akku--;
	 */
	public void dec(){
		register[0]--;
	}
	
	/**
	 * Register rnr := Speicher(adr)
	 * @param rnr register nummer
	 * @param adr memory address
	 */
	public void lwdd(short rnr, short adr){
		register[rnr] = memory[adr].getValue();
	}
	
	/**
	 * Speicher(adr) := Register rnr
	 * @param rnr register nummer
	 * @param adr speicheradresse
	 */
	public void swdd(short rnr, short adr){
		memory[adr] = new MemoryEntry(register[rnr]);
	}
	
	/**
	 * Arithmetical shift right
	 */
	public void sra(){
		MemoryEntry m = new MemoryEntry(register[0]);
		String b = m.getBinaryString();
		carryFlag = Short.parseShort(b.substring(b.length()-1));
		
		char[] ca = b.toCharArray();
		
		//shift left (dont touch first 2 bits)
		for(int i = 3;i < ca.length;i++){
			ca[i] = ca[i-1];
		}
		//add a 0 to fill the gap
		ca[2] = 0;
		
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(ca)).getValue();
	}
	
	/**
	 * Arithmetical shift left
	 */
	public void sla(){
		MemoryEntry m = new MemoryEntry(register[0]);
		String b = m.getBinaryString();
		carryFlag = Short.valueOf(b.substring(2, 2));
		
		//shift left
		char[] ca = b.toCharArray();
		for(int i = 14;i > 0;i--){
			ca[i] = ca[i+1];
		}
		//add a 0 to fill the gap
		ca[15] = 0;
		
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(ca)).getValue();
	}
	
	/**
	 * Logical shift right
	 */
	public void srl(){
		MemoryEntry m = new MemoryEntry(register[0]);
		String b = m.getBinaryString();
		carryFlag = Short.parseShort(b.substring(b.length()-1));
		
		char[] ca = b.toCharArray();
		//shift right
		for(int i = 1;i < ca.length;i++){
			ca[i] = ca[i-1];
		}
		//add a 0 to fill the gap
		ca[0] = 0;
		
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(ca)).getValue();
	}
	
	/**
	 * Logical shift left
	 */
	public void sll(){
		MemoryEntry m = new MemoryEntry(register[0]);
		String b = m.getBinaryString();
		carryFlag = Short.valueOf(b.substring(1, 1));
		
		//shift left
		char[] ca = b.toCharArray();
		for(int i = 14;i >= 0;i--){
			ca[i] = ca[i+1];
		}
		//add a 0 to fill the gap
		ca[15] = 0;
		
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(ca)).getValue();
	}
	
	/**
	 * Binary AND
	 * @param rnr
	 */
	public void and(short rnr){
		MemoryEntry ma = new MemoryEntry(register[0]);
		MemoryEntry mr = new MemoryEntry(register[rnr]);
		
		char[] caa = ma.getBinaryString().toCharArray();
		char[] car = mr.getBinaryString().toCharArray();
		
		for(int i = 0; i < caa.length;i++){
			if(caa[i] == '1' && car[i] == '1'){
				caa[i] = '1';
			}else{
				caa[i] = '0';
			}
		}
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(caa)).getValue();
	}
	
	/**
	 * Binary OR
	 * @param rnr
	 */
	public void or(short rnr){
		MemoryEntry ma = new MemoryEntry(register[0]);
		MemoryEntry mr = new MemoryEntry(register[rnr]);
		
		char[] caa = ma.getBinaryString().toCharArray();
		char[] car = mr.getBinaryString().toCharArray();
		
		for(int i = 0; i < caa.length;i++){
			if(caa[i] == '1' || car[i] == '1'){
				caa[i] = '1';
			}else{
				caa[i] = '0';
			}
		}
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(caa)).getValue();
	}
	
	/**
	 * Binary NOT
	 */
	public void not(){
		MemoryEntry m = new MemoryEntry(register[0]);
		char[] ca = m.getBinaryString().toCharArray();
		for(int i = 0;i < ca.length;i++){
			ca[i] = (ca[i] == '1' ? '0' : '1');
		}
		register[0] = MemoryEntry.parseMemoryEntry(String.valueOf(ca)).getValue();
	}
	
	/**
	 * Conditional Jump. If Akku == 0 jump to the address in register rnr
	 * @param rnr
	 */
	public void bz(short rnr){
		if(register[0] == 0){
			b(rnr);
		}
	}
	
	/**
	 * Conditional Jump. If Akku != 0 jump to the address in register rnr
	 * @param rnr
	 */
	public void bnz(short rnr){
		if(register[0] != 0){
			b(rnr);
		}
	}
	
	/**
	 * Conditional Jump. If carryFlag is set, jump to the address in register rnr
	 * @param rnr
	 */
	public void bc(short rnr){
		if(carryFlag == 1){
			b(rnr);
		}
	}
	
	/**
	 * Unconditional Jump. Jump to the address in register rnr
	 * @param rnr
	 */
	public void b(short rnr){
		instructionCounter = register[rnr];
	}
	
	/**
	 * Conditional Jump. If Akku == 0 jump to the given address
	 * @param adr
	 */
	public void bzd(short adr){
		if(register[0] == 0){
			bd(adr);
		}
	}
	
	/**
	 * Conditional Jump. If Akku != 0 jump to the given address
	 * @param rnr
	 */
	public void bnzd(short adr){
		if(register[0] != 0){
			bd(adr);
		}
	}
	
	/**
	 * Conditional Jump. If carryFlag is set, jump to the given address
	 * @param rnr
	 */
	public void bcd(short adr){
		if(carryFlag == 1){
			bd(adr);
		}
	}
	
	/**
	 * Unconditional Jump. Jump to the given address
	 * @param adr
	 */
	public void bd(short adr){
		instructionCounter = adr;
	}
	
}
