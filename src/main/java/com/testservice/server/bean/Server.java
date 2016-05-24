package com.testservice.server.bean;

/**
 * This is the bean for describing and creating a new server.
 *
 * @author Bart Martinez
 * @version 1.0
 * @since 05-21-2016
 */

public class Server {

	public String name;
	public String serverState;
	public int id;
	public int cpus;
	public int ram;
	public int disSpace;

	public Server() {
	}

	//Constructor used by web service
	public Server(String name, int cpus, int ram, int disSpace) {
		this.name = name;
		this.cpus = cpus;
		this.ram = ram;
		this.disSpace = disSpace;
	}

	//Constructor used to set test data in ServerTable
	public Server(int id, String name, String serverState, int cpus, int ram, int disSpace) {
		this.id = id;
		this.name = name;
		this.serverState = serverState;
		this.cpus = cpus;
		this.ram = ram;
		this.disSpace = disSpace;
	}

	// Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getCpus() {
		return cpus;
	}
	public void setCpus(int cpus) {
		this.cpus = cpus;
	}

	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getDisSpace() {
		return disSpace;
	}
	public void setDisSpace(int disSpace) {
		this.disSpace = disSpace;
	}

	public String getServerState() {
		return serverState;
	}
	public void setServerState(String serverState) {
		this.serverState = serverState;
	}
}