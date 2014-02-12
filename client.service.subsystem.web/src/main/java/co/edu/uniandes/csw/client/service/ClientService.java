package co.edu.uniandes.csw.client.service;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Client")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientService extends _ClientService {

     @POST
     @Path("/search")
     public List<ClientDTO> searchClients(ClientDTO client)
     {
         return super.clientLogicService.searchClients(client.getName());
     }
}