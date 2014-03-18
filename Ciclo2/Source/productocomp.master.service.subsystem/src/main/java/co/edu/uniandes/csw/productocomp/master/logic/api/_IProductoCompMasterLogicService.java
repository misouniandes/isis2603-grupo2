 
package co.edu.uniandes.csw.productocomp.master.logic.api;

import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;

public interface _IProductoCompMasterLogicService {

	public ProductoCompMasterDTO createMasterProductoComp(ProductoCompMasterDTO detail);
    public void updateMasterProductoComp(ProductoCompMasterDTO detail);
	public void deleteMasterProductoComp(Long id); 
	public ProductoCompMasterDTO getMasterProductoComp(Long id);
        
}