
package co.edu.uniandes.csw.suministro.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;

public interface _ISuministroLogicService {

	public SuministroDTO createSuministro(SuministroDTO detail);
	public List<SuministroDTO> getSuministros();
	public SuministroDTO getSuministro(Long id);
	public void deleteSuministro(Long id);
	public void updateSuministro(SuministroDTO detail);
	
}