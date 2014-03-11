
package co.edu.uniandes.csw.documento.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.documento.logic.api.IDocumentoLogicService;

@Default
@Stateless
@LocalBean
public class DocumentoLogicService extends _DocumentoLogicService implements IDocumentoLogicService {

}