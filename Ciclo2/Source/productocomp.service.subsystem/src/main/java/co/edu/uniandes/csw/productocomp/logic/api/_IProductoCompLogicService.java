
package co.edu.uniandes.csw.productocomp.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;

public interface _IProductoCompLogicService {

	public ProductoCompDTO createProductoComp(ProductoCompDTO detail);
	public List<ProductoCompDTO> getProductoComps();
	public ProductoCompDTO getProductoComp(Long id);
	public void deleteProductoComp(Long id);
	public void updateProductoComp(ProductoCompDTO detail);
	
}