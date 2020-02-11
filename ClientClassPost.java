package com.csbprog.CSBestProg2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientClass1 {
  public static void main(String args[]) throws IOException, JSONException {
	try {
     URL url = new URL("http://localhost:8080/CSBestProg2/getResource1");
     StringBuilder postData;
     Readings readings;
     while(true) {
       postData=new StringBuilder();
       readings=new Readings();
       
       LocalDateTime currDateTime = LocalDateTime.now();
       readings.setReadDateTime(currDateTime+"");
       //System.out.println(readings.getReadDateTime());
       
       long freespace = new File("/").getFreeSpace();
       long memorySize=new File("/").getTotalSpace();
       long usedRamSpace=memorySize-freespace; 
       float rup=(float)(usedRamSpace*100)/memorySize;   
       rup = (float) (Math.round(rup * 100.0) / 100.0);
       readings.setRamUsed(rup);
       //System.out.println(readings.getRamUsed());
       
       long diskSize= ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
       long feeSize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
       long usedDiskSpace=diskSize-feeSize;     
       float dup=(float)(usedDiskSpace*100)/diskSize;  
       dup = (float) (Math.round(dup * 100.0) / 100.0);
       readings.setDiskUsed(dup);
       //System.out.println(readings.getDiskUsed());
       
       double CPU=((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad(); 
       CPU=CPU*100;
       CPU =Math.round(CPU * 100.0) / 100.0;
       readings.setCpuUtilization(CPU);
       //System.out.println(readings.getCpuUtilization());
       
       String jsonInputString=JsonUtility.convertToJSON(readings);
       //System.out.println(jsonInputString);

       postData.append(jsonInputString);
      
       byte [] postDataBytes=postData.toString().getBytes("UTF-8");
       
       HttpURLConnection con = (HttpURLConnection) url.openConnection();
       con.setRequestMethod("POST");
       con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
       con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
       con.setDoOutput(true);
       
       con.getOutputStream().write(postDataBytes); 
       
       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
       String inputLine;
       StringBuffer response=new StringBuffer();
       while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
       }
       
       System.out.println(response.toString());

       // Parsing JSON string  ---------------------------------------------------
       JSONObject myresponse=new JSONObject(response.toString());
       System.out.print(myresponse.getFloat("ramUsed"));
       System.out.print("   ");
       System.out.print(myresponse.getFloat("diskUsed"));
       System.out.print("   ");
       System.out.print(myresponse.getDouble("cpuUtilization"));
       System.out.print("   ");
       System.out.println(myresponse.getString("readDateTime"));
       //-------------------------------------------------------------------------
       in.close();
       postData.setLength(0);
       Thread.sleep(4000);
     }//end of loop
	}//end of try
	catch(Exception e) {
		System.out.println("Ex. "+e);
	}
  }//end of main
}//end of class
