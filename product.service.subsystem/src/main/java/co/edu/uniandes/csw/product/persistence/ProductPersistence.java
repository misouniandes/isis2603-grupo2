
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

 

}