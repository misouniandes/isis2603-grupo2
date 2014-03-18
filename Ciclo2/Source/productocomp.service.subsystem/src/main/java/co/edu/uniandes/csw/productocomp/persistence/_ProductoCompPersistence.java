
package co.edu.uniandes.csw.productocomp.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.persistence.api._IProductoCompPersistence;
import co.edu.uniandes.csw.productocomp.persistence.converter.ProductoCompConverter;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;

public abstract class _ProductoCompPersistence implements _IProductoCompPersistence {

	@PersistenceContext(unitName="ProductoCompPU")
	protected EntityManager entityManager;
	
	public ProductoCompDTO createProductoComp(ProductoCompDTO productoComp) {
		ProductoCompEntity entity=ProductoCompConverter.persistenceDTO2Entity(productoComp);
		entityManager.persist(entity);
		return ProductoCompConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ProductoCompDTO> getProductoComps() {
		Query q = entityManager.createQuery("select u from ProductoCompEntity u");
		return ProductoCompConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ProductoCompDTO getProductoComp(Long id) {
		return ProductoCompConverter.entity2PersistenceDTO(entityManager.find(ProductoCompEntity.class, id));
	}

	public void deleteProductoComp(Long id) {
		ProductoCompEntity entity=entityManager.find(ProductoCompEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateProductoComp(ProductoCompDTO detail) {
		ProductoCompEntity entity=entityManager.merge(ProductoCompConverter.persistenceDTO2Entity(detail));
		ProductoCompConverter.entity2PersistenceDTO(entity);
	}

}