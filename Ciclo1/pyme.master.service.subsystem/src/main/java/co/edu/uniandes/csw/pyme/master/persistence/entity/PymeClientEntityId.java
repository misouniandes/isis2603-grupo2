package co.edu.uniandes.csw.pyme.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PymeClientEntityId implements Serializable{

    private Long pymeId;
    private Long clientId;

    @Override
    public int hashCode() {
        return (int) (pymeId + clientId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PymeClientEntityId) {
            PymeClientEntityId otherId = (PymeClientEntityId) object;
            return (otherId.pymeId == this.pymeId) && (otherId.clientId == this.clientId);
        }
        return false;
    }

}
