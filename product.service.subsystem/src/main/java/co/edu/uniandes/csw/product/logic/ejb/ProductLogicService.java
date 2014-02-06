
package co.edu.uniandes.csw.product.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.product.logic.api.IProductLogicService;

@Default
@Stateless
@LocalBean
public class ProductLogicService extends _ProductLogicService implements IProductLogicService {

}