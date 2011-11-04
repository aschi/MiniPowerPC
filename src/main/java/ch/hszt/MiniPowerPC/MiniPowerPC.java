package ch.hszt.MiniPowerPC;

import ch.hszt.MiniPowerPC.helper.Helper;

public class MiniPowerPC {
	short carryFlag = 0;
	MemoryEntry[] memory;
	int[] register = new int[4]; // register[0] = akku, 1-3 register
	int instructionCounter = 100;

	public MiniPowerPC(MemoryEntry[] memory) {
		this.memory = memory;
	}
	
	public void setCarryFlag(short carryFlag) {
		this.carryFlag = carryFlag;
	}
	
	public void setAkku(int i){
		register[0] = i;
	}

	//Geter and seter for memory
	public MemoryEntry[] getMemory(){
		return memory;
	}

	public void setMemory(MemoryEntry[] memory){
		this.memory = memory;
	}
	
	public void setInstructionCounter(int instructionCounter){
		this.instructionCounter = instructionCounter;
	}
	
	public void run() {
		Instruction i;

		while (instructionCounter < 500) {
			if (memory[instructionCounter] != null) {
				i = Instruction.parseInstruction(memory[instructionCounter]);
				if (i != null) {
					System.out
							.println("--------------------------------------");
					for (int x = 0; x <= 3; x++) {
						System.out
								.println("register[" + x + "]:" + register[x]);
					}
					System.out.println("carryFlag: " + carryFlag);
					System.out.println("Instruction: "
							+ i.getInstruction().toString() + "; rnr: "
							+ i.getRnr() + "; memoryAddress: "
							+ i.getMemoryAddress() + "; number:"
							+ i.getNumber());
					System.out.println();
					
					i.run(this);
				}
			}
			instructionCounter++;
		}
	}

	/**
	 * Register rnr := 0
	 * 
	 * @param rnr
	 *            Registernummer (0 - 3)
	 */
	public void clr(short rnr) {
		register[rnr] = 0;
	}

	/**
	 * Akku := Akku + Register rnr
	 * 
	 * @param rnr
	 *            Registernummer 0-2
	 */
	public void add(short rnr) {
		register[0] += register[rnr];
	}

	/**
	 * Akku := Akku + zahl
	 * 
	 * @param zahl
	 *            Zahl die hinzugefügt wird
	 */
	public void addd(int no) {
		register[0] += no;
	}

	/**
	 * Akku++;
	 */
	public void inc() {
		register[0]++;
	}

	/**
	 * Akku--;
	 */
	public void dec() {
		register[0]--;
	}

	/**
	 * Register rnr := Speicher(adr)
	 * 
	 * @param rnr
	 *            register nummer
	 * @param adr
	 *            memory address
	 */
	public void lwdd(short rnr, short adr) {
		register[rnr] = memory[adr].getNumericValue();
	}

	/**
	 * Speicher(adr) := Register rnr
	 * 
	 * @param rnr
	 *            register nummer
	 * @param adr
	 *            speicheradresse
	 */
	public void swdd(short rnr, short adr) {
		memory[adr] = MemoryEntry.parseMemoryEntry(register[rnr]);
	}

	/**
	 * Arithmetical shift right
	 */
	public void sra() {
		MemoryEntry m = MemoryEntry.parseMemoryEntry(register[0]);
		carryFlag = Short.parseShort(String.valueOf(m.getBinaryString()[m
				.getBinaryString().length - 1]));

		char[] ca = m.getBinaryString();

		// shift left (dont touch first 2 bits)
		for (int i = ca.length - 1; i > 1; i--) {
			ca[i] = ca[i - 1];
		}

		
		register[0] = Helper.binaryCharArrayToInt(ca, true);
	}

	/**
	 * Arithmetical shift left
	 */
	public void sla() {
		MemoryEntry m = MemoryEntry.parseMemoryEntry(register[0]);
		carryFlag = Short.parseShort(String.valueOf(m.getBinaryString()[m
				.getBinaryString().length - 1]));

		char[] co = m.getBinaryString();
		char[] cn = new char[16];
		// shift left
		for (int i = 1; i < co.length - 1; i++) {
			cn[i] = co[i + 1];
		}
		// Keep first bit
		cn[0] = co[0];
		// add a 0 to fill the gap
		cn[15] = '0';

		register[0] = Helper.binaryCharArrayToInt(cn, true);
	}

