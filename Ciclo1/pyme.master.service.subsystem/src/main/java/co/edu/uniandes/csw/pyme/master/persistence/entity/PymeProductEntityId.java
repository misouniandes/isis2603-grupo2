package co.edu.uniandes.csw.pyme.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PymeProductEntityId implements Serializable{

    private Long pymeId;
    private Long productId;

    @Override
    public int hashCode() {
        return (int) (pymeId + productId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PymeProductEntityId) {
            PymeProductEntityId otherId = (PymeProductEntityId) object;
            return (otherId.pymeId == this.pymeId) && (otherId.productId == this.productId);
        }
        return false;
    }

}
