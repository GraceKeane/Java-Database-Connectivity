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
			System.out.println("Enter a Superhero to increase powers by 1: ");
			Scanner sc = new Scanner(System.in);
			name = sc.nextLine();
			sc.close();
			Statement myStmt = conn.createStatement();
			String sql = "Update superhero_table set powers = powers + 1 where name like '" + name + "'";
			if (myStmt.executeUpdate(sql) > 0) {
				sql = "Select powers from superhero_table where name like '" + name + "'";
				ResultSet rs = myStmt.executeQuery(sql);
				if( rs.next() ) {
					System.out.println(name + " powers now " + rs.getBigDecimal("powers"));
				}
			} else {
				System.out.println(name + " does not exist in database ");
			}
		} catch (CommunicationsException e ) {
            System.out.println("ERROR: Cannot communicate with superheroes database");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
