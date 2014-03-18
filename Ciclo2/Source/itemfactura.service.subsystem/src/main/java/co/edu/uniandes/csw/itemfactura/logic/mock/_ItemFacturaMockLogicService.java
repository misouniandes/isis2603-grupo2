
package co.edu.uniandes.csw.itemfactura.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.logic.api._IItemFacturaLogicService;

public abstract class _ItemFacturaMockLogicService implements _IItemFacturaLogicService {

	private Long id= new Long(1);
	protected List<ItemFacturaDTO> data=new ArrayList<ItemFacturaDTO>();

	public ItemFacturaDTO createItemFactura(ItemFacturaDTO itemFactura){
		id++;
		itemFactura.setId(id);
		return itemFactura;
    }

	public List<ItemFacturaDTO> getItemFacturas(){
		return data; 
	}

	public ItemFacturaDTO getItemFactura(Long id){
		for(ItemFacturaDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteItemFactura(Long id){
	    ItemFacturaDTO delete=null;
		for(ItemFacturaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateItemFactura(ItemFacturaDTO itemFactura){
	    ItemFacturaDTO delete=null;
		for(ItemFacturaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(itemFactura);
		} 
	}	
}