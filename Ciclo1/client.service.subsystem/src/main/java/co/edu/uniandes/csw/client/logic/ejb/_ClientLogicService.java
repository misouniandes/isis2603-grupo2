
package co.edu.uniandes.csw.client.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.logic.api._IClientLogicService;
import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;

public abstract class _ClientLogicService implements _IClientLogicService {

	@Inject
	protected IClientPersistence persistance;

	public ClientDTO createClient(ClientDTO client){
		return persistance.createClient( client); 
    }

	public List<ClientDTO> getClients(){
		return persistance.getClients(); 
	}

	public ClientDTO getClient(Long id){
		return persistance.getClient(id); 
	}

	public void deleteClient(Long id){
	    persistance.deleteClient(id); 
	}

	public void updateClient(ClientDTO client){
	    persistance.updateClient(client); 
	}	
}