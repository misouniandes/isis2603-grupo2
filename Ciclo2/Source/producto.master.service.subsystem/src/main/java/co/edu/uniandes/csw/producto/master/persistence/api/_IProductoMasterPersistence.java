package co.edu.uniandes.csw.producto.master.persistence.api;

import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import java.util.List;

public interface _IProductoMasterPersistence {

    public ProductoDocumentoEntity createProductoDocumento(ProductoDocumentoEntity entity);

    public void deleteProductoDocumento(Long productoId, Long documentoId);
    
    public List<DocumentoDTO> getDocumentoListForProducto(Long productoId);
    public ProductoDocumentoEntity createProductoDocumento(ProductoDocumentoEntity entity);

    public void deleteProductoDocumento(Long productoId, Long documentoId);
    
    public List<DocumentoDTO> getDocumentoListForProducto(Long productoId);
    
    public ProductoMasterDTO getProducto(Long productoId);

}
