/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hszt.MiniPowerPC.GUI;

import ch.hszt.MiniPowerPC.MemoryEntry;

/**
 * The GUI implements this Interface, which allows the Controller to Set and
 * Get Text and status of a specific GUI component
 * @author rest
 */
public interface MiniPowerPCUIIfc {
    //TODO Initializing the Memory Tabel needs a separate methode
    
    /**
     * Sets the Text in the Textfield of the Akkumulator
     */
    public void setAkku(String inMemoryEntry);
     /**
     * Sets the Text in the Textfield of the Register1
     */
    public void setReg1(String inMemoryEntry);
    /**
     * Sets the Text in the Textfield of the Register2
     */
    public void setReg2(String inMemoryEntry);
    /**
     * Sets the Text in the Textfield of the Register3
     */
    public void setReg3(String inMemoryEntry);
    /**
     * Sets the Text in the Textfield of the Befehlszähler
     */
    public void setBefZ(String inMemoryEntry);
    
     /**
     * Sets the String in the Mnemonic-Column and the Value-Column in the Befehlsregister
     */
    public void setBefReg(String inMnemonic, String inValue);
    
     /**
     * Sets the String in the Mnemonic-Column and the Value-Column in the Befehlsregister
     */
    public void setMemoryEntries(MemoryEntry[] inMemory);
    
    
    /**
     * Sets the String in the Textfield of the "Anzahl durchgeführter Befehle"
     */
    public void setCountBef(String inMemoryEntry);
    
    /**
     * Sets the Text in the Textfield of the Befehlszähler
     */
    public void setCarryBit(String inMemoryEntry);

     /**
     * Returns True, if the Binary Exposition is selected. If Decimal Exposition
     * is selected the Method returns false.
     */
    public boolean isBinaryExpositionSelected();
    
    /**
     * Eventually there might be needed a Method to notify the GUI that the Simulation
     * is finished
     */
    //public void onSimulationFinished();
}
