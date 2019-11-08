package com.superhero;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Superhero {
	public static void main(String[] args) {
		MysqlDataSource mysqlDS = new MysqlDataSource();

		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		String name = "";
		try {
			Connection conn = mysqlDS.getConnection();
			System.out.println("Enter a Superhero to delete: ");
			Scanner sc = new Scanner(System.in);
			name = sc.nextLine();
			sc.close();
			Statement myStmt = conn.createStatement();
			String sql = "Delete from superhero_table where name like '" + name + "'";
			if (myStmt.executeUpdate(sql) > 0) {
				System.out.println(name + " successfully deleted from database ");
			} else {
				System.out.println(name + " does not exist in database ");
			}
			conn.close();
			myStmt.close();
		} catch (CommunicationsException e ) {
            System.out.println("ERROR: Cannot communicate with superheroes database");
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("ERROR: Superhero " + name + " must be first deleted from any dependent tables");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
