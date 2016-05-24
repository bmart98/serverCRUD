package com.testservice.server.helper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.testservice.server.bean.Server;

/**
 * This class manages our servers in a HashTable for this example. This would
 * be replaced with a class that has an actual database implementation.
 *
 * @author Bart Martinez
 * @version 1.0
 * @since 05-21-2016
 */
public class ServerTable {

	public static final String BUILDING = "Building";
	public static final String RUNNING = "Running";
	public static final String DESTROYED = "Destroyed";

	// Hashtable to make it thread safe
	public static Hashtable<Integer,Server> serverTable = getServerTable();

	public ServerTable() {
		super();

		if(serverTable == null)
		{
			// Setup some initial servers
			serverTable = new Hashtable<>();
			Server serverOne = new Server(1, "Alfa", RUNNING, 4, 16, 100); //No not alpha
			Server serverTwo = new Server(2, "Bravo", RUNNING, 8, 64, 200);
			Server serverThree = new Server(3, "Charlie", RUNNING, 8, 128, 200);
			Server serverFour = new Server(4, "Delta", RUNNING, 2, 16, 100);

			serverTable.put(1, serverOne);
			serverTable.put(2, serverTwo);
			serverTable.put(3, serverThree);
			serverTable.put(4, serverFour);
		}
	}

	/**
	 * This method will return a list of all servers.
	 * @return List<Server>
	 */
	public List<Server> getAllServers()
	{
		List<Server> serverList = new ArrayList(serverTable.values());
		return serverList;
	}

	/**
	 * This method will return a single server based on the server ID.
	 *
	 * @param id The ID of the server we want returned
	 * @return Server The server we requested
	 */
	public Server getServer(int id) {
		Server server = serverTable.get(id);
		return server;
	}

	/**
	 * This method will setup a new server with the initial state of "Building". For simplicity
	 * in this example we will be using the hashtable key as our server id and the server id is simply
	 * the current size of the table plus one.
	 *
	 * @param server pass in a server object
	 * @return Server return the newly created server
	 */
	public Server buildServer(Server server) {
		server.setId(serverTable.size()+1);
		server.setServerState(BUILDING);
		serverTable.put(server.getId(), server);
		return server;
	}

	/**
	 * This method will update a server object that already exists.
	 *
	 * @param server
	 * @return Server
	 */
	public Server updateServer(Server server) {
		// No negative or 0 value IDs
		if (server.getId() <= 0) {
			return null;
		}
		// Check to see if the ID already exists first before making a change
		if (serverTable.containsKey(server.getId())) {
			// Do not allow a server in the build process to be updated and do not allow a server to be updated to the
			// "Building" state
			if (server.getServerState().equals(BUILDING)){
				return null;
			}
			serverTable.put(server.getId(), server);
		}
		else {
			return null;
		}
		return server;
	}

	/**
	 * This method sets the state of the server to "Running". This is executed after the server has
	 * completed the build process.
	 *
	 * @param id The id of the server we want to set to "Running"
	 * @return Server Returns the modified server
	 */
	public Server runServer(int id) {
		Server server = serverTable.get(id);
		if (server.getId() <= 0) {
			return null;
		}
		server.setServerState(RUNNING);
		serverTable.put(server.getId(), server);
		return server;
	}

	/**
	 * This method changes the server state to "Destroyed" but does not delete the server.
	 *
	 * @param id The id of the server we want to destroy
	 * @return server The server we destroyed
	 */
	public Server destroyServer(int id) {
		Server server = serverTable.get(id);
		if (server.getId() <= 0) {
			return null;
		}
		server = serverTable.get(id);
		server.setServerState(DESTROYED);
		serverTable.put(server.getId(), server);
		return server;
	}

	/**
	 * This method completely removes the server from the hashtable.
	 *
	 * @param id The id of the server we want to delete completely.
	 */
	public void deleteServer(int id) {
		serverTable.remove(id);
	}

	/**
	 * Getter method for the server table.
	 *
	 * @return serverTable The hashtable of servers.
	 */
	public static Hashtable<Integer, Server> getServerTable() {
		return serverTable;
	}

}
