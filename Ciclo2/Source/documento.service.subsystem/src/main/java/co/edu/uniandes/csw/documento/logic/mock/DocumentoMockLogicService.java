
package co.edu.uniandes.csw.documento.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.documento.logic.api.IDocumentoLogicService;

@Alternative
@Singleton
public class DocumentoMockLogicService extends _DocumentoMockLogicService implements IDocumentoLogicService {
	
}