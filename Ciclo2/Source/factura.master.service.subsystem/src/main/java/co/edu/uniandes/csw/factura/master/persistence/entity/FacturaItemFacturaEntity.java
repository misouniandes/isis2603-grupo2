package co.edu.uniandes.csw.factura.master.persistence.entity;

import co.edu.uniandes.csw.itemfactura.persistence.entity.ItemFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
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
@IdClass(FacturaItemFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "FacturaItemFacturaEntity.getItemFacturaListForFactura", query = "SELECT  u FROM FacturaItemFacturaEntity u WHERE u.facturaId=:facturaId"),
    @NamedQuery(name = "FacturaItemFacturaEntity.deleteFacturaItemFactura", query = "DELETE FROM FacturaItemFacturaEntity u WHERE u.itemFacturaId=:itemFacturaId and  u.facturaId=:facturaId")
})
public class FacturaItemFacturaEntity implements Serializable {

    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @Id
    @Column(name = "itemFacturaId")
    private Long itemFacturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemFacturaId", referencedColumnName = "id")
    @JoinFetch
    private ItemFacturaEntity itemFacturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;

    public FacturaItemFacturaEntity() {
    }

    public FacturaItemFacturaEntity(Long facturaId, Long itemFacturaId) {
        this.facturaId = facturaId;
        this.itemFacturaId = itemFacturaId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Long getItemFacturaId() {
        return itemFacturaId;
    }

    public void setItemFacturaId(Long itemFacturaId) {
        this.itemFacturaId = itemFacturaId;
    }

    public ItemFacturaEntity getItemFacturaEntity() {
        return itemFacturaEntity;
    }

    public void setItemFacturaEntity(ItemFacturaEntity itemFacturaEntity) {
        this.itemFacturaEntity = itemFacturaEntity;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

}
