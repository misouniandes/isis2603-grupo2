
package co.edu.uniandes.csw.inventario.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.inventario.logic.dto.InventarioDTO;
import co.edu.uniandes.csw.inventario.logic.api._IInventarioLogicService;

public abstract class _InventarioMockLogicService implements _IInventarioLogicService {

	private Long id= new Long(1);
	protected List<InventarioDTO> data=new ArrayList<InventarioDTO>();

	public InventarioDTO createInventario(InventarioDTO inventario){
		id++;
		inventario.setId(id);
		return inventario;
    }

	public List<InventarioDTO> getInventarios(){
		return data; 
	}

	public InventarioDTO getInventario(Long id){
		for(InventarioDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteInventario(Long id){
	    InventarioDTO delete=null;
		for(InventarioDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateInventario(InventarioDTO inventario){
	    InventarioDTO delete=null;
		for(InventarioDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(inventario);
		} 
	}	
}