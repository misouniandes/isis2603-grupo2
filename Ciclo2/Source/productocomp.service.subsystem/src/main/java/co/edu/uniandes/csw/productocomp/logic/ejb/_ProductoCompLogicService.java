
package co.edu.uniandes.csw.productocomp.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.logic.api._IProductoCompLogicService;
import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;

public abstract class _ProductoCompLogicService implements _IProductoCompLogicService {

	@Inject
	protected IProductoCompPersistence persistance;

	public ProductoCompDTO createProductoComp(ProductoCompDTO productoComp){
		return persistance.createProductoComp( productoComp); 
    }

	public List<ProductoCompDTO> getProductoComps(){
		return persistance.getProductoComps(); 
	}

	public ProductoCompDTO getProductoComp(Long id){
		return persistance.getProductoComp(id); 
	}

	public void deleteProductoComp(Long id){
	    persistance.deleteProductoComp(id); 
	}

	public void updateProductoComp(ProductoCompDTO productoComp){
	    persistance.updateProductoComp(productoComp); 
	}	
}