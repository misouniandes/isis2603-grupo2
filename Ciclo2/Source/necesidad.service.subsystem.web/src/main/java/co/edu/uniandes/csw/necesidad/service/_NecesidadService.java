package co.edu.uniandes.csw.necesidad.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.necesidad.logic.api.INecesidadLogicService;
import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;


public abstract class _NecesidadService {

	@Inject
	protected INecesidadLogicService necesidadLogicService;
	
	@POST
	public NecesidadDTO createNecesidad(NecesidadDTO necesidad){
		return necesidadLogicService.createNecesidad(necesidad);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteNecesidad(@PathParam("id") Long id){
		necesidadLogicService.deleteNecesidad(id);
	}
	
	@GET
	public List<NecesidadDTO> getNecesidads(){
		return necesidadLogicService.getNecesidads();
	}
	
	@GET
	@Path("{id}")
	public NecesidadDTO getNecesidad(@PathParam("id") Long id){
		return necesidadLogicService.getNecesidad(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateNecesidad(@PathParam("id") Long id, NecesidadDTO necesidad){
		necesidadLogicService.updateNecesidad(necesidad);
	}
	
}