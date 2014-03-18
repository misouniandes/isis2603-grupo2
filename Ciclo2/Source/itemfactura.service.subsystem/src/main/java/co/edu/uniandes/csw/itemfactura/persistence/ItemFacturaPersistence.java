
package co.edu.uniandes.csw.itemfactura.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.itemfactura.persistence.api.IItemFacturaPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ItemFacturaPersistence extends _ItemFacturaPersistence  implements IItemFacturaPersistence {

}