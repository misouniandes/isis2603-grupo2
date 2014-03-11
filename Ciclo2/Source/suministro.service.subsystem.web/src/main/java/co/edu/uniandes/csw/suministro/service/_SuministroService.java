package co.edu.uniandes.csw.suministro.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.suministro.logic.api.ISuministroLogicService;
import co.edu.uniandes.csw.suministro.logic.dto.SuministroDTO;


public abstract class _SuministroService {

	@Inject
	protected ISuministroLogicService suministroLogicService;
	
	@POST
	public SuministroDTO createSuministro(SuministroDTO suministro){
		return suministroLogicService.createSuministro(suministro);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteSuministro(@PathParam("id") Long id){
		suministroLogicService.deleteSuministro(id);
	}
	
	@GET
	public List<SuministroDTO> getSuministros(){
		return suministroLogicService.getSuministros();
	}
	
	@GET
	@Path("{id}")
	public SuministroDTO getSuministro(@PathParam("id") Long id){
		return suministroLogicService.getSuministro(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateSuministro(@PathParam("id") Long id, SuministroDTO suministro){
		suministroLogicService.updateSuministro(suministro);
	}
	
}