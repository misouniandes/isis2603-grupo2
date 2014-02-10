
package co.edu.uniandes.csw.product.logic.api;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import java.util.List;

public interface IProductLogicService extends _IProductLogicService {
    public List<ProductDTO> searchProducts(String key);
}