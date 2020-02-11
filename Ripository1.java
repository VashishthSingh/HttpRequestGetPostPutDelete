package com.csbprog.CSBestProg2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class Ripository1 {
	public static Connection getConnectionObject() {
		Connection con=null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con=(Connection) DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/report?useSSL=false","root","password"); 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return(con);
	}
	public static void getDataFromClient(String data) {
		Readings readings=JsonUtility.convertToJavaObject(data);
		try{  
			Connection con=Ripository1.getConnectionObject();  
			PreparedStatement stmt=(PreparedStatement) con.prepareStatement("insert into MyTable(ramUti,diskUti,cpuUti,readTime) values(?,?,?,?)");  
			stmt.setFloat(1,readings.getRamUsed());
			stmt.setFloat(2,readings.getDiskUsed());
			stmt.setDouble(3,readings.getCpuUtilization());
			stmt.setString(4,readings.getReadDateTime());
			stmt.executeUpdate();  
		}catch(Exception e){ 
			System.out.println(e);
		}  
	}
	public static Map<Integer,Readings> getAllDataFromDataBase() {
		Map<Integer,Readings> map=new LinkedHashMap<Integer,Readings>();
		int keys=0;
		try{   
			Connection con=Ripository1.getConnectionObject();    
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from MyTable");
			Readings object=null;
			while(rs.next()) {
				object=new Readings();
				object.setRamUsed(rs.getFloat(1));
				object.setDiskUsed(rs.getFloat(2));
				object.setCpuUtilization(rs.getDouble(3));
				object.setReadDateTime(rs.getString(4));
				map.put(keys,object);
				keys++;
			}
		}catch(Exception e){ 
			System.out.println(e);
		}  
		return(map);
	}
	public static void insertIntoDatabase(Readings readings) {
		try{   
			Connection con=Ripository1.getConnectionObject();    
			PreparedStatement stmt=con.prepareStatement("insert into MyTable2(ramUti,diskUti,cpuUti,readTime) values(?,?,?,?)");
			stmt.setFloat(1,readings.getRamUsed());
			stmt.setFloat(2,readings.getDiskUsed());
			stmt.setDouble(3,readings.getCpuUtilization());
			stmt.setString(4,readings.getReadDateTime());
			stmt.executeUpdate(); 
			con.close();
			stmt.close();
		}catch(Exception e){ 
			System.out.println(e);
		} 
	}
	public static String updateRecord(String readings) {
		Readings object=JsonUtility.convertToJavaObject(readings);
		//System.out.println(object);
		try {
			Connection con=Ripository1.getConnectionObject();
			PreparedStatement stmt=con.prepareStatement("update MyTable set ramUti=?,diskUti=?,cpuUti=? where readTime=?");
			stmt.setFloat(1, object.getRamUsed());
			stmt.setFloat(2, object.getDiskUsed());
			stmt.setDouble(3, object.getCpuUtilization());
			stmt.setString(4, object.getReadDateTime());
			stmt.executeUpdate(); 
			con.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return("Success..");
	}
	public static String deleteRecord(String readings) {
		Readings object=JsonUtility.convertToJavaObject(readings);
		try {
			Connection con=Ripository1.getConnectionObject();
			PreparedStatement stmt=con.prepareStatement("delete from MyTable where readTime=?");
			stmt.setString(1, object.getReadDateTime());
			stmt.executeUpdate(); 
			con.close();
			stmt.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return("Success..");
	}
}
