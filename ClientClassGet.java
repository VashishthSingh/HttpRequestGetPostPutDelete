package com.csbprog.CSBestProg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ClientClassGet {
  public static void main(String args[]) throws IOException, JSONException, InterruptedException{
    URL url = new URL("http://localhost:8080/CSBestProg2/getResource1/getdata");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response=new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
    	response.append(inputLine);
    }
    System.out.println(response);
    in.close();
    JSONObject myresponse=new JSONObject(response.toString());
    int len=myresponse.names().length();
    Readings readings=null;
    for(int i=0;i<len;i++){
    	readings=new Readings();
    	JSONObject myresponse1=new JSONObject(myresponse.getJSONObject(i+"").toString());
    	float r=myresponse1.getFloat("ramUsed");
    	readings.setRamUsed(r);
    	float d=myresponse1.getFloat("diskUsed");
    	readings.setDiskUsed(d);
    	double c=myresponse1.getDouble("cpuUtilization");
    	readings.setCpuUtilization(c);
    	String s=myresponse1.getString("readDateTime");
    	readings.setReadDateTime(s);
    	Ripository1.insertIntoDatabase(readings);
    	System.out.println(r+"  "+d+"  "+"  "+c+"  "+s);
    	Thread.sleep(500);
    }
  }
}
