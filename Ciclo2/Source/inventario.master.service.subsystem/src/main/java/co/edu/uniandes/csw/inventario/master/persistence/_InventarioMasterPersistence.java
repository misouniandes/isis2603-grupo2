package co.edu.uniandes.csw.inventario.master.persistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.inventario.master.persistence.entity.InventarioProductoEntity;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.inventario.logic.dto.InventarioDTO;
import co.edu.uniandes.csw.inventario.master.logic.dto.InventarioMasterDTO;
import co.edu.uniandes.csw.inventario.master.persistence.api._IInventarioMasterPersistence;
import co.edu.uniandes.csw.inventario.persistence.api.IInventarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _InventarioMasterPersistence implements _IInventarioMasterPersistence {

    @PersistenceContext(unitName = "InventarioMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IInventarioPersistence inventarioPersistence;  

    public InventarioMasterDTO getInventario(Long inventarioId) {
        InventarioMasterDTO inventarioMasterDTO = new InventarioMasterDTO();
        InventarioDTO inventario = inventarioPersistence.getInventario(inventarioId);
        inventarioMasterDTO.setInventarioEntity(inventario);
        inventarioMasterDTO.setListFactura(getFacturaListForInventario(inventarioId));
        inventarioMasterDTO.setListProducto(getProductoListForInventario(inventarioId));
        return inventarioMasterDTO;
    }

    public InventarioFacturaEntity createInventarioFactura(InventarioFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteInventarioFactura(Long inventarioId, Long facturaId) {
        Query q = entityManager.createNamedQuery("InventarioFacturaEntity.deleteInventarioFactura");
        q.setParameter("inventarioId", inventarioId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForInventario(Long inventarioId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("InventarioFacturaEntity.getFacturaListForInventario");
        q.setParameter("inventarioId", inventarioId);
        List<InventarioFacturaEntity> qResult =  q.getResultList();
        for (InventarioFacturaEntity inventarioFacturaEntity : qResult) { 
            if(inventarioFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(inventarioFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(inventarioFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }
    public InventarioProductoEntity createInventarioProducto(InventarioProductoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteInventarioProducto(Long inventarioId, Long productoId) {
        Query q = entityManager.createNamedQuery("InventarioProductoEntity.deleteInventarioProducto");
        q.setParameter("inventarioId", inventarioId);
        q.setParameter("productoId", productoId);
        q.executeUpdate();
    }

    public List<ProductoDTO> getProductoListForInventario(Long inventarioId) {
        ArrayList<ProductoDTO> resp = new ArrayList<ProductoDTO>();
        Query q = entityManager.createNamedQuery("InventarioProductoEntity.getProductoListForInventario");
        q.setParameter("inventarioId", inventarioId);
        List<InventarioProductoEntity> qResult =  q.getResultList();
        for (InventarioProductoEntity inventarioProductoEntity : qResult) { 
            if(inventarioProductoEntity.getProductoEntity()==null){
                entityManager.refresh(inventarioProductoEntity);
            }
            resp.add(ProductoConverter.entity2PersistenceDTO(inventarioProductoEntity.getProductoEntity()));
        }
        return resp;
    }

}
