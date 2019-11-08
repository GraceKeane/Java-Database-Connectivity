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

		String name = "Joker";
		String fName = "Jack";
		String sName = "Nicholson";
		String dob = "1949-03-21";
		double powers = 89.4;
		try {
			Connection conn = mysqlDS.getConnection();
			System.out.println("Press Enter on the Console to Continue");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			sc.close();
			Statement myStmt = conn.createStatement();
			String sql = "insert into superhero_table values(" +
					"'" + name + "', " +
					"'" + fName + "', " +
					"'" + sName + "', " +
					"'" + dob + "', " +
					"'" + powers + "')";
			myStmt.executeUpdate(sql);
			System.out.println(name + " successfully inserted into database");
		} catch (CommunicationsException e ) {
            System.out.println("ERROR: Cannot communicate with superheroes database");
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("ERROR: Superhero " + name + " already exists in database");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
