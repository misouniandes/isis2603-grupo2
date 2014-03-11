package co.edu.uniandes.csw.productocomp.master.persistence;
import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.productocomp.master.persistence.entity.ProductoCompNecesidadEntity;
import co.edu.uniandes.csw.necesidad.persistence.converter.NecesidadConverter;
import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;
import co.edu.uniandes.csw.productocomp.master.logic.dto.ProductoCompMasterDTO;
import co.edu.uniandes.csw.productocomp.master.persistence.api._IProductoCompMasterPersistence;
import co.edu.uniandes.csw.productocomp.persistence.api.IProductoCompPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ProductoCompMasterPersistence implements _IProductoCompMasterPersistence {

    @PersistenceContext(unitName = "ProductoCompMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IProductoCompPersistence productocompPersistence;  

    public ProductoCompMasterDTO getProductoComp(Long productocompId) {
        ProductoCompMasterDTO productocompMasterDTO = new ProductoCompMasterDTO();
        ProductoCompDTO productocomp = productocompPersistence.getProductoComp(productocompId);
        productocompMasterDTO.setProductoCompEntity(productocomp);
        productocompMasterDTO.setListNecesidad(getNecesidadListForProductoComp(productocompId));
        return productocompMasterDTO;
    }

    public ProductoCompNecesidadEntity createProductoCompNecesidad(ProductoCompNecesidadEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteProductoCompNecesidad(Long productocompId, Long necesidadId) {
        Query q = entityManager.createNamedQuery("ProductoCompNecesidadEntity.deleteProductoCompNecesidad");
        q.setParameter("productocompId", productocompId);
        q.setParameter("necesidadId", necesidadId);
        q.executeUpdate();
    }

    public List<NecesidadDTO> getNecesidadListForProductoComp(Long productocompId) {
        ArrayList<NecesidadDTO> resp = new ArrayList<NecesidadDTO>();
        Query q = entityManager.createNamedQuery("ProductoCompNecesidadEntity.getNecesidadListForProductoComp");
        q.setParameter("productocompId", productocompId);
        List<ProductoCompNecesidadEntity> qResult =  q.getResultList();
        for (ProductoCompNecesidadEntity productocompNecesidadEntity : qResult) { 
            if(productocompNecesidadEntity.getNecesidadEntity()==null){
                entityManager.refresh(productocompNecesidadEntity);
            }
            resp.add(NecesidadConverter.entity2PersistenceDTO(productocompNecesidadEntity.getNecesidadEntity()));
        }
        return resp;
    }

}
