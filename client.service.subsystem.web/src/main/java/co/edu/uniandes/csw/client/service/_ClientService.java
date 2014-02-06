package co.edu.uniandes.csw.client.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.client.logic.api.IClientLogicService;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;


public abstract class _ClientService {

	@Inject
	protected IClientLogicService clientLogicService;
	
	@POST
	public ClientDTO createClient(ClientDTO client){
		return clientLogicService.createClient(client);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteClient(@PathParam("id") Long id){
		clientLogicService.deleteClient(id);
	}
	
	@GET
	public List<ClientDTO> getClients(){
		return clientLogicService.getClients();
	}
	
	@GET
	@Path("{id}")
	public ClientDTO getClient(@PathParam("id") Long id){
		return clientLogicService.getClient(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateClient(@PathParam("id") Long id, ClientDTO client){
		clientLogicService.updateClient(client);
	}
	
}