package ba.unsa.etf.si.app.iTravel.BLL;

import javax.swing.table.DefaultTableModel;

public class NasModel extends DefaultTableModel {

	public NasModel(Object[][] o, String[] s)
	{
		super(o, s);
	}
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

}

