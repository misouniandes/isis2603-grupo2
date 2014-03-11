
package co.edu.uniandes.csw.pyme.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;

public abstract class _PymeConverter {


	public static PymeDTO entity2PersistenceDTO(PymeEntity entity){
		if (entity != null) {
			PymeDTO dto = new PymeDTO();
				dto.setName(entity.getName());
				dto.setId(entity.getId());
                                dto.setDirContacto(entity.getDirContacto());
                                dto.setEmail(entity.getEmail());
                                dto.setDescription(entity.getDescription());
                          
			return dto;
		}else{
			return null;
		}
	}
	
	public static PymeEntity persistenceDTO2Entity(PymeDTO dto){
		if(dto!=null){
			PymeEntity entity=new PymeEntity();
			entity.setName(dto.getName());
			entity.setId(dto.getId());
                        entity.setDirContacto(dto.getDirContacto());
                        entity.setEmail(dto.getEmail());
                        entity.setDescription(dto.getDescription());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<PymeDTO> entity2PersistenceDTOList(List<PymeEntity> entities){
		List<PymeDTO> dtos=new ArrayList<PymeDTO>();
		for(PymeEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<PymeEntity> persistenceDTO2EntityList(List<PymeDTO> dtos){
		List<PymeEntity> entities=new ArrayList<PymeEntity>();
		for(PymeDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}