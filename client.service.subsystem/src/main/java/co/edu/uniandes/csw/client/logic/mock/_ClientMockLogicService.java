
package co.edu.uniandes.csw.client.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.logic.api._IClientLogicService;

public abstract class _ClientMockLogicService implements _IClientLogicService {

	private Long id= new Long(1);
	protected List<ClientDTO> data=new ArrayList<ClientDTO>();

	public ClientDTO createClient(ClientDTO client){
		id++;
		client.setId(id);
		return client;
    }

	public List<ClientDTO> getClients(){
		return data; 
	}

	public ClientDTO getClient(Long id){
		for(ClientDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteClient(Long id){
	    ClientDTO delete=null;
		for(ClientDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateClient(ClientDTO client){
	    ClientDTO delete=null;
		for(ClientDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(client);
		} 
	}	
}