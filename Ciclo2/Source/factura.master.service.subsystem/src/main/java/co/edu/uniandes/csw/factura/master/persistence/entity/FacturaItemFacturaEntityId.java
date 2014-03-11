package co.edu.uniandes.csw.factura.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class FacturaItemFacturaEntityId implements Serializable{

    private Long facturaId;
    private Long itemFacturaId;

    @Override
    public int hashCode() {
        return (int) (facturaId + itemFacturaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FacturaItemFacturaEntityId) {
            FacturaItemFacturaEntityId otherId = (FacturaItemFacturaEntityId) object;
            return (otherId.facturaId == this.facturaId) && (otherId.itemFacturaId == this.itemFacturaId);
        }
        return false;
    }

}
