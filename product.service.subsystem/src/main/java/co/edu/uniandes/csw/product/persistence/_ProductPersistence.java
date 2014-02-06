
package co.edu.uniandes.csw.product.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.persistence.api._IProductPersistence;
import co.edu.uniandes.csw.product.persistence.converter.ProductConverter;
import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;

public abstract class _ProductPersistence implements _IProductPersistence {

	@PersistenceContext(unitName="ProductPU")
	protected EntityManager entityManager;
	
	public ProductDTO createProduct(ProductDTO product) {
		ProductEntity entity=ProductConverter.persistenceDTO2Entity(product);
		entityManager.persist(entity);
		return ProductConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ProductDTO> getProducts() {
		Query q = entityManager.createQuery("select u from ProductEntity u");
		return ProductConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ProductDTO getProduct(Long id) {
		return ProductConverter.entity2PersistenceDTO(entityManager.find(ProductEntity.class, id));
	}

	public void deleteProduct(Long id) {
		ProductEntity entity=entityManager.find(ProductEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateProduct(ProductDTO detail) {
		ProductEntity entity=entityManager.merge(ProductConverter.persistenceDTO2Entity(detail));
		ProductConverter.entity2PersistenceDTO(entity);
	}

}