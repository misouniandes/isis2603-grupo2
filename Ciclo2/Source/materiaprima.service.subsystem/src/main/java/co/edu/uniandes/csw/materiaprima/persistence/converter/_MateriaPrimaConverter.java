
package co.edu.uniandes.csw.materiaprima.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;
import co.edu.uniandes.csw.materiaprima.persistence.entity.MateriaPrimaEntity;

public abstract class _MateriaPrimaConverter {


	public static MateriaPrimaDTO entity2PersistenceDTO(MateriaPrimaEntity entity){
		if (entity != null) {
			MateriaPrimaDTO dto = new MateriaPrimaDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setCantidadDisp(entity.getCantidadDisp());
				dto.setCanitdadEnProc(entity.getCanitdadEnProc());
				dto.setTiempoEspera(entity.getTiempoEspera());
				dto.setCostoPromedio(entity.getCostoPromedio());
				dto.setLugar(entity.getLugar());
				dto.setCantidadMin(entity.getCantidadMin());
				dto.setCantidadMax(entity.getCantidadMax());
			return dto;
		}else{
			return null;
		}
	}
	
	public static MateriaPrimaEntity persistenceDTO2Entity(MateriaPrimaDTO dto){
		if(dto!=null){
			MateriaPrimaEntity entity=new MateriaPrimaEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCantidadDisp(dto.getCantidadDisp());
			entity.setCanitdadEnProc(dto.getCanitdadEnProc());
			entity.setTiempoEspera(dto.getTiempoEspera());
			entity.setCostoPromedio(dto.getCostoPromedio());
			entity.setLugar(dto.getLugar());
			entity.setCantidadMin(dto.getCantidadMin());
			entity.setCantidadMax(dto.getCantidadMax());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<MateriaPrimaDTO> entity2PersistenceDTOList(List<MateriaPrimaEntity> entities){
		List<MateriaPrimaDTO> dtos=new ArrayList<MateriaPrimaDTO>();
		for(MateriaPrimaEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<MateriaPrimaEntity> persistenceDTO2EntityList(List<MateriaPrimaDTO> dtos){
		List<MateriaPrimaEntity> entities=new ArrayList<MateriaPrimaEntity>();
		for(MateriaPrimaDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}