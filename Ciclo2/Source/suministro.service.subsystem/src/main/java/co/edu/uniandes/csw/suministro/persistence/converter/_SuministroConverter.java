
package co.edu.uniandes.csw.suministro.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;
import co.edu.uniandes.csw.suministro.persistence.entity.SuministroEntity;

public abstract class _SuministroConverter {


	public static SuministroDTO entity2PersistenceDTO(SuministroEntity entity){
		if (entity != null) {
			SuministroDTO dto = new SuministroDTO();
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
	
	public static SuministroEntity persistenceDTO2Entity(SuministroDTO dto){
		if(dto!=null){
			SuministroEntity entity=new SuministroEntity();
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
	
	public static List<SuministroDTO> entity2PersistenceDTOList(List<SuministroEntity> entities){
		List<SuministroDTO> dtos=new ArrayList<SuministroDTO>();
		for(SuministroEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<SuministroEntity> persistenceDTO2EntityList(List<SuministroDTO> dtos){
		List<SuministroEntity> entities=new ArrayList<SuministroEntity>();
		for(SuministroDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}