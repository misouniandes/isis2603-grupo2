
package co.edu.uniandes.csw.product.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.product.logic.api.IProductLogicService;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;

@Alternative
@Singleton
public class ProductMockLogicService extends _ProductMockLogicService implements IProductLogicService {

    public List<ProductDTO> searchProduct(ProductDTO pyme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
	
}