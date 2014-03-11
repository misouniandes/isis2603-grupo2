
package co.edu.uniandes.csw.necesidad.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.necesidad.logic.api.INecesidadLogicService;

@Default
@Stateless
@LocalBean
public class NecesidadLogicService extends _NecesidadLogicService implements INecesidadLogicService {

}