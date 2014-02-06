
package co.edu.uniandes.csw.product.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ProductDTO {

	private Long id;
	private String name;
	private Long value;

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
	public Long getValue() {
		return value;
	}
 
	public void setValue(Long value) {
		this.value = value;
	}
	
}