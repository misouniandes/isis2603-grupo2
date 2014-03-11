package co.edu.uniandes.csw.producto.master.persistence.entity;

import co.edu.uniandes.csw.documento.persistence.entity.DocumentoEntity;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
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
@IdClass(ProductoDocumentoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ProductoDocumentoEntity.getDocumentoListForProducto", query = "SELECT  u FROM ProductoDocumentoEntity u WHERE u.productoId=:productoId"),
    @NamedQuery(name = "ProductoDocumentoEntity.deleteProductoDocumento", query = "DELETE FROM ProductoDocumentoEntity u WHERE u.documentoId=:documentoId and  u.productoId=:productoId")
})
public class ProductoDocumentoEntity implements Serializable {

    @Id
    @Column(name = "productoId")
    private Long productoId;
    @Id
    @Column(name = "documentoId")
    private Long documentoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "documentoId", referencedColumnName = "id")
    @JoinFetch
    private DocumentoEntity documentoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;

    public ProductoDocumentoEntity() {
    }

    public ProductoDocumentoEntity(Long productoId, Long documentoId) {
        this.productoId = productoId;
        this.documentoId = documentoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }

    public DocumentoEntity getDocumentoEntity() {
        return documentoEntity;
    }

    public void setDocumentoEntity(DocumentoEntity documentoEntity) {
        this.documentoEntity = documentoEntity;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

}
