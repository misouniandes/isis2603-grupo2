package co.edu.uniandes.csw.inventario.master.persistence.entity;

import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.inventario.persistence.entity.InventarioEntity;
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
@IdClass(InventarioFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "InventarioFacturaEntity.getFacturaListForInventario", query = "SELECT  u FROM InventarioFacturaEntity u WHERE u.inventarioId=:inventarioId"),
    @NamedQuery(name = "InventarioFacturaEntity.deleteInventarioFactura", query = "DELETE FROM InventarioFacturaEntity u WHERE u.facturaId=:facturaId and  u.inventarioId=:inventarioId")
})
public class InventarioFacturaEntity implements Serializable {

    @Id
    @Column(name = "inventarioId")
    private Long inventarioId;
    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "inventarioId", referencedColumnName = "id")
    @JoinFetch
    private InventarioEntity inventarioEntity;

    public InventarioFacturaEntity() {
    }

    public InventarioFacturaEntity(Long inventarioId, Long facturaId) {
        this.inventarioId = inventarioId;
        this.facturaId = facturaId;
    }

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

    public InventarioEntity getInventarioEntity() {
        return inventarioEntity;
    }

    public void setInventarioEntity(InventarioEntity inventarioEntity) {
        this.inventarioEntity = inventarioEntity;
    }

}
