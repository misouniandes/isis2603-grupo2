
package co.edu.uniandes.csw.itemfactura.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;

public interface _IItemFacturaPersistence {

	public ItemFacturaDTO createItemFactura(ItemFacturaDTO detail);
	public List<ItemFacturaDTO> getItemFacturas();
	public ItemFacturaDTO getItemFactura(Long id);
	public void deleteItemFactura(Long id);
	public void updateItemFactura(ItemFacturaDTO detail);
	
}