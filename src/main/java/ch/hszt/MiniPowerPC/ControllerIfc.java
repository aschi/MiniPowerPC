/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hszt.MiniPowerPC;

import java.io.File;

/**
 *
 * @author rest
 */
public interface ControllerIfc {
    public void runSimulation();
    public void nextStep();
    public void loadProgramm(File inProgramm)throws IllegalArgumentException;
    public void setMultiplicationOP(int inMemoryPos1, int inOP1, int inMemoryPos2, int inOP2);
}
