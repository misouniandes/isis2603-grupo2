package co.edu.uniandes.csw.producto.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ProductoDocumentoEntityId implements Serializable{

    private Long productoId;
    private Long documentoId;

    @Override
    public int hashCode() {
        return (int) (productoId + documentoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ProductoDocumentoEntityId) {
            ProductoDocumentoEntityId otherId = (ProductoDocumentoEntityId) object;
            return (otherId.productoId == this.productoId) && (otherId.documentoId == this.documentoId);
        }
        return false;
    }

}
