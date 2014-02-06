
package co.edu.uniandes.csw.product.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;

public abstract class _ProductConverter {


	public static ProductDTO entity2PersistenceDTO(ProductEntity entity){
		if (entity != null) {
			ProductDTO dto = new ProductDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setValue(entity.getValue());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ProductEntity persistenceDTO2Entity(ProductDTO dto){
		if(dto!=null){
			ProductEntity entity=new ProductEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setValue(dto.getValue());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ProductDTO> entity2PersistenceDTOList(List<ProductEntity> entities){
		List<ProductDTO> dtos=new ArrayList<ProductDTO>();
		for(ProductEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ProductEntity> persistenceDTO2EntityList(List<ProductDTO> dtos){
		List<ProductEntity> entities=new ArrayList<ProductEntity>();
		for(ProductDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}