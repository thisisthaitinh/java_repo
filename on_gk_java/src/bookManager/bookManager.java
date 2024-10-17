package bookManager;

import javax.swing.table.*;
import java.util.*;

public class bookManager {
	private List<book> b;
	
	public bookManager() {
		b = new ArrayList<>();
	}
	
	public List<book> getSach() {
		return b;
	}
	
	public void themSach(book s, DefaultTableModel table) {
		b.add(s);
		String[] bookData = {
				s.getMaSach(),
				s.getTuaSach(),
				String.valueOf(s.getNamXB()),
				String.valueOf(s.getSoTrang()),
				s.getISB_num(),
				s.getTacGia(),
				s.getNhaXB(),
				String.valueOf(s.getDonGia())
		};
		
		table.addRow(bookData);
	}
}
