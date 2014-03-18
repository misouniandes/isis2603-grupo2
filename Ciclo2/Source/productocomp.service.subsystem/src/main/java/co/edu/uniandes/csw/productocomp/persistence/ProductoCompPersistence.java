
package co.edu.uniandes.csw.productocomp.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ProductoCompPersistence extends _ProductoCompPersistence  implements IProductoCompPersistence {

}