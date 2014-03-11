
package co.edu.uniandes.csw.necesidad.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.persistence.entity.NecesidadEntity;

public abstract class _NecesidadConverter {


	public static NecesidadDTO entity2PersistenceDTO(NecesidadEntity entity){
		if (entity != null) {
			NecesidadDTO dto = new NecesidadDTO();
				dto.setCantidad(entity.getCantidad());
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setProductoId(entity.getProductoId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static NecesidadEntity persistenceDTO2Entity(NecesidadDTO dto){
		if(dto!=null){
			NecesidadEntity entity=new NecesidadEntity();
			entity.setCantidad(dto.getCantidad());
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setProductoId(dto.getProductoId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<NecesidadDTO> entity2PersistenceDTOList(List<NecesidadEntity> entities){
		List<NecesidadDTO> dtos=new ArrayList<NecesidadDTO>();
		for(NecesidadEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<NecesidadEntity> persistenceDTO2EntityList(List<NecesidadDTO> dtos){
		List<NecesidadEntity> entities=new ArrayList<NecesidadEntity>();
		for(NecesidadDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}