
package co.edu.uniandes.csw.materiaprima.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;

public interface _IMateriaPrimaLogicService {

	public MateriaPrimaDTO createMateriaPrima(MateriaPrimaDTO detail);
	public List<MateriaPrimaDTO> getMateriaPrimas();
	public MateriaPrimaDTO getMateriaPrima(Long id);
	public void deleteMateriaPrima(Long id);
	public void updateMateriaPrima(MateriaPrimaDTO detail);
	
}