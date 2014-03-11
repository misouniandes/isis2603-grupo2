
package co.edu.uniandes.csw.materiaprima.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;
import co.edu.uniandes.csw.materiaprima.logic.api._IMateriaPrimaLogicService;

public abstract class _MateriaPrimaMockLogicService implements _IMateriaPrimaLogicService {

	private Long id= new Long(1);
	protected List<MateriaPrimaDTO> data=new ArrayList<MateriaPrimaDTO>();

	public MateriaPrimaDTO createMateriaPrima(MateriaPrimaDTO materiaPrima){
		id++;
		materiaPrima.setId(id);
		return materiaPrima;
    }

	public List<MateriaPrimaDTO> getMateriaPrimas(){
		return data; 
	}

	public MateriaPrimaDTO getMateriaPrima(Long id){
		for(MateriaPrimaDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteMateriaPrima(Long id){
	    MateriaPrimaDTO delete=null;
		for(MateriaPrimaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateMateriaPrima(MateriaPrimaDTO materiaPrima){
	    MateriaPrimaDTO delete=null;
		for(MateriaPrimaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(materiaPrima);
		} 
	}	
}