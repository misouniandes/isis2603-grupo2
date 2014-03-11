package co.edu.uniandes.csw.productocomp.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ProductoCompNecesidadEntityId implements Serializable{

    private Long productocompId;
    private Long necesidadId;

    @Override
    public int hashCode() {
        return (int) (productocompId + necesidadId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ProductoCompNecesidadEntityId) {
            ProductoCompNecesidadEntityId otherId = (ProductoCompNecesidadEntityId) object;
            return (otherId.productocompId == this.productocompId) && (otherId.necesidadId == this.necesidadId);
        }
        return false;
    }

}
