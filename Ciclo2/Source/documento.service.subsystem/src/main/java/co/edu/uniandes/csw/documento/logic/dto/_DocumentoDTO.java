
package co.edu.uniandes.csw.documento.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _DocumentoDTO {

	private String type;
	private Date fecha;
	private Long id;
	private String autor;
	private String descripcion;
	private String name;

	public String getType() {
		return type;
	}
 
	public void setType(String type) {
		this.type = type;
	}
	public Date getFecha() {
		return fecha;
	}
 
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
 
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDescripcion() {
		return descripcion;
	}
 
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
}