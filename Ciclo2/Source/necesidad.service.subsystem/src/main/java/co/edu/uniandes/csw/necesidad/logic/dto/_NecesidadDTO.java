
package co.edu.uniandes.csw.necesidad.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _NecesidadDTO {

	private Integer cantidad;
	private Long id;
	private String name;
	private Long productoId;

	public Integer getCantidad() {
		return cantidad;
	}
 
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Long getProductoId() {
		return productoId;
	}
 
	public void setProductoId(Long productoid) {
		this.productoId = productoid;
	}
	
}