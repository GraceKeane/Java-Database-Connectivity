package com.employees;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.event.RowSorterEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Employee {
	public static void main(String[] args) {
		int offset = 0;
		int limit = 11;
		boolean moreRows = true;
		
		MysqlDataSource mysqlDS = new MysqlDataSource();

		mysqlDS.setURL("jdbc:mysql://localhost:3306/employees");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		try {
			Connection conn = mysqlDS.getConnection();
			while (moreRows) {
				System.out.println("Press Enter on the Console to Continue");
				Scanner sc = new Scanner(System.in);
				sc.nextLine();
				Statement myStmt = conn.createStatement();
				String query = "select * from employees LIMIT " + limit + " OFFSET " + offset;
				ResultSet rs = myStmt.executeQuery(query);
				while( rs.next() ) {
					String name = rs.getString("emp_no");
					String realFName = rs.getString("first_name");
					System.out.println(name + ", " + realFName);
				}
				rs.last();
				int rowsReturned = rs.getRow();
				System.out.println("Rows ret = "+ rowsReturned);
				System.out.println("offset = "+ offset);
				if (rowsReturned < limit) {
					moreRows = false;
				} else {
					offset += rowsReturned;
				}
			}
		} catch(CommunicationsException e ) {
            System.out.println("ERROR: Cannot communicate with superheroes database");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
