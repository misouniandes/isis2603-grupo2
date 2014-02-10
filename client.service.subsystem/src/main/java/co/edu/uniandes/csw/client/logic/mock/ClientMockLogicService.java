
package co.edu.uniandes.csw.client.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.client.logic.api.IClientLogicService;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;

@Alternative
@Singleton
public class ClientMockLogicService extends _ClientMockLogicService implements IClientLogicService {

    public List<ClientDTO> searchClients(String key) {
        List<ClientDTO> resp=new ArrayList<ClientDTO>();
        for(ClientDTO d:super.data)
            if(d.getName().contains(key))
		resp.add(d);
        return resp;
    }
	
}