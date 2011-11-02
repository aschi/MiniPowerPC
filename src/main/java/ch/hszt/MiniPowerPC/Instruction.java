package ch.hszt.MiniPowerPC;

import java.util.regex.Pattern;

public class Instruction {
	short memoryAddress;
	short rnr;
	short number;
	InstructionSet instruction;
	
	private Instruction(short memoryAddress, short rnr, short number, InstructionSet instruction){
		this.memoryAddress = memoryAddress;
		this.rnr = rnr;
		this.number = number;
		this.instruction = instruction;
	}
	
	public static Instruction parseInstruction(MemoryEntry input){
		String b = input.getBinaryString(); 	
		
		Pattern p;
		
		short memoryAddress = (short)Integer.parseInt(b.substring(7));
		short rnr = (short)Integer.parseInt(b.substring(5, 6), 2);
		short number = MemoryEntry.parseMemoryEntry(b.substring(2)).getValue();
		InstructionSet instruction;
		
		//CLR Rnr
		p = Pattern.compile("^0000[0,1][0,1]101*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.CLR;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//ADD Rnr
		p = Pattern.compile("^0000[0,1][0,1]111*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.ADD;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//ADDD
		if(b.substring(1, 1).equals("1")){
			instruction = InstructionSet.ADDD;
			memoryAddress = -1;
			rnr = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//INC
		if(b.substring(1, 8).equals("00000001")){
			instruction = InstructionSet.INC;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//DEC
		if(b.substring(1, 8).equals("00000100")){
			instruction = InstructionSet.DEC;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//LWDD
		if(b.substring(1, 3).equals("010")){
			instruction = InstructionSet.LWDD;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
			
		}
		
		//SWDD
		if(b.substring(1, 3).equals("011")){
			instruction = InstructionSet.SWDD;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//SRA
		if(b.substring(1, 8).equals("00000101")){
			instruction = InstructionSet.SRA;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//SLA
		if(b.substring(1, 8).equals("00001000")){
			instruction = InstructionSet.SLA;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}

		//SRL
		if(b.substring(1, 8).equals("00001001")){
			instruction = InstructionSet.SRL;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//SLL
		if(b.substring(1, 8).equals("00001100")){
			instruction = InstructionSet.SLL;
			memoryAddress = -1;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//AND
		p = Pattern.compile("^0000[0,1][0,1]100*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.AND;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//OR
		p = Pattern.compile("^0000[0,1][0,1]110*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.OR;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//NOT
		if(b.substring(1, 8).equals("00000000")){
			instruction = InstructionSet.NOT;
			rnr = -1;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BZ
		p = Pattern.compile("^0001[0,1][0,1]10*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.BZ;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BNZ
		p = Pattern.compile("^0001[0,1][0,1]01*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.BNZ;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BC
		p = Pattern.compile("^0001[0,1][0,1]11*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.BC;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//B
		p = Pattern.compile("^0001[0,1][0,1]00*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.B;
			memoryAddress = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BZD
		if(b.substring(1, 5).equals("00110")){
			instruction = InstructionSet.BZD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BNZD
		if(b.substring(1, 5).equals("00101")){
			instruction = InstructionSet.BNZD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BCD
		if(b.substring(1, 5).equals("00111")){
			instruction = InstructionSet.BCD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//BD
		if(b.substring(1, 5).equals("00100")){
			instruction = InstructionSet.BD;
			rnr = -1;
			number = -1;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		return null;
	}
	
	public short getMemoryAddress() {
		return memoryAddress;
	}

	public short getRnr() {
		return rnr;
	}


	public short getNumber() {
		return number;
	}

	public InstructionSet getInstruction() {
		return instruction;
	}
	
	public void run(MiniPowerPC emu){
		switch(instruction){
		case CLR:
			emu.clr(rnr);
			break;
		case ADD:
			emu.add(rnr);
			break;
		case ADDD:
			emu.add(number);
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
