
package co.edu.uniandes.csw.necesidad.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;

public interface _INecesidadLogicService {

	public NecesidadDTO createNecesidad(NecesidadDTO detail);
	public List<NecesidadDTO> getNecesidads();
	public NecesidadDTO getNecesidad(Long id);
	public void deleteNecesidad(Long id);
	public void updateNecesidad(NecesidadDTO detail);
	
}