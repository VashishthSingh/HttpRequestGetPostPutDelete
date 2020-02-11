package com.csbprog.CSBestProg2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientClassPut{
	public static void main(String []args) {
	    try {
	    	
		    Readings readings=new Readings();
		    
		    readings.setRamUsed(11.11f);
		    readings.setDiskUsed(22.22f);
		    readings.setCpuUtilization(33.33);
		    readings.setReadDateTime("2020-02-11 10:54:18");
		    
		    String jsonInputString=JsonUtility.convertToJSON(readings);
		    
		    URL url = new URL("http://localhost:8080/CSBestProg2/getResource1");
	    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    	con.setRequestMethod("PUT"); // Setting http Method
		    
		    con.setDoOutput(true);
	    	StringBuilder postData=new StringBuilder();
			postData.append(jsonInputString);
		    byte [] postDataBytes=postData.toString().getBytes("UTF-8");
	    	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		    con.getOutputStream().write(postDataBytes);
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		    String inputLine;
		    StringBuffer response=new StringBuffer();
		    while ((inputLine = in.readLine()) != null) {
		       response.append(inputLine);
		    }
		    System.out.println(response.toString());
		    in.close();
		    postData.setLength(0);
	    }
	    catch(Exception e) {
	    	System.out.println("Ex: "+e);
	    }
	}
}
