
package co.edu.uniandes.csw.necesidad.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.necesidad.persistence.api.INecesidadPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class NecesidadPersistence extends _NecesidadPersistence  implements INecesidadPersistence {

}