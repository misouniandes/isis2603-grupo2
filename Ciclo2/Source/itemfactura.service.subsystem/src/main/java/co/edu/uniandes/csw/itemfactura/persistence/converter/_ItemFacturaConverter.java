
package co.edu.uniandes.csw.itemfactura.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.persistence.entity.ItemFacturaEntity;

public abstract class _ItemFacturaConverter {


	public static ItemFacturaDTO entity2PersistenceDTO(ItemFacturaEntity entity){
		if (entity != null) {
			ItemFacturaDTO dto = new ItemFacturaDTO();
				dto.setCantidad(entity.getCantidad());
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setProductoId(entity.getProductoId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ItemFacturaEntity persistenceDTO2Entity(ItemFacturaDTO dto){
		if(dto!=null){
			ItemFacturaEntity entity=new ItemFacturaEntity();
			entity.setCantidad(dto.getCantidad());
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setProductoId(dto.getProductoId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ItemFacturaDTO> entity2PersistenceDTOList(List<ItemFacturaEntity> entities){
		List<ItemFacturaDTO> dtos=new ArrayList<ItemFacturaDTO>();
		for(ItemFacturaEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ItemFacturaEntity> persistenceDTO2EntityList(List<ItemFacturaDTO> dtos){
		List<ItemFacturaEntity> entities=new ArrayList<ItemFacturaEntity>();
		for(ItemFacturaDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}