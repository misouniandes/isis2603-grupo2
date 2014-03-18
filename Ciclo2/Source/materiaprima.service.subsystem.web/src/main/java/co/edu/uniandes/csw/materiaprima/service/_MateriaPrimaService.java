package co.edu.uniandes.csw.materiaprima.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.materiaprima.logic.api.IMateriaPrimaLogicService;
import co.edu.uniandes.csw.materiaprima.logic.dto.MateriaPrimaDTO;


public abstract class _MateriaPrimaService {

	@Inject
	protected IMateriaPrimaLogicService materiaPrimaLogicService;
	
	@POST
	public MateriaPrimaDTO createMateriaPrima(MateriaPrimaDTO materiaPrima){
		return materiaPrimaLogicService.createMateriaPrima(materiaPrima);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteMateriaPrima(@PathParam("id") Long id){
		materiaPrimaLogicService.deleteMateriaPrima(id);
	}
	
	@GET
	public List<MateriaPrimaDTO> getMateriaPrimas(){
		return materiaPrimaLogicService.getMateriaPrimas();
	}
	
	@GET
	@Path("{id}")
	public MateriaPrimaDTO getMateriaPrima(@PathParam("id") Long id){
		return materiaPrimaLogicService.getMateriaPrima(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateMateriaPrima(@PathParam("id") Long id, MateriaPrimaDTO materiaPrima){
		materiaPrimaLogicService.updateMateriaPrima(materiaPrima);
	}
	
}