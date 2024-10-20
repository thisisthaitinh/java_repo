package nhanVien;

import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class NhanVien_GUI extends JFrame {
    private JTable nhanvien_table;
    private DefaultTableModel tableModel;
    private JLabel maNV, hoNV, tenNV, tuoiNV, luongNV, timNV;
    private JTextField txtMaNV, txtHoNV, txtTenNV, txtTuoi, txtLuong, txtTimNV;
    private JComboBox<String> gioitinhNV;
    private JButton btnThem, btnSua, btnXoa, btnLuu, btnTim;
    private NhanVien_manager nv_manage;
    private NhanVien nv;
    private Set<String> xoaNhanVien;

    
//    CONSTRUCTOR
    public NhanVien_GUI() {
    	super("^-^");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(1000, 500);
//    	MAIN LAYOUT
    	nv_manage = new NhanVien_manager();
    	xoaNhanVien = new HashSet<>();
    	
    	JPanel mainPanel = new JPanel(new BorderLayout());
//    	HEADER
    	JLabel title = new JLabel("THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
    	title.setFont(new Font("Arial", Font.BOLD, 25));
    	title.setForeground(Color.BLUE);
    	this.add(title, BorderLayout.NORTH);
    	
    	// KET HOP FORM VA TABLE
    	JPanel combine = new JPanel();
    	combine.setLayout(new BoxLayout(combine, BoxLayout.Y_AXIS));
    	
    	// PANEL FORM
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // MA NHAN VIEN
        gbc.gridx = 0;
        gbc.gridy = 0;
        maNV = new JLabel("Mã nhân viên: ");
        panelForm.add(maNV, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtMaNV = new JTextField(50);
        panelForm.add(txtMaNV, gbc);
        
        gbc.gridwidth = 1; // reset
        
        // TEN NHAN VIEN
        gbc.gridx = 0;
        gbc.gridy = 1;
        hoNV = new JLabel("Họ: ");
        panelForm.add(hoNV, gbc);
        gbc.gridx = 1;
        txtHoNV = new JTextField(20);
        panelForm.add(txtHoNV, gbc);
        
        gbc.gridx = 2;
        tenNV = new JLabel("Tên nhân viên: ");
        panelForm.add(tenNV, gbc);
        gbc.gridx = 3;
        txtTenNV = new JTextField(20);
        panelForm.add(txtTenNV, gbc);
        
        // TUOI NHAN VIEN
        gbc.gridx = 0;
        gbc.gridy = 2;
        tuoiNV = new JLabel("Tuổi: ");
        panelForm.add(tuoiNV, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtTuoi = new JTextField(50);
        panelForm.add(txtTuoi, gbc);
        
        gbc.gridwidth = 1; // reset 
        
        // GIOI TINH NHAN VIEN
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(new JLabel("Phái:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gioitinhNV = new JComboBox<>(new String[]{"Nam", "Nữ"});
        panelForm.add(gioitinhNV, gbc);
        gbc.gridwidth = 1;

        // TIEN LUONG
        gbc.gridx = 0;
        gbc.gridy = 4;
        luongNV = new JLabel("Tiền lương:");
        panelForm.add(luongNV, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtLuong = new JTextField(50);
        panelForm.add(txtLuong, gbc);
        
        
        combine.add(panelForm);
        
        // TABLE        
        String[] columns = {"Mã nhân viên", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; 
            }
        };
        nhanvien_table = new JTable(tableModel);
        
        // EDIT GENDER COLUMN
        TableColumn genderColumn = nhanvien_table.getColumnModel().getColumn(3); 
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Nam", "Nữ"});
        genderColumn.setCellEditor(new DefaultCellEditor(genderComboBox));
        
        // COMBINE
        combine.add(new JScrollPane(nhanvien_table));
        
        // ADD COMBINE TABLE TO THE CENTRE OF TABLE
    	this.add(combine, BorderLayout.CENTER);
    	
    	// SOUTH
    	JPanel combine2 = new JPanel();
    	combine2.setLayout(new BoxLayout(combine2, BoxLayout.X_AXIS));
    	
    	// TIM NHAN VIEN THEO MA NV
    	JPanel timNV_panel = new JPanel();
    	timNV = new JLabel("Nhập mã số cần tìm: ");
    	timNV_panel.add(timNV);
    	txtTimNV = new JTextField(20);
    	timNV_panel.add(txtTimNV);
    	btnTim = new JButton("Tim");
    	timNV_panel.add(btnTim);
    	
    	// TIM NHAN VIEN    	
    	btnTim = new JButton("Tìm");
    	btnTim.addActionListener(e -> findEmployee());
    	combine2.add(timNV_panel);
    	
        // PANEL BUTTONS FOR ACTIONS
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnThem = new JButton("Thêm");
        btnThem.addActionListener(e -> addEmployee());
        panelButtons.add(btnThem);

        btnSua = new JButton("Xóa trắng");
        btnSua.addActionListener(e -> clearFields());
        panelButtons.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(e -> deleteEmployee());
        panelButtons.add(btnXoa);

        btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(e -> saveDatabase());
        panelButtons.add(btnLuu);

        combine2.add(panelButtons);
        this.add(combine2, BorderLayout.SOUTH);
    	
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	
    }
    // THEM NHAN VIEN VAO BANG
    private void addEmployee() {
    	try {
    		String maNV = txtMaNV.getText();
    		String ho = txtHoNV.getText();
    		String tenNV = txtTenNV.getText();
    		String phai = gioitinhNV.getSelectedItem().toString();
    		int tuoi = Integer.parseInt(txtTuoi.getText());
    		double luong = Double.parseDouble(txtLuong.getText());
    		
    		nv = new NhanVien(maNV, ho, tenNV, tuoi, phai, luong);
    		
    		nv_manage.addNV(nv);
    		tableModel.addRow(new Object[]{nv.getNv_id(), nv.getNv_ho(), nv.getNv_ten(), nv.getNv_tuoi(), nv.getNv_gt(), nv.getNv_luong()});
    		
    		JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
    		clearFields();
    		
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên hợp lệ.");
    	}
    }

    // XOA NHAN VIEN DUOC CHON
    private void deleteEmployee() {
        int selectedRow = nhanvien_table.getSelectedRow();
        if (selectedRow >= 0) {
        	int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa không?", "Xóa thông tin", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        	
        	if (confirm == JOptionPane.YES_OPTION) {
        		String id = (String) tableModel.getValueAt(selectedRow, 0);
        		xoaNhanVien.add(id);
        		tableModel.removeRow(selectedRow);
        		JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công.");
        	}
        } else {
        	JOptionPane.showMessageDialog(this, "Không có nhân viên nào được chọn để xóa.");
        }
    }
    
    // LUU DATABASE
    private void saveDatabase() {
    	nv_manage.clearEmployees();
    	
    	for (int i = 0; i < tableModel.getRowCount(); i++) {
    		String id = (String) tableModel.getValueAt(i, 0);
    		String ho = (String) tableModel.getValueAt(i, 1);
    		String ten = (String) tableModel.getValueAt(i, 2);
    		int tuoi = (int) tableModel.getValueAt(i, 3);
    		String gt = (String) tableModel.getValueAt(i, 4);
    		double luong = (double) tableModel.getValueAt(i, 5);
    		
    		NhanVien nv = new NhanVien(id, ho, ten, tuoi, gt, luong);
    		nv_manage.addNV(nv);
    	}
    	
    	for (String maNV : xoaNhanVien) {
    		nv_manage.deleteEmployeeFromDatabase(maNV);
    	}
    	
    	xoaNhanVien.clear();
    	
    	JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được lưu vào database.");
    }

    // TIM NHAN VIEN THEO MA NHAN VIEN
    private void findEmployee() {
    	try {
    		String maNV = txtMaNV.getText();
    		boolean found = false;
    		tableModel.setRowCount(0);
    		
    		for (NhanVien nv : nv_manage.getNV()) {
    			
    			if (nv.getNv_id() == maNV) {
    				tableModel.addRow(new Object[] {nv.getNv_id(), nv.getNv_ho(), nv.getNv_ten(), nv.getNv_tuoi(), nv.getNv_gt(), nv.getNv_luong()});
    				found = true;
    			}
    		}
    		
    		if (!found) {
    			JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với ID: " + maNV);
    		}    		
    	} catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên hợp lệ.");
    	}
    }
    
    // XOA TOAN BO DU LIEU TRONG BANG
    private void clearFields() {
        txtMaNV.setText("");
        txtHoNV.setText("");
        txtTenNV.setText("");
        txtTuoi.setText("");
        txtLuong.setText("");
        gioitinhNV.setSelectedIndex(0);
    }
    
}
