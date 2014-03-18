package co.edu.uniandes.csw.inventario.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class InventarioFacturaEntityId implements Serializable{

    private Long inventarioId;
    private Long facturaId;

    @Override
    public int hashCode() {
        return (int) (inventarioId + facturaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof InventarioFacturaEntityId) {
            InventarioFacturaEntityId otherId = (InventarioFacturaEntityId) object;
            return (otherId.inventarioId == this.inventarioId) && (otherId.facturaId == this.facturaId);
        }
        return false;
    }

}
