package co.edu.uniandes.csw.pyme.master.persistence.entity;

import co.edu.uniandes.csw.product.persistence.entity.ProductEntity;
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
@IdClass(PymeProductEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PymeProductEntity.getProductListForPyme", query = "SELECT  u FROM PymeProductEntity u WHERE u.pymeId=:pymeId"),
    @NamedQuery(name = "PymeProductEntity.deletePymeProduct", query = "DELETE FROM PymeProductEntity u WHERE u.productId=:productId and  u.pymeId=:pymeId")
})
public class PymeProductEntity implements Serializable {

    @Id
    @Column(name = "pymeId")
    private Long pymeId;
    @Id
    @Column(name = "productId")
    private Long productId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productId", referencedColumnName = "id")
    @JoinFetch
    private ProductEntity productEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "pymeId", referencedColumnName = "id")
    @JoinFetch
    private PymeEntity pymeEntity;

    public PymeProductEntity() {
    }

    public PymeProductEntity(Long pymeId, Long productId) {
        this.pymeId = pymeId;
        this.productId = productId;
    }

    public Long getPymeId() {
        return pymeId;
    }

    public void setPymeId(Long pymeId) {
        this.pymeId = pymeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public PymeEntity getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeEntity pymeEntity) {
        this.pymeEntity = pymeEntity;
    }

}
