
package co.edu.uniandes.csw.client.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ClientEntity {

	@Id
	@GeneratedValue(generator = "Client")
	private Long id;
	private String name;
	private String cc;

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
	public String getCc(){
		return cc;
	}
	
	public void setCc(String cc){
		this.cc = cc;
	}
}