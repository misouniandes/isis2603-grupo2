
package co.edu.uniandes.csw.client.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.client.logic.api.IClientLogicService;

@Default
@Stateless
@LocalBean
public class ClientLogicService extends _ClientLogicService implements IClientLogicService {

}