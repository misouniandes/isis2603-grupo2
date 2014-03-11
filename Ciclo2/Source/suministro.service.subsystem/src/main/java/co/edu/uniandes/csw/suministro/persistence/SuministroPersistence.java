
package co.edu.uniandes.csw.suministro.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.suministro.persistence.api.ISuministroPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class SuministroPersistence extends _SuministroPersistence  implements ISuministroPersistence {

}