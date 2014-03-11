package co.edu.uniandes.csw.inventario.master.persistence.entity;

import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
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
@IdClass(InventarioProductoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "InventarioProductoEntity.getProductoListForInventario", query = "SELECT  u FROM InventarioProductoEntity u WHERE u.inventarioId=:inventarioId"),
    @NamedQuery(name = "InventarioProductoEntity.deleteInventarioProducto", query = "DELETE FROM InventarioProductoEntity u WHERE u.productoId=:productoId and  u.inventarioId=:inventarioId")
})
public class InventarioProductoEntity implements Serializable {

    @Id
    @Column(name = "inventarioId")
    private Long inventarioId;
    @Id
    @Column(name = "productoId")
    private Long productoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "inventarioId", referencedColumnName = "id")
    @JoinFetch
    private InventarioEntity inventarioEntity;

    public InventarioProductoEntity() {
    }

    public InventarioProductoEntity(Long inventarioId, Long productoId) {
        this.inventarioId = inventarioId;
        this.productoId = productoId;
    }

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

    public InventarioEntity getInventarioEntity() {
        return inventarioEntity;
    }

    public void setInventarioEntity(InventarioEntity inventarioEntity) {
        this.inventarioEntity = inventarioEntity;
    }

}
