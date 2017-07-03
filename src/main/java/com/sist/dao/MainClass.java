package com.sist.dao;

import java.sql.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("do");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			Connection conn=DriverManager.getConnection(url, "scott", "tiger");
			System.out.println(conn);
			String sql="SELECT * FROM address";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
			}
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
