package ch.hszt.MiniPowerPC;

import java.util.regex.Pattern;

import ch.hszt.MiniPowerPC.helper.Helper;

public class Instruction {
	char[] binaryString;
	short memoryAddress;
	short rnr;
	int number;
	InstructionSet instruction;

	/**
	 * Returns the "Mnemonics" of the instruction
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean firstNo = true;

		sb.append(instruction.toString());
		if (rnr != -1) {
			sb.append((firstNo ? " " : ", "));
			sb.append("R" + rnr);
			firstNo = false;
		}
		if (instruction == InstructionSet.ADDD) {
			sb.append((firstNo ? " " : ", "));
			sb.append("#" + number);
			firstNo = false;
		} else {
			if (memoryAddress != -1) {
				sb.append((firstNo ? " " : ", "));
				sb.append("#" + memoryAddress);
				firstNo = false;
			}
		}
		return sb.toString();
	}

	private Instruction(short memoryAddress, short rnr, int number,
			InstructionSet instruction, char[] binaryString) {
		this.memoryAddress = memoryAddress;
		this.rnr = rnr;
		this.number = number;
		this.instruction = instruction;
		this.binaryString = binaryString;
	}

	public String getBinaryString() {
		return String.valueOf(binaryString);
	}

	public static Instruction parseInstruction(MemoryEntry input) {
		String b = String.valueOf(input.getBinaryString());
		Pattern p;

		short memoryAddress = (short) Helper.binaryCharArrayToInt(b
				.substring(6).toCharArray(), false);
		short rnr = (short) Integer.parseInt(b.substring(4, 6), 2);
		int number = Helper.binaryCharArrayToInt(b.substring(1).toCharArray(),
				true);
		InstructionSet instruction;

		/*
		 * //Debugging info System.out.println("b:" + b);
		 * System.out.println("memoryAddress:" + memoryAddress);
		 * System.out.println("rnr:" + rnr); System.out.println("number:" +
		 * number);
		 */
		// CLR Rnr
		p = Pattern.compile("^0000[01][01]101");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.CLR;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// ADD Rnr
		p = Pattern.compile("^0000[01][01]111");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.ADD;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// ADDD
		if (b.substring(0, 1).equals("1")) {
			instruction = InstructionSet.ADDD;
			memoryAddress = -1;
			rnr = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// INC
		if (b.substring(0, 8).equals("00000001")) {
			instruction = InstructionSet.INC;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// DEC
		if (b.substring(0, 8).equals("00000100")) {
			instruction = InstructionSet.DEC;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// LWDD
		if (b.substring(0, 3).equals("010")) {
			instruction = InstructionSet.LWDD;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());

		}

		// SWDD
		if (b.substring(0, 3).equals("011")) {
			instruction = InstructionSet.SWDD;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// SRA
		if (b.substring(0, 8).equals("00000101")) {
			instruction = InstructionSet.SRA;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// SLA
		if (b.substring(0, 8).equals("00001000")) {
			instruction = InstructionSet.SLA;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// SRL
		if (b.substring(0, 8).equals("00001001")) {
			instruction = InstructionSet.SRL;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// SLL
		if (b.substring(0, 8).equals("00001100")) {
			instruction = InstructionSet.SLL;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// AND
		p = Pattern.compile("^0000[0,1][0,1]100");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.AND;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// OR
		p = Pattern.compile("^0000[01][01]110");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.OR;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// NOT
		if (b.substring(0, 8).equals("00000001")) {
			instruction = InstructionSet.NOT;
			rnr = -1;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// END
		if (b.substring(0, 8).equals("00000000")) {
			instruction = InstructionSet.END;
			rnr = -1;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BZ
		p = Pattern.compile("^0001[01][01]10");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.BZ;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BNZ
		p = Pattern.compile("^0001[01][01]01");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.BNZ;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BC
		p = Pattern.compile("^0001[01][01]11");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.BC;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// B
		p = Pattern.compile("^0001[01][01]00");
		if (p.matcher(b).find()) {
			instruction = InstructionSet.B;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BZD
		if (b.substring(0, 5).equals("00110")) {
			instruction = InstructionSet.BZD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BNZD
		if (b.substring(0, 5).equals("00101")) {
			instruction = InstructionSet.BNZD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BCD
		if (b.substring(0, 5).equals("00111")) {
			instruction = InstructionSet.BCD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		// BD
		if (b.substring(0, 5).equals("00100")) {
			instruction = InstructionSet.BD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction,
					input.getBinaryString());
		}

		return null;
	}

	public short getMemoryAddress() {
		return memoryAddress;
	}

	public short getRnr() {
		return rnr;
	}

	public int getNumber() {
		return number;
	}

	public InstructionSet getInstruction() {
		return instruction;
	}

	public void run(MiniPowerPC emu) {
		//System.out.println(this.instruction + ";rnr:" + this.rnr
		//		+ ";memoryaddr:" + this.memoryAddress + ";no:" + this.number);

		switch (instruction) {
		case CLR:
			emu.clr(rnr);
			break;
		case ADD:
			emu.add(rnr);
			break;
		case ADDD:
			emu.addd(number);
			break;
		case INC:
			emu.inc();
			break;
		case DEC:
			emu.dec();
			break;
		case LWDD:
			emu.lwdd(rnr, memoryAddress);
			break;
		case SWDD:
			emu.swdd(rnr, memoryAddress);
			break;
		case SRA:
			emu.sra();
			break;
		case SLA:
			emu.sla();
			break;
		case SRL:
			emu.srl();
			break;
		case SLL:
			emu.sll();
			break;
		case AND:
			emu.and(rnr);
			break;
		case OR:
			emu.or(rnr);
			break;
		case NOT:
			emu.not();
			break;
		case END:
			emu.end();
			break;
		case BZ:
			emu.bz(rnr);
			break;
		case BNZ:
			emu.bnz(rnr);
			break;
		case BC:
			emu.bc(rnr);
			break;
		case B:
			emu.b(rnr);
			break;
		case BZD:
			emu.bzd(memoryAddress);
			break;
		case BNZD:
			emu.bnzd(memoryAddress);
			break;
		case BCD:
			emu.bcd(memoryAddress);
			break;
		case BD:
			emu.bd(memoryAddress);
		}
	}
}
