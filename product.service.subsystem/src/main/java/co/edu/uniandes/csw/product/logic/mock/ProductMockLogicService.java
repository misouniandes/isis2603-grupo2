
package co.edu.uniandes.csw.product.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.product.logic.api.IProductLogicService;

@Alternative
@Singleton
public class ProductMockLogicService extends _ProductMockLogicService implements IProductLogicService {
	
}