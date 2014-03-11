
package co.edu.uniandes.csw.client.persistence;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ClientPersistence extends _ClientPersistence  implements IClientPersistence {

    public List<ClientDTO> searchClients(String key) {
        List<ClientDTO> listaTodos = super.getClients();
        List<ClientDTO> res = new ArrayList<ClientDTO>();
        for(ClientDTO d:listaTodos)
            if(d.getName().contains(key))   
                res.add(d);
        return res;
    }

}