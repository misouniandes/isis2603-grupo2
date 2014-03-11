
package co.edu.uniandes.csw.itemfactura.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.persistence.api._IItemFacturaPersistence;
import co.edu.uniandes.csw.itemfactura.persistence.converter.ItemFacturaConverter;
import co.edu.uniandes.csw.itemfactura.persistence.entity.ItemFacturaEntity;

public abstract class _ItemFacturaPersistence implements _IItemFacturaPersistence {

	@PersistenceContext(unitName="ItemFacturaPU")
	protected EntityManager entityManager;
	
	public ItemFacturaDTO createItemFactura(ItemFacturaDTO itemFactura) {
		ItemFacturaEntity entity=ItemFacturaConverter.persistenceDTO2Entity(itemFactura);
		entityManager.persist(entity);
		return ItemFacturaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ItemFacturaDTO> getItemFacturas() {
		Query q = entityManager.createQuery("select u from ItemFacturaEntity u");
		return ItemFacturaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ItemFacturaDTO getItemFactura(Long id) {
		return ItemFacturaConverter.entity2PersistenceDTO(entityManager.find(ItemFacturaEntity.class, id));
	}

	public void deleteItemFactura(Long id) {
		ItemFacturaEntity entity=entityManager.find(ItemFacturaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateItemFactura(ItemFacturaDTO detail) {
		ItemFacturaEntity entity=entityManager.merge(ItemFacturaConverter.persistenceDTO2Entity(detail));
		ItemFacturaConverter.entity2PersistenceDTO(entity);
	}

}