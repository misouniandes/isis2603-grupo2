
package co.edu.uniandes.csw.suministro.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;
import co.edu.uniandes.csw.suministro.logic.api._ISuministroLogicService;

public abstract class _SuministroMockLogicService implements _ISuministroLogicService {

	private Long id= new Long(1);
	protected List<SuministroDTO> data=new ArrayList<SuministroDTO>();

	public SuministroDTO createSuministro(SuministroDTO suministro){
		id++;
		suministro.setId(id);
		return suministro;
    }

	public List<SuministroDTO> getSuministros(){
		return data; 
	}

	public SuministroDTO getSuministro(Long id){
		for(SuministroDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteSuministro(Long id){
	    SuministroDTO delete=null;
		for(SuministroDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateSuministro(SuministroDTO suministro){
	    SuministroDTO delete=null;
		for(SuministroDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(suministro);
		} 
	}	
}