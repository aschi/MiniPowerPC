package ch.hszt.MiniPowerPC;

import ch.hszt.MiniPowerPC.GUI.MiniPowerPCGUI;

public class Controller implements ControllerIfc {
	private MiniPowerPC emu;
	private MiniPowerPCGUI gui;

	public Controller(MemoryEntry[] memory) {
		emu = new MiniPowerPC(memory);
		gui = new MiniPowerPCGUI(this);
		updateGUI();
	}

	public MiniPowerPC getEmu() {
		return emu;
	}

	public Controller(MemoryEntry[] memory, int startPosition) {
		this(memory);
		emu.setInstructionCounter(startPosition);
		updateGUI();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MemoryEntry[] m = new MemoryEntry[1024];

		int i1 = 100;
		int i2 = 150;

		// Add example
		m[100] = new MemoryEntry("0100000111110100".toCharArray()); // LWDD 0,500
		m[102] = new MemoryEntry("0100010111110110".toCharArray()); // LWDD 1,502
		m[104] = new MemoryEntry("0000011110000000".toCharArray()); // ADD 1
		m[106] = new MemoryEntry("0110000111111000".toCharArray()); // SWDD 0,504
		m[108] = new MemoryEntry("0010000001111010".toCharArray()); // BD 122

		// jump to 100
		m[120] = new MemoryEntry("0010000001100100".toCharArray()); // BD 100

		m[500] = MemoryEntry.parseMemoryEntry(i1);
		m[502] = MemoryEntry.parseMemoryEntry(i2);
		
		Controller c = new Controller(m, 120);
		/*
		for (int i = 100; i < 1024; i++) {
			if (c.getEmu().getMemory()[i] != null) {
				System.out.println(i
						+ " - "
						+ String.valueOf(c.getEmu().getMemory()[i]
								.getBinaryString()));
			}
		}
		*/
		
	}

	public void updateGUI() {
		gui.setAkku(String.valueOf(emu.getAkku()));
		if (emu.getInstructionReg() != null) {
			gui.setBefReg(emu.getInstructionReg().toString(),
					emu.getInstructionReg().getBinaryString());
		}
		gui.setBefZ(String.valueOf(emu.getInstructionCounter()));
		gui.setReg1(String.valueOf(emu.getRegister()[1]));
		gui.setReg2(String.valueOf(emu.getRegister()[2]));
		gui.setReg3(String.valueOf(emu.getRegister()[3]));
		gui.setCarryBit(String.valueOf(emu.getCarryFlag()));
		gui.setCountBef(String.valueOf(emu.getStepCounter()));
		gui.setMemoryEntries(emu.getMemory());
	}

	@Override
	public void runSimulation() {
		emu.run();
		updateGUI();
	}

	@Override
	public void nextStep() {
		emu.nextStep();
		updateGUI();
	}

}
