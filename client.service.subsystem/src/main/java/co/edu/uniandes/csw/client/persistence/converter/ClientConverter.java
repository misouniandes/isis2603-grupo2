
package co.edu.uniandes.csw.client.persistence.converter;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import static co.edu.uniandes.csw.client.persistence.converter._ClientConverter.entity2PersistenceDTO;
import static co.edu.uniandes.csw.client.persistence.converter._ClientConverter.persistenceDTO2Entity;
import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;
import java.util.ArrayList;
import java.util.List;

public class ClientConverter extends _ClientConverter {
    public static ClientDTO entity2PersistenceDTO(ClientEntity entity){
        if(entity == null) return null;
        else
        {
            ClientDTO res=_ClientConverter.entity2PersistenceDTO(entity);
            res.setEmail(entity.getEmail());
            res.setPassword(entity.getPassword());
            return res;
        }
    }
    public static ClientEntity persistenceDTO2Entity(ClientDTO dto){
        if(dto == null) return null;
        else
        {
            ClientEntity res=_ClientConverter.persistenceDTO2Entity(dto);
            res.setEmail(dto.getEmail());
            res.setPassword(dto.getPassword());
            return res;
        
        }
    }
    public static List<ClientDTO> entity2PersistenceDTOList(List<ClientEntity> entities){
		List<ClientDTO> dtos=new ArrayList<ClientDTO>();
		for(ClientEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ClientEntity> persistenceDTO2EntityList(List<ClientDTO> dtos){
		List<ClientEntity> entities=new ArrayList<ClientEntity>();
		for(ClientDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}