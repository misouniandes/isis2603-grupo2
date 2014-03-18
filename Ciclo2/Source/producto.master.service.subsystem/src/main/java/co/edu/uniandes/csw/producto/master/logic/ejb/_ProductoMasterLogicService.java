package co.edu.uniandes.csw.producto.master.logic.ejb;

import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.api.IDocumentoPersistence;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.api.IDocumentoPersistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.api._IProductoMasterLogicService;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.master.persistence.api.IProductoMasterPersistence;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import javax.inject.Inject;

public abstract class _ProductoMasterLogicService implements _IProductoMasterLogicService {

    @Inject
    protected IProductoPersistence productoPersistance;
    @Inject
    protected IProductoMasterPersistence productoMasterPersistance;
    @Inject
    protected IDocumentoPersistence documentoPersistance;
    @Inject
    protected IDocumentoPersistence documentoPersistance;

    public ProductoMasterDTO createMasterProducto(ProductoMasterDTO producto) {
        ProductoDTO persistedProductoDTO = productoPersistance.createProducto(producto.getProductoEntity());
        if (producto.getCreateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getCreateDocumento()) {
                DocumentoDTO persistedDocumentoDTO = documentoPersistance.createDocumento(documentoDTO);
                ProductoDocumentoEntity productoDocumentoEntity = new ProductoDocumentoEntity(persistedProductoDTO.getId(), persistedDocumentoDTO.getId());
                productoMasterPersistance.createProductoDocumento(productoDocumentoEntity);
            }
        }
        if (producto.getCreateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getCreateDocumento()) {
                DocumentoDTO persistedDocumentoDTO = documentoPersistance.createDocumento(documentoDTO);
                ProductoDocumentoEntity productoDocumentoEntity = new ProductoDocumentoEntity(persistedProductoDTO.getId(), persistedDocumentoDTO.getId());
                productoMasterPersistance.createProductoDocumento(productoDocumentoEntity);
            }
        }
        return producto;
    }

    public ProductoMasterDTO getMasterProducto(Long id) {
        return productoMasterPersistance.getProducto(id);
    }

    public void deleteMasterProducto(Long id) {
        productoPersistance.deleteProducto(id);
    }

    public void updateMasterProducto(ProductoMasterDTO producto) {
        productoPersistance.updateProducto(producto.getProductoEntity());

        //---- FOR RELATIONSHIP
        // persist new documento
        if (producto.getCreateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getCreateDocumento()) {
                DocumentoDTO persistedDocumentoDTO = documentoPersistance.createDocumento(documentoDTO);
                ProductoDocumentoEntity productoDocumentoEntity = new ProductoDocumentoEntity(producto.getProductoEntity().getId(), persistedDocumentoDTO.getId());
                productoMasterPersistance.createProductoDocumento(productoDocumentoEntity);
            }
        }
        // update documento
        if (producto.getUpdateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getUpdateDocumento()) {
                documentoPersistance.updateDocumento(documentoDTO);
            }
        }
        // delete documento
        if (producto.getDeleteDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getDeleteDocumento()) {
                productoMasterPersistance.deleteProductoDocumento(producto.getProductoEntity().getId(), documentoDTO.getId());
                documentoPersistance.deleteDocumento(documentoDTO.getId());
            }
        }
        // persist new documento
        if (producto.getCreateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getCreateDocumento()) {
                DocumentoDTO persistedDocumentoDTO = documentoPersistance.createDocumento(documentoDTO);
                ProductoDocumentoEntity productoDocumentoEntity = new ProductoDocumentoEntity(producto.getProductoEntity().getId(), persistedDocumentoDTO.getId());
                productoMasterPersistance.createProductoDocumento(productoDocumentoEntity);
            }
        }
        // update documento
        if (producto.getUpdateDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getUpdateDocumento()) {
                documentoPersistance.updateDocumento(documentoDTO);
            }
        }
        // delete documento
        if (producto.getDeleteDocumento() != null) {
            for (DocumentoDTO documentoDTO : producto.getDeleteDocumento()) {
                productoMasterPersistance.deleteProductoDocumento(producto.getProductoEntity().getId(), documentoDTO.getId());
                documentoPersistance.deleteDocumento(documentoDTO.getId());
            }
        }
    }
}
