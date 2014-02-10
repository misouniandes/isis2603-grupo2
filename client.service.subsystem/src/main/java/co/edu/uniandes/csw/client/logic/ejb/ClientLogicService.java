
package co.edu.uniandes.csw.client.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.client.logic.api.IClientLogicService;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class ClientLogicService extends _ClientLogicService implements IClientLogicService {

    public List<ClientDTO> searchClients(String key) {
        return super.persistance.searchClients(key);
    }

    
}