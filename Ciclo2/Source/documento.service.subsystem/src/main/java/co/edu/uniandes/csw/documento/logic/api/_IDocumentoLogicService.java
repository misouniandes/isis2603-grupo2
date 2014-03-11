
package co.edu.uniandes.csw.documento.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;

public interface _IDocumentoLogicService {

	public DocumentoDTO createDocumento(DocumentoDTO detail);
	public List<DocumentoDTO> getDocumentos();
	public DocumentoDTO getDocumento(Long id);
	public void deleteDocumento(Long id);
	public void updateDocumento(DocumentoDTO detail);
	
}