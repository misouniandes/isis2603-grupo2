package co.edu.uniandes.csw.itemfactura.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.itemfactura.logic.api.IItemFacturaLogicService;
import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;


public abstract class _ItemFacturaService {

	@Inject
	protected IItemFacturaLogicService itemFacturaLogicService;
	
	@POST
	public ItemFacturaDTO createItemFactura(ItemFacturaDTO itemFactura){
		return itemFacturaLogicService.createItemFactura(itemFactura);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteItemFactura(@PathParam("id") Long id){
		itemFacturaLogicService.deleteItemFactura(id);
	}
	
	@GET
	public List<ItemFacturaDTO> getItemFacturas(){
		return itemFacturaLogicService.getItemFacturas();
	}
	
	@GET
	@Path("{id}")
	public ItemFacturaDTO getItemFactura(@PathParam("id") Long id){
		return itemFacturaLogicService.getItemFactura(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateItemFactura(@PathParam("id") Long id, ItemFacturaDTO itemFactura){
		itemFacturaLogicService.updateItemFactura(itemFactura);
	}
	
}