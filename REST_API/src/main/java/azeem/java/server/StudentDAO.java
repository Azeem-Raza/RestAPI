package azeem.java.server;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;


public class StudentDAO {
	private String jdbcURL = "jdbc:mariadb://mariadb.vamk.fi:3306/e2102961_Students";
	private String jdbcUsername = "DB/username";
	private String jdbcPassword = "DB/password";
	

	//Constructor
	public StudentDAO(String url, String userName, String password) {
		this.jdbcURL = url;
		this.jdbcUsername = userName;
		this.jdbcPassword = password;
	}
	public StudentDAO() {}
	
	private static final String SELECT_ALL_STUDENTS_QUERY = "SELECT * FROM Students";
	private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM Students WHERE Students_ID=?";
	private static final String INSERT_STUDENTS_QUERY = "Insert into Students VALUES (?, ?, ?)";
	private static final String DELETE_STUDENT_QUERY = "DELETE FROM Students WHERE Students_ID=?";

	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<students> selectAllStudents() {
        List<students> student = new ArrayList<students>();
        
        try(Connection conn = getConnection();
        	PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENTS_QUERY)) {
            ResultSet rs = ps.executeQuery();
        		
        	while (rs.next()) {
        		int id = rs.getInt(1);
        		String firstName = rs.getString(2);
        		String lastName = rs.getString(3);
        		
        		student.add(new students(id,firstName,lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
        
        
    }
	
	public students selectStudentByID(int id) {
		students student = null;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID);) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//int id = rs.getInt("id");
	            String firstName = rs.getString("firstName");
	            String lastName = rs.getString("lastName");
 
	            student = new students(id, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}
	public void insertStudent(students student) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_STUDENTS_QUERY);) {
			ps.setInt(1, student.getId());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean deleteStudent(int id) {
		boolean rowDeleted = false;
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT_QUERY);) {
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}
