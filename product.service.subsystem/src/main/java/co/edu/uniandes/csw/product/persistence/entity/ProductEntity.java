
package co.edu.uniandes.csw.product.persistence.entity;

import javax.persistence.Entity;

@Entity
public class ProductEntity extends _ProductEntity {
    private String imagen, descripcion;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
         
}