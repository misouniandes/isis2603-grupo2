
package co.edu.uniandes.csw.itemfactura.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.itemfactura.logic.api.IItemFacturaLogicService;

@Default
@Stateless
@LocalBean
public class ItemFacturaLogicService extends _ItemFacturaLogicService implements IItemFacturaLogicService {

}