
package co.edu.uniandes.csw.itemfactura.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.itemfactura.logic.api.IItemFacturaLogicService;

@Alternative
@Singleton
public class ItemFacturaMockLogicService extends _ItemFacturaMockLogicService implements IItemFacturaLogicService {
	
}