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
		
		short memoryAddress = 0;
		short rnr = (short)Integer.parseInt(b.substring(5, 6), 2);
		short number = (short)Integer.parseInt(b.substring(7), 2);
		InstructionSet instruction;
		
		//CLR Rnr
		p = Pattern.compile("^0000[0,1][0,1]101*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.CLR;
			return new Instruction(memoryAddress, rnr, number, instruction);			
		}
		
		//ADD Rnr
		p = Pattern.compile("^0000[0,1][0,1]111*");
		if(p.matcher(b).find()){
			instruction = InstructionSet.ADD;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//ADDD
		if(b.substring(1, 1).equals("1")){
			instruction = InstructionSet.ADDD;
			number = MemoryEntry.parseMemoryEntry(b.substring(2)).getValue();
			rnr = 0;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//INC
		if(b.substring(1, 8).equals("00000001")){
			instruction = InstructionSet.INC;
			rnr = 0;
			return new Instruction(memoryAddress, rnr, number, instruction);	
		}
		
		//DEC
		if(b.substring(1, 8).equals("00000100")){
			instruction = InstructionSet.DEC;
			rnr = 0;
			return new Instruction(memoryAddress, rnr, number, instruction);
		}
		
		//LWDD
		if(b.substring(1, 3).equals("010")){
			instruction = InstructionSet.LWDD;
			return new Instruction(memoryAddress, rnr, number, instruction);
			
		}
		
		//SWDD
		if(b.substring(1, 3).equals("011")){
			instruction = InstructionSet.SWDD;
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
	
	public void runInstruction(MiniPowerPC emu){
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
			
			break;
		case SLA:
			
			break;
		case SRL:
			
			break;
		case SLL:
			
			break;
		case AND:
			
			break;
		case OR:
			
			break;
		case NOT:
			
			break;
		case BZ:
			
			break;
		case BNZ:
			
			break;
		case BC:
			
			break;
		case B:
			
			break;
		case BZD:
			
			break;
		case BNZD:
			
			break;
		case BCD:
			
			break;
		case BD:
				
		}
	}
}
