package com.csbprog.CSBestProg2;

import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("getResource1")
public class AlienResource1 { 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String geData(String data){
		System.out.println(data);
		Ripository1.getDataFromClient(data);
		return(data);
	}
	
	@GET
	@Path("getdata")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer,Readings> getAllData() {
		Map<Integer,Readings> map=Ripository1.getAllDataFromDataBase();
		return(map);
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateReadings(String readings) {
		//System.out.println(readings);
		return(Ripository1.updateRecord(readings));
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteReadings(String readings) {
		return(Ripository1.deleteRecord(readings));
	}
}

