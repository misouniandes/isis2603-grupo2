
package co.edu.uniandes.csw.product.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.logic.api._IProductLogicService;
import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;

public abstract class _ProductLogicService implements _IProductLogicService {

	@Inject
	protected IProductPersistence persistance;

	public ProductDTO createProduct(ProductDTO product){
		return persistance.createProduct( product); 
    }

	public List<ProductDTO> getProducts(){
		return persistance.getProducts(); 
	}

	public ProductDTO getProduct(Long id){
		return persistance.getProduct(id); 
	}

	public void deleteProduct(Long id){
	    persistance.deleteProduct(id); 
	}

	public void updateProduct(ProductDTO product){
	    persistance.updateProduct(product); 
	}	
}