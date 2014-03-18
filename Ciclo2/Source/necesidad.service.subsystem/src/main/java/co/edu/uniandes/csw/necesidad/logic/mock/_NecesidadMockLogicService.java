
package co.edu.uniandes.csw.necesidad.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.logic.api._INecesidadLogicService;

public abstract class _NecesidadMockLogicService implements _INecesidadLogicService {

	private Long id= new Long(1);
	protected List<NecesidadDTO> data=new ArrayList<NecesidadDTO>();

	public NecesidadDTO createNecesidad(NecesidadDTO necesidad){
		id++;
		necesidad.setId(id);
		return necesidad;
    }

	public List<NecesidadDTO> getNecesidads(){
		return data; 
	}

	public NecesidadDTO getNecesidad(Long id){
		for(NecesidadDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteNecesidad(Long id){
	    NecesidadDTO delete=null;
		for(NecesidadDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateNecesidad(NecesidadDTO necesidad){
	    NecesidadDTO delete=null;
		for(NecesidadDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(necesidad);
		} 
	}	
}