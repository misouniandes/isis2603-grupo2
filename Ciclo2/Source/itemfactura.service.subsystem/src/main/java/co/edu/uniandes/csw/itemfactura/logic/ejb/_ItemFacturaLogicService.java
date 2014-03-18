
package co.edu.uniandes.csw.itemfactura.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.logic.api._IItemFacturaLogicService;
import co.edu.uniandes.csw.itemfactura.persistence.api.IItemFacturaPersistence;

public abstract class _ItemFacturaLogicService implements _IItemFacturaLogicService {

	@Inject
	protected IItemFacturaPersistence persistance;

	public ItemFacturaDTO createItemFactura(ItemFacturaDTO itemFactura){
		return persistance.createItemFactura( itemFactura); 
    }

	public List<ItemFacturaDTO> getItemFacturas(){
		return persistance.getItemFacturas(); 
	}

	public ItemFacturaDTO getItemFactura(Long id){
		return persistance.getItemFactura(id); 
	}

	public void deleteItemFactura(Long id){
	    persistance.deleteItemFactura(id); 
	}

	public void updateItemFactura(ItemFacturaDTO itemFactura){
	    persistance.updateItemFactura(itemFactura); 
	}	
}