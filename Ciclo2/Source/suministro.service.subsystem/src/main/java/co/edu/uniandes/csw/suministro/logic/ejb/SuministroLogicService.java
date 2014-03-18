
package co.edu.uniandes.csw.suministro.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.suministro.logic.api.ISuministroLogicService;

@Default
@Stateless
@LocalBean
public class SuministroLogicService extends _SuministroLogicService implements ISuministroLogicService {

}