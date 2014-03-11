package co.edu.uniandes.csw.inventario.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class InventarioProductoEntityId implements Serializable{

    private Long inventarioId;
    private Long productoId;

    @Override
    public int hashCode() {
        return (int) (inventarioId + productoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof InventarioProductoEntityId) {
            InventarioProductoEntityId otherId = (InventarioProductoEntityId) object;
            return (otherId.inventarioId == this.inventarioId) && (otherId.productoId == this.productoId);
        }
        return false;
    }

}
