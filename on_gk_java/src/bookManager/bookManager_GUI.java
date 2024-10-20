package bookManager;

import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;
import java.time.*;
import javax.swing.table.DefaultTableModel;

public class bookManager_GUI extends JFrame {

	// labels and text fields
	private JLabel maSach_label, tuaSach_label, namXB_label, soTrang_label, ISB_num_label, tacGia_label, nhaXB_label, donGia_label, timMaSach_label;
	private JTextField maSach_tfield, tuaSach_tfield, namXB_tfield, soTrang_tfield, ISB_num_tfield, tacGia_tfield, nhaXB_tfield, donGia_tfield;
	private JButton addB, clearB, deleteB, editB, saveB;
	private JComboBox<String> maSach_combo;
	private DefaultTableModel tableModel;
	private JTable table;
	
	private bookManager bm;
	
	private List<book> b;
	
	// constructor
	public bookManager_GUI() {
		// set layout
		super("Quan ly sach");
		bm = new bookManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		
		// north layout
		JPanel northLayout = new JPanel(new GridLayout(1, 2, 10, 5));
		
		Border border = BorderFactory.createTitledBorder("Records editor");
		northLayout.setBorder(border);
		
		// first column
		JPanel firstCol = new JPanel(new GridLayout(5, 2, 10, 5));
		
		maSach_label = new JLabel("Ma sach: ");
		maSach_tfield = new JTextField(30);
		
		tuaSach_label = new JLabel("Tua sach: ");
		tuaSach_tfield = new JTextField(30);
		
		namXB_label = new JLabel("Nam xuat ban: ");
		namXB_tfield = new JTextField(30);
		
		soTrang_label = new JLabel("So trang: ");
		soTrang_tfield = new JTextField(30);
		
		ISB_num_label = new JLabel("Internation Standard Book Number: ");
		ISB_num_tfield = new JTextField(30);
		
		firstCol.add(maSach_label);
		firstCol.add(maSach_tfield);
		
		firstCol.add(tuaSach_label);
		firstCol.add(tuaSach_tfield);
		
		firstCol.add(namXB_label);
		firstCol.add(namXB_tfield);
		
		firstCol.add(soTrang_label);
		firstCol.add(soTrang_tfield);
		
		firstCol.add(ISB_num_label);
		firstCol.add(ISB_num_tfield);
				
		northLayout.add(firstCol);
		
		// second column
		JPanel secondCol = new JPanel(new GridLayout(5, 2, 10, 5));
		
		tacGia_label = new JLabel("Tac gia: ");
		tacGia_tfield = new JTextField(30);
		
		nhaXB_label = new JLabel("Nha xuat ban: ");
		nhaXB_tfield = new JTextField(30);
		
		donGia_label = new JLabel("Don gia: ");
		donGia_tfield = new JTextField(30);
		
		secondCol.add(tacGia_label);
		secondCol.add(tacGia_tfield);
		
		secondCol.add(nhaXB_label);
		secondCol.add(nhaXB_tfield);
		
		secondCol.add(donGia_label);
		secondCol.add(donGia_tfield);
		
		northLayout.add(secondCol);
		
		add(northLayout, BorderLayout.NORTH);
		
		// center layout
		JPanel centerLayout = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		addB = new JButton("Them");
		clearB = new JButton("Xoa rong");
		deleteB = new JButton("Xoa");
		editB = new JButton("Sua");
		saveB = new JButton("Luu");
		timMaSach_label = new JLabel("Tim theo ma sach: ");
		maSach_combo = new JComboBox<>();
		
		centerLayout.add(addB);
		addB.addActionListener(e -> themSach());
		
		centerLayout.add(clearB);
		clearB.addActionListener(e -> xoaRong());
		
		centerLayout.add(deleteB);
		centerLayout.add(editB);
		centerLayout.add(saveB);
		centerLayout.add(timMaSach_label);
		centerLayout.add(maSach_combo);
		
		add(centerLayout, BorderLayout.CENTER);
		
		// south layout
		String[] titles = {"Ma sach", "Tua sach", "Tac gia", "Nam xuat ban", "Nha xuat ban", "So trang", "Don gia", "ISBN"};
		tableModel = new DefaultTableModel(titles, 0);
		
		table = new JTable(tableModel);
		
		table.setBounds(30, 40, 200, 50);
		JScrollPane sp = new JScrollPane(table);
		
		Border b = BorderFactory.createTitledBorder("Danh sach cac cuon sach");
		sp.setBorder(b);
		
		add(sp, BorderLayout.SOUTH);
		
		// center the program
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	
	// methods

	// check if inputs are valid
	private boolean checkValidate(String ms, String ts, String namxb, String st, String isb, String tg, String nxb, String dg) {
		// regexes
		String ms_regex = "^[A-Z]\\d{3}$";
		String ts_regex = "^[\\w\\s\\-()]+$";
		String tg_regex = "^[A-Za-z\\s']+$";
		String isb_regex = "^\\d+(-\\d+){3}$";
		
		Pattern p_ms = Pattern.compile(ms_regex);
		Pattern p_ts = Pattern.compile(ts_regex);
		Pattern p_tg = Pattern.compile(tg_regex);
		Pattern p_isb = Pattern.compile(isb_regex);
		
		if (!checkNull(ms, ts, tg, nxb, isb)) {
			JOptionPane.showMessageDialog(this, "Thong tin sach k dc de trong!");
		} else {
			// check ma sach
			if (!p_ms.matcher(ms).matches()) {
				JOptionPane.showMessageDialog(this, "Ma sach k hop le!");
				return false;
				// check ma sach trung hay k
			} else if (checkTrungMS(ms)) {
				JOptionPane.showMessageDialog(this, "Trung ma sach, vui long thu lai!");
			}
			
			// check tua sach
			if (!p_ts.matcher(ts).matches()) {
				JOptionPane.showMessageDialog(this, "Tua sach k hop le!");
				return false;
			}
			
			// check tac gia
			if (!p_tg.matcher(tg).matches()) {
				JOptionPane.showMessageDialog(this, "Ten tac gia k hop le!");
				return false;
			}
			
			// check nam xuat ban
			if (!checkNamXB(namxb)) {
				JOptionPane.showMessageDialog(this, "Nam xuat ban phai nam tu nam 1900 den nam " + Year.now().getValue());
				return false;
			}
			
			// check isb num
			if (!p_isb.matcher(isb).matches()) {
				JOptionPane.showMessageDialog(this, "Ma ISB k hop le!");
				return false;
			}
		}
		return true;
	}
	
	// check if inputs are null
	private boolean checkNull(String ms, String ts, String tg, String nxb, String isb) {
		if (ms.isEmpty() || ts.isEmpty() || tg.isEmpty() || nxb.isEmpty() || isb.isEmpty()) {
			return false;
		}
		return true;
	}
	
	// check nam xuat ban
	private boolean checkNamXB(String namxb) {
		int nxb = Integer.parseInt(namxb);
		int namHienTai = Year.now().getValue();
		return (1900 <= nxb && nxb <= namHienTai) ? true : false;
	}
	
	// check ma sach co trung hay k
	private boolean checkTrungMS(String ms) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if (tableModel.getValueAt(i, 0).equals(ms)) {
				return true;
			}
		}
		return false;
	}
	
	// them sach
	private void themSach() {
		String ms = maSach_tfield.getText();
		String ts = tuaSach_tfield.getText();

		String namxb = maSach_tfield.getText();
		String st = tuaSach_tfield.getText();

		String isb = maSach_tfield.getText();
		String tg = tuaSach_tfield.getText();

		String nhaxb = maSach_tfield.getText();
		String dg = tuaSach_tfield.getText();
		
		if (checkValidate(ms, ts, namxb, st, isb, tg, nhaxb, dg)) {
			book bo = new book(ms, ts, Integer.parseInt(namxb), Integer.parseInt(st), isb, tg, nhaxb, Double.parseDouble(dg));
			bm.themSach(bo, tableModel);
			maSach_combo.addItem(ms);
		}
	}
	
	// xoa toan bo inputs
	private void xoaRong() {
		maSach_tfield.setText("");
		tuaSach_tfield.setText("");

		namXB_tfield.setText("");
		soTrang_tfield.setText("");

		ISB_num_tfield.setText("");
		tacGia_tfield.setText("");

		nhaXB_tfield.setText("");
		donGia_tfield.setText("");
	}
	
	
}
