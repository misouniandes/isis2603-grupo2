package co.edu.uniandes.csw.productocomp.master.logic.ejb;

import co.edu.uniandes.csw.productocomp.master.logic.api.IProductoCompMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class ProductoCompMasterLogicService extends _ProductoCompMasterLogicService implements IProductoCompMasterLogicService {

}