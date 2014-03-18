package co.edu.uniandes.csw.productocomp.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.productocomp.logic.api.IProductoCompLogicService;
import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;


public abstract class _ProductoCompService {

	@Inject
	protected IProductoCompLogicService productoCompLogicService;
	
	@POST
	public ProductoCompDTO createProductoComp(ProductoCompDTO productoComp){
		return productoCompLogicService.createProductoComp(productoComp);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteProductoComp(@PathParam("id") Long id){
		productoCompLogicService.deleteProductoComp(id);
	}
	
	@GET
	public List<ProductoCompDTO> getProductoComps(){
		return productoCompLogicService.getProductoComps();
	}
	
	@GET
	@Path("{id}")
	public ProductoCompDTO getProductoComp(@PathParam("id") Long id){
		return productoCompLogicService.getProductoComp(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateProductoComp(@PathParam("id") Long id, ProductoCompDTO productoComp){
		productoCompLogicService.updateProductoComp(productoComp);
	}
	
}