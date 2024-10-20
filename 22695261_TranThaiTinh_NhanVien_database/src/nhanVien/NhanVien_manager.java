package nhanVien;
import SQL.SQL_connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class NhanVien_manager {
	private Collection<NhanVien> nv;
	
	public NhanVien_manager() {
		nv = new ArrayList<>();
	}
	
	public void addNV(NhanVien n) {
		nv.add(n);
		saveEmployee(n);
	}
	
	public Collection<NhanVien> getNV() {
		return nv;
	}
	
	public void deleteEmployeeFromDatabase(String employeeId) {
	    String sql = "DELETE FROM employees WHERE id = ?";

	    try (Connection conn = SQL_connection.connection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, employeeId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi xóa nhân viên: " + e.getMessage());
	    }
	}
	
	private void saveEmployee(NhanVien n) {
		String sql_query = "INSERT INTO Employee (employee_id, employee_surname, employee_name, employee_age, employee_gender, employee_salary) VALUES (?, ?, ?, ?, ?, ?)";
		
		try (Connection con = SQL_connection.connection();
				PreparedStatement pstmt = con.prepareStatement(sql_query)) {
			pstmt.setString(1, n.getNv_id());
			pstmt.setString(2, n.getNv_ho());
			pstmt.setString(3, n.getNv_ten());
			pstmt.setInt(4, n.getNv_tuoi());
			pstmt.setString(5, n.getNv_gt());
			pstmt.setDouble(6, n.getNv_luong());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Loi khi luu database: " + e.getMessage());
		}
	}
	
	public void clearEmployees() {
        nv.clear(); // Clear the collection of employees
    }
}
