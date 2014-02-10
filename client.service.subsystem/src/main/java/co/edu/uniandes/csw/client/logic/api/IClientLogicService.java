
package co.edu.uniandes.csw.client.logic.api;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import java.util.List;

public interface IClientLogicService extends _IClientLogicService {
       public List<ClientDTO> searchClients(String key);
}