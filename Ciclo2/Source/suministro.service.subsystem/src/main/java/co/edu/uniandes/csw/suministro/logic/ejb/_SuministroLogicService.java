
package co.edu.uniandes.csw.suministro.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;
import co.edu.uniandes.csw.suministro.logic.api._ISuministroLogicService;
import co.edu.uniandes.csw.suministro.persistence.api.ISuministroPersistence;

public abstract class _SuministroLogicService implements _ISuministroLogicService {

	@Inject
	protected ISuministroPersistence persistance;

	public SuministroDTO createSuministro(SuministroDTO suministro){
		return persistance.createSuministro( suministro); 
    }

	public List<SuministroDTO> getSuministros(){
		return persistance.getSuministros(); 
	}

	public SuministroDTO getSuministro(Long id){
		return persistance.getSuministro(id); 
	}

	public void deleteSuministro(Long id){
	    persistance.deleteSuministro(id); 
	}

	public void updateSuministro(SuministroDTO suministro){
	    persistance.updateSuministro(suministro); 
	}	
}