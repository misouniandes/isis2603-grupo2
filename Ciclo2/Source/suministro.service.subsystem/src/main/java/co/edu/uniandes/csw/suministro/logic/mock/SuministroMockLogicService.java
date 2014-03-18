
package co.edu.uniandes.csw.suministro.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.suministro.logic.api.ISuministroLogicService;

@Alternative
@Singleton
public class SuministroMockLogicService extends _SuministroMockLogicService implements ISuministroLogicService {
	
}