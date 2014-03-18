package co.edu.uniandes.csw.productocomp.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.productocomp.master.persistence.api.IProductoCompMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class ProductoCompMasterPersistence extends _ProductoCompMasterPersistence  implements IProductoCompMasterPersistence {

}