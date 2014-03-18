package co.edu.uniandes.csw.producto.master.persistence.converter;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.converter.DocumentoConverter;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoDocumentoEntity;
import co.edu.uniandes.csw.documento.logic.dto.DocumentoDTO;
import co.edu.uniandes.csw.documento.persistence.converter.DocumentoConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ProductoMasterConverter {

    public static ProductoMasterDTO entity2PersistenceDTO(ProductoEntity productoEntity 
    ,List<ProductoDocumentoEntity> productoDocumentoEntity 
    ,List<ProductoDocumentoEntity> productoDocumentoEntity 
    ) {
        ProductoDTO productoDTO = ProductoConverter.entity2PersistenceDTO(productoEntity);
        ArrayList<DocumentoDTO> documentoEntities = new ArrayList<DocumentoDTO>(productoDocumentoEntity.size());
        for (ProductoDocumentoEntity productoDocumento : productoDocumentoEntity) {
            documentoEntities.add(DocumentoConverter.entity2PersistenceDTO(productoDocumento.getDocumentoEntity()));
        }
        ArrayList<DocumentoDTO> documentoEntities = new ArrayList<DocumentoDTO>(productoDocumentoEntity.size());
        for (ProductoDocumentoEntity productoDocumento : productoDocumentoEntity) {
            documentoEntities.add(DocumentoConverter.entity2PersistenceDTO(productoDocumento.getDocumentoEntity()));
        }
        ProductoMasterDTO respDTO  = new ProductoMasterDTO();
        respDTO.setProductoEntity(productoDTO);
        respDTO.setListDocumento(documentoEntities);
        respDTO.setListDocumento(documentoEntities);
        return respDTO;
    }

}