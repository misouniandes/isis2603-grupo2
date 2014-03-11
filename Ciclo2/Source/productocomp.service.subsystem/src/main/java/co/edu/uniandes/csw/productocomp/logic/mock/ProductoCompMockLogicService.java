
package co.edu.uniandes.csw.productocomp.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.productocomp.logic.api.IProductoCompLogicService;

@Alternative
@Singleton
public class ProductoCompMockLogicService extends _ProductoCompMockLogicService implements IProductoCompLogicService {
	
}