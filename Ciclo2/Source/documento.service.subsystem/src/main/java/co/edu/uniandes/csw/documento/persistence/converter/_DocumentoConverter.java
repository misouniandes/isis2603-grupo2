
package co.edu.uniandes.csw.documento.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.entity.DocumentoEntity;

public abstract class _DocumentoConverter {


	public static DocumentoDTO entity2PersistenceDTO(DocumentoEntity entity){
		if (entity != null) {
			DocumentoDTO dto = new DocumentoDTO();
				dto.setType(entity.getType());
				dto.setFecha(entity.getFecha());
				dto.setId(entity.getId());
				dto.setAutor(entity.getAutor());
				dto.setDescripcion(entity.getDescripcion());
				dto.setName(entity.getName());
			return dto;
		}else{
			return null;
		}
	}
	
	public static DocumentoEntity persistenceDTO2Entity(DocumentoDTO dto){
		if(dto!=null){
			DocumentoEntity entity=new DocumentoEntity();
			entity.setType(dto.getType());
			entity.setFecha(dto.getFecha());
			entity.setId(dto.getId());
			entity.setAutor(dto.getAutor());
			entity.setDescripcion(dto.getDescripcion());
			entity.setName(dto.getName());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<DocumentoDTO> entity2PersistenceDTOList(List<DocumentoEntity> entities){
		List<DocumentoDTO> dtos=new ArrayList<DocumentoDTO>();
		for(DocumentoEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<DocumentoEntity> persistenceDTO2EntityList(List<DocumentoDTO> dtos){
		List<DocumentoEntity> entities=new ArrayList<DocumentoEntity>();
		for(DocumentoDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}