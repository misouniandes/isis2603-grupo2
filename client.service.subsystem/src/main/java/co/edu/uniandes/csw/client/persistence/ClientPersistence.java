
package co.edu.uniandes.csw.client.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ClientPersistence extends _ClientPersistence  implements IClientPersistence {

}