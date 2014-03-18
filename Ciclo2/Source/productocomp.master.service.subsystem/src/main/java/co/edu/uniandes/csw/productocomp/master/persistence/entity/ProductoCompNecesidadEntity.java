package co.edu.uniandes.csw.productocomp.master.persistence.entity;

import co.edu.uniandes.csw.necesidad.persistence.entity.NecesidadEntity;
import co.edu.uniandes.csw.productocomp.persistence.entity.ProductoCompEntity;
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
@IdClass(ProductoCompNecesidadEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ProductoCompNecesidadEntity.getNecesidadListForProductoComp", query = "SELECT  u FROM ProductoCompNecesidadEntity u WHERE u.productocompId=:productocompId"),
    @NamedQuery(name = "ProductoCompNecesidadEntity.deleteProductoCompNecesidad", query = "DELETE FROM ProductoCompNecesidadEntity u WHERE u.necesidadId=:necesidadId and  u.productocompId=:productocompId")
})
public class ProductoCompNecesidadEntity implements Serializable {

    @Id
    @Column(name = "productocompId")
    private Long productocompId;
    @Id
    @Column(name = "necesidadId")
    private Long necesidadId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "necesidadId", referencedColumnName = "id")
    @JoinFetch
    private NecesidadEntity necesidadEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productocompId", referencedColumnName = "id")
    @JoinFetch
    private ProductoCompEntity productocompEntity;

    public ProductoCompNecesidadEntity() {
    }

    public ProductoCompNecesidadEntity(Long productocompId, Long necesidadId) {
        this.productocompId = productocompId;
        this.necesidadId = necesidadId;
    }

    public Long getProductoCompId() {
        return productocompId;
    }

    public void setProductoCompId(Long productocompId) {
        this.productocompId = productocompId;
    }

    public Long getNecesidadId() {
        return necesidadId;
    }

    public void setNecesidadId(Long necesidadId) {
        this.necesidadId = necesidadId;
    }

    public NecesidadEntity getNecesidadEntity() {
        return necesidadEntity;
    }

    public void setNecesidadEntity(NecesidadEntity necesidadEntity) {
        this.necesidadEntity = necesidadEntity;
    }

    public ProductoCompEntity getProductoCompEntity() {
        return productocompEntity;
    }

    public void setProductoCompEntity(ProductoCompEntity productocompEntity) {
        this.productocompEntity = productocompEntity;
    }

}
