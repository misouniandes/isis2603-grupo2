
package co.edu.uniandes.csw.item.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ItemDTO {

	private Long id;
	private Date fechaLlegada;
	private Date fechaExpiracion;
	private Double costo;
	private String name;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaLlegada() {
		return fechaLlegada;
	}
 
	public void setFechaLlegada(Date fechallegada) {
		this.fechaLlegada = fechallegada;
	}
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}
 
	public void setFechaExpiracion(Date fechaexpiracion) {
		this.fechaExpiracion = fechaexpiracion;
	}
	public Double getCosto() {
		return costo;
	}
 
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
}