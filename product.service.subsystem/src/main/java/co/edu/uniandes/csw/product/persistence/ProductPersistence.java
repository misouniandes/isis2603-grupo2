
package co.edu.uniandes.csw.product.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ProductPersistence extends _ProductPersistence  implements IProductPersistence {

}