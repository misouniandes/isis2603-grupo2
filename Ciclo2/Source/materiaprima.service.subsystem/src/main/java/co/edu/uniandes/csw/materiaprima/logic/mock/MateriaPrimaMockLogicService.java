
package co.edu.uniandes.csw.materiaprima.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.materiaprima.logic.api.IMateriaPrimaLogicService;

@Alternative
@Singleton
public class MateriaPrimaMockLogicService extends _MateriaPrimaMockLogicService implements IMateriaPrimaLogicService {
	
}