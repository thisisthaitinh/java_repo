package Bai1;

import javax.swing.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class ql_sach_gui extends JFrame {
	
	// VARIAB
	private JLabel maSach, tuaSach, namXB, soTrang, isb_num, tacGia, nhaXB, donGia;
	private JTextField txtmaSach, txttuaSach, txtnamXB, txtsoTrang, txtisb_num, txttacGia, txtnhaXB, txtdonGia;
	private DefaultTableModel tableModel;
	private ql_sach QLSach;
	private JComboBox<String> findBookIDs;

	// CONSTRUCTOR	
	public ql_sach_gui() {
		super("Quan ly sach");
		QLSach = new ql_sach();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 500);
		
		// NORTH LAYOUT		
		JPanel northLayout = new JPanel(new GridLayout(1, 2, 10, 5));
		
		// BORDER FACTORY
		Border border = BorderFactory.createTitledBorder("Records Editor");
		northLayout.setBorder(border);
		
		// FIRST COLUMN LAYOUT		
		JPanel first = new JPanel(new GridLayout(5, 2, 5, 10));
		
		maSach = new JLabel("Ma sach");
		txtmaSach = new JTextField(20);
		
		first.add(maSach);
		first.add(txtmaSach);
				
		tuaSach = new JLabel("Tua sach");
		txttuaSach = new JTextField(20);
		
		first.add(tuaSach);
		first.add(txttuaSach);
				
		namXB = new JLabel("Nam xuat ban");
		txtnamXB = new JTextField(20);
		
		first.add(namXB);
		first.add(txtnamXB);
				
		soTrang = new JLabel("So trang");
		txtsoTrang = new JTextField(20);
		
		first.add(soTrang);
		first.add(txtsoTrang);
				
		isb_num = new JLabel("International Standard Book Number: ");
		txtisb_num = new JTextField(20);
		
		first.add(isb_num);
		first.add(txtisb_num);
				
		// SECOND COLUMN LAYOUT
		JPanel second = new JPanel(new GridLayout(5, 2, 5, 10));
		
		tacGia = new JLabel("Tac gia: ");
		txttacGia = new JTextField(20);
		
		second.add(tacGia);
		second.add(txttacGia);
				
		nhaXB = new JLabel("Nha xuat ban: ");
		txtnhaXB = new JTextField(20);
		
		second.add(nhaXB);
		second.add(txtnhaXB);
				
		donGia = new JLabel("Don gia: ");
		txtdonGia = new JTextField(20);
		
		second.add(donGia);
		second.add(txtdonGia);
				
		northLayout.add(first);
		northLayout.add(second);
		
		add(northLayout, BorderLayout.NORTH);
	
		// BUTTONS PANEL
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JButton add = new JButton("Them");
		JButton clear = new JButton("Xoa rong");
		JButton delete = new JButton("Xoa");
		JButton edit = new JButton("Sua");
		JButton save = new JButton("Luu");
		
		// SEARCH
		JPanel searchPanel = new JPanel();
		
		JLabel searchLabel = new JLabel("Tim theo ma sach");
		findBookIDs = new JComboBox<>();
		findBookIDs.addActionListener(new findListener());
		
		searchPanel.add(searchLabel);
		searchPanel.add(findBookIDs);
		
		buttonPanel.add(add);
		buttonPanel.add(clear);
		buttonPanel.add(delete);
		buttonPanel.add(edit);
		buttonPanel.add(save);
		
		save.addActionListener(new saveListener());
		
		buttonPanel.add(searchPanel);
				
		add(buttonPanel, BorderLayout.CENTER);
		
		// TABLE
		String[] cols = {"MaSach", "TuaSach", "TacGia", "NamXuatBan", "NhaXuatBan", "SoTrang", "DonGia", "ISBN"};
		tableModel = new DefaultTableModel(cols, 0);
		
		JTable table = new JTable(tableModel);
		
		table.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(table);
		
		
		add(sp, BorderLayout.SOUTH);
		
		loadData();
		pack();
		setLocationRelativeTo(null);
	}
	
	// METHODS
    private void loadData() {
        String[][] data = QLSach.LoadData();
        Vector<String> bookIDs = new Vector<>();
        for (String[] row : data) {
            if (row != null) {
                tableModel.addRow(row);
                bookIDs.add(row[0]);
                
            }
        }
        findBookIDs.setModel(new DefaultComboBoxModel<>(bookIDs));
    }

    private class saveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            QLSach.saveData(tableModel);
            JOptionPane.showMessageDialog(ql_sach_gui.this, "Dữ liệu đã được lưu!");
        }
    }
    
    private class findListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selected = (String) findBookIDs.getSelectedItem();
			filterTableByBookID(selected);
		}
    }
    
    private void filterTableByBookID(String bookID) {
    	tableModel.setRowCount(0);
    	String[][] data = QLSach.LoadData();
    	for (String[] row : data) {
    		if (row[0].equals(bookID)) {
    			tableModel.addRow(row);
    			break;
    		}
    	}
    }
}
