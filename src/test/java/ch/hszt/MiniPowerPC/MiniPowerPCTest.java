package ch.hszt.MiniPowerPC;

import junit.framework.TestCase;
import org.junit.Test;

import ch.hszt.MiniPowerPC.helper.Helper;

public class MiniPowerPCTest extends TestCase {
	MemoryEntry[] m;
	MiniPowerPC instance;
		
	@Test
	public void testLWDDSWDD(){
		m = new MemoryEntry[1024];
	
		int i1 = 100;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == i1);
	}
	
	@Test
	public void testADD(){
		m = new MemoryEntry[1024];
		
		int i1 = 100;
		int i2 = 150;
		//Addition zweier Zahlen
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
			
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testADDD(){
		m = new MemoryEntry[1024];
		
		int i1 = 100;
		int i2 = 150;
		
		String i2str = String.valueOf(Helper.intToBinaryCharArray(i2, 15));
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry(("1"+i2str).toCharArray()); //ADDD i2
		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testINC(){
		m = new MemoryEntry[1024];	
		int i1 = 1337;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0000000100000000".toCharArray()); //INC
		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (++i1));
	}
	
	@Test
	public void testDEC(){
		m = new MemoryEntry[1024];
		int i1 = 1337;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0000010000000000".toCharArray()); //DEC
		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (--i1));
	}
	
	@Test
	public void testSRA(){
		m = new MemoryEntry[1024];
		int i1 = -128;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0000010100000000".toCharArray()); //SRA
		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		System.out.println("SRA: " + m[504].getNumericValue());
		
		assertTrue(m[504].getNumericValue() == (i1/2));
	}
	
	@Test
	public void testSLA(){
		m = new MemoryEntry[1024];
		int i1 = -128;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0000100000000000".toCharArray()); //SLA
		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1*2));
	}
	
	@Test
	public void testSRL(){
		m = new MemoryEntry[1024];
		int i1 = -1;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[102] = new MemoryEntry("0000100100000000".toCharArray()); //SRL
 		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (32767));
	}
	
	@Test
	public void testSLL(){
		m = new MemoryEntry[1024];
		int i1 = -1;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[102] = new MemoryEntry("0000110000000000".toCharArray()); //SLL
 		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
				
		assertTrue(m[504].getNumericValue() == (-2));
	}
	
	@Test
	public void testAND(){
		m = new MemoryEntry[1024];
		int i1 = 256;
		int i2 = 257;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011000000000".toCharArray()); //AND 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
			
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (256));
	}
	
	@Test
	public void testOR(){
		m = new MemoryEntry[1024];
		int i1 = 256;
		int i2 = 257;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011100000000".toCharArray()); //OR 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
			
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (257));
	}
	
	@Test
	public void testNOT(){
		m = new MemoryEntry[1024];
		int i1 = -1;
		
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[102] = new MemoryEntry("0000000000000000".toCharArray()); //NOT
 		m[104] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		
		instance = new MiniPowerPC(m);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (0));
	}
	
	@Test
	public void testB(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0001110000000000".toCharArray()); //B 3
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0100111000000000".toCharArray()); //LWDD 3, 512
		m[124] = new MemoryEntry("0001010000000000".toCharArray()); //B 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr1
		m[512] = MemoryEntry.parseMemoryEntry(126); //Jump Adr2
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBZdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0001011000000000".toCharArray()); //BZ 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	@Test
	public void testBZdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[122] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[124] = new MemoryEntry("0001011000000000".toCharArray()); //BZ 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(110);
		instance.run();
		
		assertTrue(m[504] == null);
	}
	
	@Test
	public void testBNZdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0000001010000000".toCharArray()); //CLR 0
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0001010100000000".toCharArray()); //BNZ 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr
		
		instance = new MiniPowerPC(m);
		instance.setAkku(1);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBNZdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0000001010000000".toCharArray()); //CLR 0
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0001010100000000".toCharArray()); //BNZ 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr
		
		instance = new MiniPowerPC(m);
		instance.setAkku(0);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504] == null);
	}
	
	@Test
	public void testBCdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0001111100000000".toCharArray()); //BC 3
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0100111000000000".toCharArray()); //LWDD 3, 512
		m[124] = new MemoryEntry("0001011100000000".toCharArray()); //BC 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr1
		m[512] = MemoryEntry.parseMemoryEntry(126); //Jump Adr2
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(120);
		instance.setCarryFlag((short)0);
		instance.run();
		
		assertTrue(m[504] == null);
	}
	
	@Test
	public void testBCdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0001111100000000".toCharArray()); //BC 3
		
		//Load jump addr & jump to 100
		m[120] = new MemoryEntry("0100010111111110".toCharArray()); //LWDD 1, 510
		m[122] = new MemoryEntry("0100111000000000".toCharArray()); //LWDD 3, 512
		m[124] = new MemoryEntry("0001011100000000".toCharArray()); //BC 1
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		m[510] = MemoryEntry.parseMemoryEntry(100); //Jump Adr1
		m[512] = MemoryEntry.parseMemoryEntry(128); //Jump Adr2
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(120);
		instance.setCarryFlag((short)1);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBD(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0010000001111010".toCharArray()); //BD 122
		
		//jump to 100
		m[120] = new MemoryEntry("0010000001100100".toCharArray()); //BD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBZDdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		
		//jump to 100
		m[120] = new MemoryEntry("0011000001100100".toCharArray()); //BZD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setAkku(0);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBZDdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		
		//jump to 100
		m[120] = new MemoryEntry("0011000001100100".toCharArray()); //BZD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setAkku(1);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504] == null);
	}
	
	@Test
	public void testBNZDdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0000001010000000".toCharArray()); //CLR 0
		
		//jump to 100
		m[120] = new MemoryEntry("0010100001100100".toCharArray()); //BNZD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setAkku(1);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	@Test
	public void testBZNDdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0000001010000000".toCharArray()); //CLR 0
		
		//jump to 100
		m[120] = new MemoryEntry("0010100001100100".toCharArray()); //BNZD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setAkku(0);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504] == null);
	}
	
	@Test
	public void testBCDdoJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0011100001111010".toCharArray()); //BCD 122
		
		//jump to 100
		m[120] = new MemoryEntry("0011100001100100".toCharArray()); //BCD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setCarryFlag((short)1);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504].getNumericValue() == (i1+i2));
	}
	
	
	@Test
	public void testBCDdontJump(){
		m = new MemoryEntry[1024];
		int i1 = 100;
		int i2 = 150;
		
		//Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[108] = new MemoryEntry("0011100001111010".toCharArray()); //BCD 122
		
		//jump to 100
		m[120] = new MemoryEntry("0011100001100100".toCharArray()); //BCD 100
		
		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		instance = new MiniPowerPC(m);
		instance.setCarryFlag((short)0);
		instance.setInstructionCounter(120);
		instance.run();
		
		assertTrue(m[504] == null);
	}
}
