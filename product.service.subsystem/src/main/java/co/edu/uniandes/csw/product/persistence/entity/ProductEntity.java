
package co.edu.uniandes.csw.product.persistence.entity;

import javax.persistence.Entity;

@Entity
public class ProductEntity extends _ProductEntity {
 	private String URLImagen, descripcion;

    public String getURLImagen() {
        return URLImagen;
    }

    public void setURLImagen(String URLImagen) {
        this.URLImagen = URLImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
         
}