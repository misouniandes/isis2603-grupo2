
package co.edu.uniandes.csw.product.persistence.api;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import java.util.List;

public interface IProductPersistence extends _IProductPersistence {
    public List<ProductDTO> searchProducts(String key);
}