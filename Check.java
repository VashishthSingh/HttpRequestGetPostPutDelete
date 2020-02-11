package com.csbprog.CSBestProg2;

public class Check {
	public static void main(String []args) {
		Readings r=new Readings(); 
		r.setRamUsed(12.4f);
		r.setDiskUsed(12.9f);
		r.setCpuUtilization(23.4d);
		r.setReadDateTime("2020-02-07 18:04:30");
		String jsonString=JsonUtility.convertToJSON(r);
		Readings javaObject=JsonUtility.convertToJavaObject(jsonString);
		// Printing JsonString and JavaObject
		System.out.println(jsonString);
		System.out.println(javaObject);
	}
}
