package co.edu.uniandes.csw.documento.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.documento.logic.api.IDocumentoLogicService;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;


public abstract class _DocumentoService {

	@Inject
	protected IDocumentoLogicService documentoLogicService;
	
	@POST
	public DocumentoDTO createDocumento(DocumentoDTO documento){
		return documentoLogicService.createDocumento(documento);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteDocumento(@PathParam("id") Long id){
		documentoLogicService.deleteDocumento(id);
	}
	
	@GET
	public List<DocumentoDTO> getDocumentos(){
		return documentoLogicService.getDocumentos();
	}
	
	@GET
	@Path("{id}")
	public DocumentoDTO getDocumento(@PathParam("id") Long id){
		return documentoLogicService.getDocumento(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateDocumento(@PathParam("id") Long id, DocumentoDTO documento){
		documentoLogicService.updateDocumento(documento);
	}
	
}