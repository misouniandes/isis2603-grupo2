
package co.edu.uniandes.csw.productocomp.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.productocomp.logic.api.IProductoCompLogicService;

@Default
@Stateless
@LocalBean
public class ProductoCompLogicService extends _ProductoCompLogicService implements IProductoCompLogicService {

}