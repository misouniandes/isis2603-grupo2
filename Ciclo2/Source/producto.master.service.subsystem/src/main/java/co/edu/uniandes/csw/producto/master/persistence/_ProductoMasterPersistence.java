package co.edu.uniandes.csw.producto.master.persistence;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.persistence.converter.DocumentoConverter;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.persistence.converter.DocumentoConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.master.persistence.api._IProductoMasterPersistence;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ProductoMasterPersistence implements _IProductoMasterPersistence {

    @PersistenceContext(unitName = "ProductoMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IProductoPersistence productoPersistence;  

    public ProductoMasterDTO getProducto(Long productoId) {
        ProductoMasterDTO productoMasterDTO = new ProductoMasterDTO();
        ProductoDTO producto = productoPersistence.getProducto(productoId);
        productoMasterDTO.setProductoEntity(producto);
        productoMasterDTO.setListDocumento(getDocumentoListForProducto(productoId));
        productoMasterDTO.setListDocumento(getDocumentoListForProducto(productoId));
        return productoMasterDTO;
    }

    public ProductoDocumentoEntity createProductoDocumento(ProductoDocumentoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteProductoDocumento(Long productoId, Long documentoId) {
        Query q = entityManager.createNamedQuery("ProductoDocumentoEntity.deleteProductoDocumento");
        q.setParameter("productoId", productoId);
        q.setParameter("documentoId", documentoId);
        q.executeUpdate();
    }

    public List<DocumentoDTO> getDocumentoListForProducto(Long productoId) {
        ArrayList<DocumentoDTO> resp = new ArrayList<DocumentoDTO>();
        Query q = entityManager.createNamedQuery("ProductoDocumentoEntity.getDocumentoListForProducto");
        q.setParameter("productoId", productoId);
        List<ProductoDocumentoEntity> qResult =  q.getResultList();
        for (ProductoDocumentoEntity productoDocumentoEntity : qResult) { 
            if(productoDocumentoEntity.getDocumentoEntity()==null){
                entityManager.refresh(productoDocumentoEntity);
            }
            resp.add(DocumentoConverter.entity2PersistenceDTO(productoDocumentoEntity.getDocumentoEntity()));
        }
        return resp;
    }
    public ProductoDocumentoEntity createProductoDocumento(ProductoDocumentoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteProductoDocumento(Long productoId, Long documentoId) {
        Query q = entityManager.createNamedQuery("ProductoDocumentoEntity.deleteProductoDocumento");
        q.setParameter("productoId", productoId);
        q.setParameter("documentoId", documentoId);
        q.executeUpdate();
    }

    public List<DocumentoDTO> getDocumentoListForProducto(Long productoId) {
        ArrayList<DocumentoDTO> resp = new ArrayList<DocumentoDTO>();
        Query q = entityManager.createNamedQuery("ProductoDocumentoEntity.getDocumentoListForProducto");
        q.setParameter("productoId", productoId);
        List<ProductoDocumentoEntity> qResult =  q.getResultList();
        for (ProductoDocumentoEntity productoDocumentoEntity : qResult) { 
            if(productoDocumentoEntity.getDocumentoEntity()==null){
                entityManager.refresh(productoDocumentoEntity);
            }
            resp.add(DocumentoConverter.entity2PersistenceDTO(productoDocumentoEntity.getDocumentoEntity()));
        }
        return resp;
    }

}
