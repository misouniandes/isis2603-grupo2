
package co.edu.uniandes.csw.product.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.product.logic.dto.ProductDTO;

public interface _IProductPersistence {

	public ProductDTO createProduct(ProductDTO detail);
	public List<ProductDTO> getProducts();
	public ProductDTO getProduct(Long id);
	public void deleteProduct(Long id);
	public void updateProduct(ProductDTO detail);
	
}