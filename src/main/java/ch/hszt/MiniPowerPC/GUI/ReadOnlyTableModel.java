package ch.hszt.MiniPowerPC.GUI;

import javax.swing.table.DefaultTableModel;

public class ReadOnlyTableModel extends DefaultTableModel {
	public ReadOnlyTableModel(String[] columnNames, int i) {
		super(columnNames, i);
	}

	/**
	 * Overriden function that simply disallows all cell editing.
	 * 
	 * @param row
	 *            ignored
	 * @param col
	 *            ignored
	 * @return false
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
