
package co.edu.uniandes.csw.inventario.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _InventarioEntity {

	@Id
	@GeneratedValue(generator = "Inventario")
	private Long id;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
}