
package co.edu.uniandes.csw.item.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ItemEntity {

	@Id
	@GeneratedValue(generator = "Item")
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date fechaLlegada;
	@Temporal(TemporalType.DATE)
	private Date fechaExpiracion;
	private Double costo;
	private String name;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Date getFechaLlegada(){
		return fechaLlegada;
	}
	
	public void setFechaLlegada(Date fechaLlegada){
		this.fechaLlegada = fechaLlegada;
	}
	public Date getFechaExpiracion(){
		return fechaExpiracion;
	}
	
	public void setFechaExpiracion(Date fechaExpiracion){
		this.fechaExpiracion = fechaExpiracion;
	}
	public Double getCosto(){
		return costo;
	}
	
	public void setCosto(Double costo){
		this.costo = costo;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}