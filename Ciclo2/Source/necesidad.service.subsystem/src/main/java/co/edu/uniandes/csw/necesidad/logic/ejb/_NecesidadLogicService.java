
package co.edu.uniandes.csw.necesidad.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.logic.api._INecesidadLogicService;
import co.edu.uniandes.csw.necesidad.persistence.api.INecesidadPersistence;

public abstract class _NecesidadLogicService implements _INecesidadLogicService {

	@Inject
	protected INecesidadPersistence persistance;

	public NecesidadDTO createNecesidad(NecesidadDTO necesidad){
		return persistance.createNecesidad( necesidad); 
    }

	public List<NecesidadDTO> getNecesidads(){
		return persistance.getNecesidads(); 
	}

	public NecesidadDTO getNecesidad(Long id){
		return persistance.getNecesidad(id); 
	}

	public void deleteNecesidad(Long id){
	    persistance.deleteNecesidad(id); 
	}

	public void updateNecesidad(NecesidadDTO necesidad){
	    persistance.updateNecesidad(necesidad); 
	}	
}