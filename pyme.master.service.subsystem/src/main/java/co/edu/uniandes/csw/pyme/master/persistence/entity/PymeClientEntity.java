package co.edu.uniandes.csw.pyme.master.persistence.entity;

import co.edu.uniandes.csw.client.persistence.entity.ClientEntity;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(PymeClientEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PymeClientEntity.getClientListForPyme", query = "SELECT  u FROM PymeClientEntity u WHERE u.pymeId=:pymeId"),
    @NamedQuery(name = "PymeClientEntity.deletePymeClient", query = "DELETE FROM PymeClientEntity u WHERE u.clientId=:clientId and  u.pymeId=:pymeId")
})
public class PymeClientEntity implements Serializable {

    @Id
    @Column(name = "pymeId")
    private Long pymeId;
    @Id
    @Column(name = "clientId")
    private Long clientId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "clientId", referencedColumnName = "id")
    @JoinFetch
    private ClientEntity clientEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "pymeId", referencedColumnName = "id")
    @JoinFetch
    private PymeEntity pymeEntity;

    public PymeClientEntity() {
    }

    public PymeClientEntity(Long pymeId, Long clientId) {
        this.pymeId = pymeId;
        this.clientId = clientId;
    }

    public Long getPymeId() {
        return pymeId;
    }

    public void setPymeId(Long pymeId) {
        this.pymeId = pymeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public PymeEntity getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeEntity pymeEntity) {
        this.pymeEntity = pymeEntity;
    }

}
