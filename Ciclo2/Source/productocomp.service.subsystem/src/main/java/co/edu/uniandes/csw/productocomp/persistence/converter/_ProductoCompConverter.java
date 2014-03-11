
package co.edu.uniandes.csw.productocomp.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;

public abstract class _ProductoCompConverter {


	public static ProductoCompDTO entity2PersistenceDTO(ProductoCompEntity entity){
		if (entity != null) {
			ProductoCompDTO dto = new ProductoCompDTO();
				dto.setTerminado(entity.getTerminado());
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
	
	public static ProductoCompEntity persistenceDTO2Entity(ProductoCompDTO dto){
		if(dto!=null){
			ProductoCompEntity entity=new ProductoCompEntity();
			entity.setTerminado(dto.getTerminado());
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
	
	public static List<ProductoCompDTO> entity2PersistenceDTOList(List<ProductoCompEntity> entities){
		List<ProductoCompDTO> dtos=new ArrayList<ProductoCompDTO>();
		for(ProductoCompEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ProductoCompEntity> persistenceDTO2EntityList(List<ProductoCompDTO> dtos){
		List<ProductoCompEntity> entities=new ArrayList<ProductoCompEntity>();
		for(ProductoCompDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}