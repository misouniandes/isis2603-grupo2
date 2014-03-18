
package co.edu.uniandes.csw.documento.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.logic.api._IDocumentoLogicService;
import co.edu.uniandes.csw.documento.persistence.api.IDocumentoPersistence;

public abstract class _DocumentoLogicService implements _IDocumentoLogicService {

	@Inject
	protected IDocumentoPersistence persistance;

	public DocumentoDTO createDocumento(DocumentoDTO documento){
		return persistance.createDocumento( documento); 
    }

	public List<DocumentoDTO> getDocumentos(){
		return persistance.getDocumentos(); 
	}

	public DocumentoDTO getDocumento(Long id){
		return persistance.getDocumento(id); 
	}

	public void deleteDocumento(Long id){
	    persistance.deleteDocumento(id); 
	}

	public void updateDocumento(DocumentoDTO documento){
	    persistance.updateDocumento(documento); 
	}	
}