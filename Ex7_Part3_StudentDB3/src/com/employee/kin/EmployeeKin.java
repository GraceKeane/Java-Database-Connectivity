package com.employee.kin;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class EmployeeKin {
	public static void main(String[] args) {
		MysqlDataSource mysqlDS = new MysqlDataSource();

		mysqlDS.setURL("jdbc:mysql://localhost:3306/studentdb3");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		try {
			Connection conn = mysqlDS.getConnection();
			Statement myStmt = conn.createStatement();
			String query = "select s.student_name as Student, c.course_name as Course, " +
							"coll.college_name as College, co.county_name as County, " +
							" co.main_town as 'Main Town' " +
							"from student_table s " +
							"Inner Join course_table c " +
							"on s.course_id = c.course_id " +
							"Inner Join college_table coll " +
							"on c.college_id = coll.college_id " +
							"Inner Join county_table co " +
							"on coll.county = co.county_name ";
			ResultSet rs = myStmt.executeQuery(query);
			while( rs.next() ) {
				String student = rs.getString("Student");
				String course = rs.getString("Course");
				String college = rs.getString("College");
				String county = rs.getString("County");
				String mainTown = rs.getString("Main Town");
				System.out.println(student + ", " + course + ", " + college + ", " + county + ", " + mainTown);
			}
		} catch( SQLException se ) {
            System.out.println(se.getMessage());
        }
	}
}
