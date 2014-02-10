
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

    public List<ProductDTO> searchProducts(String key) {
        List<ProductDTO> res = new ArrayList<ProductDTO>();
        for(ProductDTO d:super.data)
            if(d.getName().contains(key))
                res.add(d);
        return res;
    }
	
}