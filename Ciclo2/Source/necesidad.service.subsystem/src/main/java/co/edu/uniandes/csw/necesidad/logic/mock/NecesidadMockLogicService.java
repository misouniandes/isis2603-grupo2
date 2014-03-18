
package co.edu.uniandes.csw.necesidad.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.necesidad.logic.api.INecesidadLogicService;

@Alternative
@Singleton
public class NecesidadMockLogicService extends _NecesidadMockLogicService implements INecesidadLogicService {
	
}