	/**
	 * Logical shift right
	 */
	public void srl() {
		MemoryEntry m = MemoryEntry.parseMemoryEntry(register[0]);
		carryFlag = Short.parseShort(String.valueOf(m.getBinaryString()[m
				.getBinaryString().length - 1]));

		char[] ca = m.getBinaryString();
		
		// shift right
		for (int i = ca.length - 1; i > 0; i--) {
			ca[i] = ca[i - 1];
		}
		// add a 0 to fill the gap
		ca[0] = '0';
		register[0] = Helper.binaryCharArrayToInt(ca, true);
	}

	/**
	 * Logical shift left
	 */
	public void sll() {
		MemoryEntry m = MemoryEntry.parseMemoryEntry(register[0]);
		carryFlag = Short.parseShort(String.valueOf(m.getBinaryString()[0]));

		char[] ca = m.getBinaryString();
		// shift left
		for (int i = 0; i < ca.length-1; i++) {
			ca[i] = ca[i + 1];
		}
		// add a 0 to fill the gap
		ca[ca.length-1] = '0';
		register[0] = Helper.binaryCharArrayToInt(ca, true);
	}

	/**
	 * Binary AND
	 * 
	 * @param rnr
	 */
	public void and(short rnr) {
		MemoryEntry ma = MemoryEntry.parseMemoryEntry(register[0]);
		MemoryEntry mr = MemoryEntry.parseMemoryEntry(register[rnr]);

		char[] caa = ma.getBinaryString();
		char[] car = mr.getBinaryString();

		for (int i = 0; i < caa.length; i++) {
			if (caa[i] == '1' && car[i] == '1') {
				caa[i] = '1';
			} else {
				caa[i] = '0';
			}
		}
		register[0] = Helper.binaryCharArrayToInt(caa, true);
	}

	/**
	 * Binary OR
	 * 
	 * @param rnr
	 */
	public void or(short rnr) {
		MemoryEntry ma = MemoryEntry.parseMemoryEntry(register[0]);
		MemoryEntry mr = MemoryEntry.parseMemoryEntry(register[rnr]);

		char[] caa = ma.getBinaryString();
		char[] car = mr.getBinaryString();

		for (int i = 0; i < caa.length; i++) {
			if (caa[i] == '1' || car[i] == '1') {
				caa[i] = '1';
			} else {
				caa[i] = '0';
			}
		}
		register[0] = Helper.binaryCharArrayToInt(caa, true);
	}

	/**
	 * Binary NOT
	 */
	public void not() {
		MemoryEntry m = MemoryEntry.parseMemoryEntry(register[0]);
		char[] ca = m.getBinaryString();
		for (int i = 0; i < ca.length; i++) {
			ca[i] = (ca[i] == '1' ? '0' : '1');
		}
		register[0] = Helper.binaryCharArrayToInt(ca, true);
	}

	/**
	 * Conditional Jump. If Akku == 0 jump to the address in register rnr
	 * 
	 * @param rnr
	 */
	public void bz(short rnr) {
		if (register[0] == 0) {
			b(rnr);
		}
	}

	/**
	 * Conditional Jump. If Akku != 0 jump to the address in register rnr
	 * 
	 * @param rnr
	 */
	public void bnz(short rnr) {
		if (register[0] != 0) {
			b(rnr);
		}
	}

	/**
	 * Conditional Jump. If carryFlag is set, jump to the address in register
	 * rnr
	 * 
	 * @param rnr
	 */
	public void bc(short rnr) {
		if (carryFlag == 1) {
			b(rnr);
		}
	}

	/**
	 * Unconditional Jump. Jump to the address in register rnr
	 * 
	 * @param rnr
	 */
	public void b(short rnr) {
		instructionCounter = (register[rnr]-1); //-1 because it will be incremented immediately
	}

	/**
	 * Conditional Jump. If Akku == 0 jump to the given address
	 * 
	 * @param adr
	 */
	public void bzd(short adr) {
		if (register[0] == 0) {
			bd(adr);
		}
	}

	/**
	 * Conditional Jump. If Akku != 0 jump to the given address
	 * 
	 * @param rnr
	 */
	public void bnzd(short adr) {
		if (register[0] != 0) {
			bd(adr);
		}
	}

	/**
	 * Conditional Jump. If carryFlag is set, jump to the given address
	 * 
	 * @param rnr
	 */
	public void bcd(short adr) {
		if (carryFlag == 1) {
			bd(adr);
		}
	}

	/**
	 * Unconditional Jump. Jump to the given address
	 * 
	 * @param adr
	 */
	public void bd(short adr) {
		instructionCounter = (adr-1); //-1 because it will be incremented immediately
	}

}
