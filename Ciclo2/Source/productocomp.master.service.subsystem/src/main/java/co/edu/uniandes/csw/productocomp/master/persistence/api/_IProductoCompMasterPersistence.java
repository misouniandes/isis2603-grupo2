package co.edu.uniandes.csw.productocomp.master.persistence.api;

import co.edu.uniandes.csw.productocomp.master.persistence.entity.ProductoCompNecesidadEntity;
import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;
import java.util.List;

public interface _IProductoCompMasterPersistence {

    public ProductoCompNecesidadEntity createProductoCompNecesidad(ProductoCompNecesidadEntity entity);

    public void deleteProductoCompNecesidad(Long productocompId, Long necesidadId);
    
    public List<NecesidadDTO> getNecesidadListForProductoComp(Long productocompId);
    
    public ProductoCompMasterDTO getProductoComp(Long productocompId);

}
