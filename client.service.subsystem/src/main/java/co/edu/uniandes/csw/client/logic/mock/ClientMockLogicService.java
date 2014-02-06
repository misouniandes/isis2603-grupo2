
package co.edu.uniandes.csw.client.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.client.logic.api.IClientLogicService;

@Alternative
@Singleton
public class ClientMockLogicService extends _ClientMockLogicService implements IClientLogicService {
	
}