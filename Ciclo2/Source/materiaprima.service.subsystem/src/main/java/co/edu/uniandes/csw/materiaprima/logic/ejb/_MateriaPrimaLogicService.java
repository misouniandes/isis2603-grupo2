
package co.edu.uniandes.csw.materiaprima.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;
import co.edu.uniandes.csw.materiaprima.logic.api._IMateriaPrimaLogicService;
import co.edu.uniandes.csw.materiaprima.persistence.api.IMateriaPrimaPersistence;

public abstract class _MateriaPrimaLogicService implements _IMateriaPrimaLogicService {

	@Inject
	protected IMateriaPrimaPersistence persistance;

	public MateriaPrimaDTO createMateriaPrima(MateriaPrimaDTO materiaPrima){
		return persistance.createMateriaPrima( materiaPrima); 
    }

	public List<MateriaPrimaDTO> getMateriaPrimas(){
		return persistance.getMateriaPrimas(); 
	}

	public MateriaPrimaDTO getMateriaPrima(Long id){
		return persistance.getMateriaPrima(id); 
	}

	public void deleteMateriaPrima(Long id){
	    persistance.deleteMateriaPrima(id); 
	}

	public void updateMateriaPrima(MateriaPrimaDTO materiaPrima){
	    persistance.updateMateriaPrima(materiaPrima); 
	}	
}