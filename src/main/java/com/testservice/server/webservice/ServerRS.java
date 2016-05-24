package com.testservice.server.webservice;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.testservice.server.bean.Server;
import com.testservice.server.helper.ServerTable;

/**
 *
 * Webservice class that performs RESTful CRUD operations for our servers.
 *
 * @author Bart Martinez
 * @version 1.0
 * @since 05-21-2016
 */

@Path("/servers")
public class ServerRS {

	private static final ExecutorService TASK_EXECUTOR = Executors.newCachedThreadPool();

	ServerTable serverTable = new ServerTable();

	/**
	 * GET that returns a list of all available servers.
	 *
	 * @return serverList
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Server> getServers() {
		List<Server> serverList = serverTable.getAllServers();
		return serverList;
	}

	/**
	 * GET that returns a single server based on the server ID.
	 *
	 * @param id The ID of the requested server
	 * @return The requested server
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Server getServerById(@PathParam("id") int id) {
		return serverTable.getServer(id);
	}

	/**
	 * POST that creates a new server. This creates a new thread that simulates the build process for a server.
	 * For this example we will use a simple timeout of 35 seconds to emulate the build process. Once that is
	 * completed it will change the server state from "Building" to "Running".
	 *
	 * @param server The server that we wish to create
	 * @return serverList A list of all servers in their current state.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Server> createServer(Server server) {

		serverTable.buildServer(server);

		TASK_EXECUTOR.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(35000);
				}
				catch(final InterruptedException e) {
					e.printStackTrace();
				}
				serverTable.runServer(server.getId());
			}
		});

		List<Server> serverList = serverTable.getAllServers();
		return serverList;
	}

	/**
	 * PUT that updates the specs on any available server.
	 *
	 * @param server The server we wish to update.
	 * @return The server that was updated with its new specs.
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Server updateServer(Server server) {
		return serverTable.updateServer(server);
	}

	/**
	 * PUT that will destroy the server and put it into the "Destroyed" state. Please note the path.
	 *
	 * @param id The ID of the server we wish to destroy.
	 * @return The server we put into the "Destroyed" state.
	 */
	@PUT
	@Path("/destroy/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Server destroyServer(@PathParam("id") int id) {
		return serverTable.destroyServer(id);
	}

	/**
	 * DELETE that will permanently remove the server from our table.
	 *
	 * @param id The ID of the server we wish to delete.
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteServer(@PathParam("id") int id) {
		serverTable.deleteServer(id);
	}

}
