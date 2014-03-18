package co.edu.uniandes.csw.productocomp.master.logic.ejb;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.necesidad.persistence.api.INecesidadPersistence;
import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.master.logic.api._IProductoCompMasterLogicService;
import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;
import co.edu.uniandes.csw.productocomp.master.persistence.api.IProductoCompMasterPersistence;
import co.edu.uniandes.csw.productocomp.master.persistence.entity.ProductoCompNecesidadEntity;
import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;
import javax.inject.Inject;

public abstract class _ProductoCompMasterLogicService implements _IProductoCompMasterLogicService {

    @Inject
    protected IProductoCompPersistence productocompPersistance;
    @Inject
    protected IProductoCompMasterPersistence productocompMasterPersistance;
    @Inject
    protected INecesidadPersistence necesidadPersistance;

    public ProductoCompMasterDTO createMasterProductoComp(ProductoCompMasterDTO productocomp) {
        ProductoCompDTO persistedProductoCompDTO = productocompPersistance.createProductoComp(productocomp.getProductoCompEntity());
        if (productocomp.getCreateNecesidad() != null) {
            for (NecesidadDTO necesidadDTO : productocomp.getCreateNecesidad()) {
                NecesidadDTO persistedNecesidadDTO = necesidadPersistance.createNecesidad(necesidadDTO);
                ProductoCompNecesidadEntity productocompNecesidadEntity = new ProductoCompNecesidadEntity(persistedProductoCompDTO.getId(), persistedNecesidadDTO.getId());
                productocompMasterPersistance.createProductoCompNecesidad(productocompNecesidadEntity);
            }
        }
        return productocomp;
    }

    public ProductoCompMasterDTO getMasterProductoComp(Long id) {
        return productocompMasterPersistance.getProductoComp(id);
    }

    public void deleteMasterProductoComp(Long id) {
        productocompPersistance.deleteProductoComp(id);
    }

    public void updateMasterProductoComp(ProductoCompMasterDTO productocomp) {
        productocompPersistance.updateProductoComp(productocomp.getProductoCompEntity());

        //---- FOR RELATIONSHIP
        // persist new necesidad
        if (productocomp.getCreateNecesidad() != null) {
            for (NecesidadDTO necesidadDTO : productocomp.getCreateNecesidad()) {
                NecesidadDTO persistedNecesidadDTO = necesidadPersistance.createNecesidad(necesidadDTO);
                ProductoCompNecesidadEntity productocompNecesidadEntity = new ProductoCompNecesidadEntity(productocomp.getProductoCompEntity().getId(), persistedNecesidadDTO.getId());
                productocompMasterPersistance.createProductoCompNecesidad(productocompNecesidadEntity);
            }
        }
        // update necesidad
        if (productocomp.getUpdateNecesidad() != null) {
            for (NecesidadDTO necesidadDTO : productocomp.getUpdateNecesidad()) {
                necesidadPersistance.updateNecesidad(necesidadDTO);
            }
        }
        // delete necesidad
        if (productocomp.getDeleteNecesidad() != null) {
            for (NecesidadDTO necesidadDTO : productocomp.getDeleteNecesidad()) {
                productocompMasterPersistance.deleteProductoCompNecesidad(productocomp.getProductoCompEntity().getId(), necesidadDTO.getId());
                necesidadPersistance.deleteNecesidad(necesidadDTO.getId());
            }
        }
    }
}
