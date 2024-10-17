package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class BookManager {
    private final String fileName = "dulieu.txt"; // Make sure the filename matches
    private final String[] columnNames = {"MaSach", "TuaSach", "TacGia", "NamXuatBan", "NhaXuatBan", "SoTrang", "DonGia", "ISBN"};

    public String[][] loadData() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Bỏ qua dòng tiêu đề
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.add(values);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return data.toArray(new String[0][0]);
    }

    public void saveData(DefaultTableModel tableModel) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(String.join(";", columnNames));
            bw.newLine();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bw.write(";");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}