package NhanVien;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeInfo extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField txtMaNV, txtTenNV, txtTuoi, txtLuong;
    private JComboBox<String> comboPhai;
    private JButton btnThem, btnSua, btnXoa, btnLuu;

    public EmployeeInfo() {
        setTitle("THÔNG TIN NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create top panel for employee form
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add fields for employee details
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("Mã nhân viên:"), gbc);
        gbc.gridx = 1;
        txtMaNV = new JTextField(10);
        panelForm.add(txtMaNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(new JLabel("Tên nhân viên:"), gbc);
        gbc.gridx = 1;
        txtTenNV = new JTextField(10);
        panelForm.add(txtTenNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(new JLabel("Tuổi:"), gbc);
        gbc.gridx = 1;
        txtTuoi = new JTextField(10);
        panelForm.add(txtTuoi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(new JLabel("Phái:"), gbc);
        gbc.gridx = 1;
        comboPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        panelForm.add(comboPhai, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForm.add(new JLabel("Tiền lương:"), gbc);
        gbc.gridx = 1;
        txtLuong = new JTextField(10);
        panelForm.add(txtLuong, gbc);

        add(panelForm, BorderLayout.NORTH);

        // Create the table
        String[] columnNames = {"Mã NV", "Tên NV", "Phái", "Tuổi", "Lương"};
        Object[][] data = {
            {"1111", "Hoàng", "Nam", "25", "4,500"},
            {"2222", "Lê", "Nữ", "23", "5,000"},
            {"3333", "Hoàng", "Nam", "26", "5,500"},
            {"4444", "Trần", "Nữ", "27", "5,000"}
        };
        tableModel = new DefaultTableModel(data, columnNames) {
            // Make the "Mã NV" column uneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Only allow editing other columns
            }
        };
        employeeTable = new JTable(tableModel);
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        // Create bottom panel for action buttons
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnThem = new JButton("Thêm");
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        panelButtons.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
        panelButtons.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
        panelButtons.add(btnXoa);

        btnLuu = new JButton("Lưu");
        panelButtons.add(btnLuu);

        add(panelButtons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    // Add employee to table
    private void addEmployee() {
        String maNV = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        String phai = comboPhai.getSelectedItem().toString();
        String tuoi = txtTuoi.getText();
        String luong = txtLuong.getText();

        tableModel.addRow(new Object[]{maNV, tenNV, phai, tuoi, luong});

        clearFields();
    }

    // Update selected employee information
    private void updateEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.setValueAt(txtTenNV.getText(), selectedRow, 1);
            tableModel.setValueAt(comboPhai.getSelectedItem().toString(), selectedRow, 2);
            tableModel.setValueAt(txtTuoi.getText(), selectedRow, 3);
            tableModel.setValueAt(txtLuong.getText(), selectedRow, 4);
        }
    }

    // Delete selected employee from table
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        }
    }

    // Clear form fields
    private void clearFields() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtTuoi.setText("");
        txtLuong.setText("");
        comboPhai.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmployeeInfo();
            }
        });
    }
}
