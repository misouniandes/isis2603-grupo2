
package co.edu.uniandes.csw.product.logic.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ProductDTO extends _ProductDTO {
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