package com.superhero;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Superhero {
	public static void main(String[] args) {
		MysqlDataSource mysqlDS = new MysqlDataSource();

		mysqlDS.setURL("jdbc:mysql://localhost:3306/superheroes");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		try {
			Connection conn = mysqlDS.getConnection();
			System.out.println("Press Enter on the Console to Continue");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			sc.close();
			Statement myStmt = conn.createStatement();
			String query = "select * from superhero_table";
			ResultSet rs = myStmt.executeQuery(query);
			while( rs.next() ) {
				String name = rs.getString("name");
				String realFName = rs.getString("real_first_name");
				String realSurame = rs.getString("real_surname");
				String dob = rs.getString("dob");
				Float powers = rs.getFloat("powers");
				System.out.println(name + ", " + realFName + " " + realSurame + ", " + dob + ", " + powers);
			}
		} catch(CommunicationsException e ) {
            System.out.println("ERROR: Cannot communicate with superheroes database");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
