
package co.edu.uniandes.csw.client.persistence.api;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import java.util.List;

public interface IClientPersistence extends _IClientPersistence {
    public List<ClientDTO> searchClients(String key);
}