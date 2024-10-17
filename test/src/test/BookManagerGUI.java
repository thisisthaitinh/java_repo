package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagerGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton saveButton;
    private String[] columnNames = {"MaSach", "TuaSach", "TacGia", "NamXuatBan", "NhaXuatBan", "SoTrang", "DonGia", "ISBN"};
    private BookManager bookManager;

    public BookManagerGUI() {
        bookManager = new BookManager();
        setTitle("Quản Lý Sách");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        loadData();

        saveButton = new JButton("Lưu Dữ Liệu");
        saveButton.addActionListener(new SaveButtonListener());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void loadData() {
        String[][] data = bookManager.loadData();
        for (String[] row : data) {
            if (row != null) {
                tableModel.addRow(row);
            }
        }
    }

    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            bookManager.saveData(tableModel);
            JOptionPane.showMessageDialog(BookManagerGUI.this, "Dữ liệu đã được lưu!");
        }
    }
}
