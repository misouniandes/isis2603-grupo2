
package co.edu.uniandes.csw.materiaprima.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.materiaprima.logic.api.IMateriaPrimaLogicService;

@Default
@Stateless
@LocalBean
public class MateriaPrimaLogicService extends _MateriaPrimaLogicService implements IMateriaPrimaLogicService {

}