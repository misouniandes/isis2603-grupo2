
package co.edu.uniandes.csw.documento.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.documento.persistence.api.IDocumentoPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class DocumentoPersistence extends _DocumentoPersistence  implements IDocumentoPersistence {

}