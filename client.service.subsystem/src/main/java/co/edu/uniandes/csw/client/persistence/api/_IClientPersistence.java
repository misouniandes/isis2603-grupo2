
package co.edu.uniandes.csw.client.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;

public interface _IClientPersistence {

	public ClientDTO createClient(ClientDTO detail);
	public List<ClientDTO> getClients();
	public ClientDTO getClient(Long id);
	public void deleteClient(Long id);
	public void updateClient(ClientDTO detail);
	
}