package com.csbprog.CSBestProg2;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Readings {
	private float ramUsed;
	private float diskUsed;
	private double cpuUtilization;
	private String readDateTime;
	public float getRamUsed() {
		return ramUsed;
	}
	public void setRamUsed(float ramUsed) {
		this.ramUsed = ramUsed;
	}
	public float getDiskUsed() {
		return diskUsed;
	}
	public void setDiskUsed(float diskUsed) {
		this.diskUsed = diskUsed;
	}
	public double getCpuUtilization() {
		return cpuUtilization;
	}
	public void setCpuUtilization(double cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}
	public String getReadDateTime() {
		return readDateTime;
	}
	public void setReadDateTime(String readDateTime) {
		this.readDateTime = readDateTime;
	}
	@Override
	public String toString() {
		return "Readings [ramUsed=" + ramUsed + ", diskUsed=" + diskUsed + ", cpuUtilization=" + cpuUtilization
				+ ", readDateTime=" + readDateTime + "]";
	}
}
