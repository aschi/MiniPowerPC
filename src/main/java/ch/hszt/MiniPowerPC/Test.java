package ch.hszt.MiniPowerPC;

import ch.hszt.MiniPowerPC.GUI.MiniPowerPCGUI;
import ch.hszt.MiniPowerPC.GUI.MiniPowerPCUIIfc;

public class Test implements ControllerIfc {
           private MiniPowerPCUIIfc gui;
	/**
	 * Baschdle =D
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MemoryEntry[] m = new MemoryEntry[1023];
		
		/*
		//Addition zweier Zahlen
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); //ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		
		m[500] = MemoryEntry.parseMemoryEntry(100);
		m[502] = MemoryEntry.parseMemoryEntry(150);
		*/
		
		/*
		//(x + 4) * 4
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("1000000000000100".toCharArray()); //ADDD 4
		m[104] = new MemoryEntry("0000100000000000".toCharArray()); //SLA
		m[106] = new MemoryEntry("0000100000000000".toCharArray()); //SLA
		m[108] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		m[500] = MemoryEntry.parseMemoryEntry(-128);
		*/
		
		/*
		//x++; x++; x--; x/2
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0000000100000000".toCharArray()); //INC
		m[104] = new MemoryEntry("0000000100000000".toCharArray()); //INC
		m[106] = new MemoryEntry("0000010000000000".toCharArray()); //DEC
 		m[108] = new MemoryEntry("0000010100000000".toCharArray()); //SRA
 		m[110] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		m[500] = MemoryEntry.parseMemoryEntry(255);
		*/
		
		//x++; x++; x--; x/2
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[108] = new MemoryEntry("0000010100000000".toCharArray()); //SRA
 		m[110] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		m[500] = MemoryEntry.parseMemoryEntry(-128);
		
		/*	
		//SRL
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[108] = new MemoryEntry("0000100100000000".toCharArray()); //SRL
 		m[110] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(-1);
		*/
		/*
		//SLL
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[108] = new MemoryEntry("0000110000000000".toCharArray()); //SLL
 		m[110] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		m[500] = MemoryEntry.parseMemoryEntry(-256);
		*/
		
		/*
		//AND
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011000000000".toCharArray()); //AND 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		
		m[500] = MemoryEntry.parseMemoryEntry(256);
		m[502] = MemoryEntry.parseMemoryEntry(257);
		*/
		
		/*
		//OR
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); //LWDD 1, 502
		m[104] = new MemoryEntry("0000011100000000".toCharArray()); //OR 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
 		
		m[500] = MemoryEntry.parseMemoryEntry(256);
		m[502] = MemoryEntry.parseMemoryEntry(257);
		*/
		
		/*
		//NOT
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); //LWDD 0, 500
 		m[108] = new MemoryEntry("0000000000000000".toCharArray()); //NOT
 		m[110] = new MemoryEntry("0110000111111000".toCharArray()); //SWDD 0, 504
		m[500] = MemoryEntry.parseMemoryEntry(-1);
		*/
		
                new Test().openGUI();
		MiniPowerPC emu = new MiniPowerPC(m);
		emu.run();
	}
        
        private void openGUI(){
                gui = new MiniPowerPCGUI(this);
            
        }

    public void runSimulation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextStep() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
