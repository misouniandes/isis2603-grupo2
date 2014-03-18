
package co.edu.uniandes.csw.itemfactura.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;

public interface _IItemFacturaLogicService {

	public ItemFacturaDTO createItemFactura(ItemFacturaDTO detail);
	public List<ItemFacturaDTO> getItemFacturas();
	public ItemFacturaDTO getItemFactura(Long id);
	public void deleteItemFactura(Long id);
	public void updateItemFactura(ItemFacturaDTO detail);
	
}