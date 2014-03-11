
package co.edu.uniandes.csw.necesidad.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _NecesidadEntity {

	private Integer cantidad;
	@Id
	@GeneratedValue(generator = "Necesidad")
	private Long id;
	private String name;
	private Long productoId;

	public Integer getCantidad(){
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Long getProductoId(){
		return productoId;
	}
	
	public void setProductoId(Long productoId){
		this.productoId = productoId;
	}
}