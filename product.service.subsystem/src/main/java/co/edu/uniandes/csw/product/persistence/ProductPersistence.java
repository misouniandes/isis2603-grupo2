
package co.edu.uniandes.csw.product.persistence;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ProductPersistence extends _ProductPersistence  implements IProductPersistence {

    public List<ProductDTO> searchProducts(String key) {
        List<ProductDTO> lista = super.getProducts();
        List<ProductDTO> res = new ArrayList<ProductDTO>();
        for (ProductDTO d:lista)
                if(d.getName().contains(key))
                    res.add(d);
        return res;
    }

}