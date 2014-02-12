
package co.edu.uniandes.csw.pyme.persistence.converter;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;

public class PymeConverter extends _PymeConverter {  
	public static PymeDTO entity2PersistenceDTO(PymeEntity entity){
		if (entity != null) {
			PymeDTO dto = _PymeConverter.entity2PersistenceDTO(entity);
			dto.setDirContacto(entity.getDirContacto());
                        dto.setEmail(entity.getEmail());
			return dto;
		}else{
			return null;
		}
	}
	
	public static PymeEntity persistenceDTO2Entity(PymeDTO dto){
            if(dto==null) return null;
            PymeEntity entity = _PymeConverter.persistenceDTO2Entity(dto);
            entity.setDirContacto(dto.getDirContacto());
            entity.setEmail(dto.getEmail());
            return entity;
        }
}
