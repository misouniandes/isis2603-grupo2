
package co.edu.uniandes.csw.product.persistence.converter;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;

public class ProductConverter extends _ProductConverter {
    public static ProductDTO entity2PersistenceDTO(ProductEntity entity){
		if (entity != null) {
			ProductDTO dto = _ProductConverter.entity2PersistenceDTO(entity);
                        dto.setDescripcion(entity.getDescripcion());
                        dto.setURLImagen(entity.getURLImagen());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ProductEntity persistenceDTO2Entity(ProductDTO dto){
		if(dto!=null){
			ProductEntity entity=_ProductConverter.persistenceDTO2Entity(dto);
                        entity.setURLImagen(dto.getURLImagen());
                        entity.setDescripcion(dto.getDescripcion());
			return entity;
		}else {
			return null;
		}
	} 
}