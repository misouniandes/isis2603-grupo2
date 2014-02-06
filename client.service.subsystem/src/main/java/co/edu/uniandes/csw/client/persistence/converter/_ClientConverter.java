
package co.edu.uniandes.csw.client.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;

public abstract class _ClientConverter {


	public static ClientDTO entity2PersistenceDTO(ClientEntity entity){
		if (entity != null) {
			ClientDTO dto = new ClientDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setCc(entity.getCc());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ClientEntity persistenceDTO2Entity(ClientDTO dto){
		if(dto!=null){
			ClientEntity entity=new ClientEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCc(dto.getCc());
			return entity;
		}else {
			return null;
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