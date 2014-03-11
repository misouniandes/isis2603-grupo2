
package co.edu.uniandes.csw.productocomp.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ProductoCompDTO {

	private Integer terminado;
	private Long id;
	private String name;
	private Integer cantidadDisp;
	private Integer canitdadEnProc;
	private Integer tiempoEspera;
	private Double costoPromedio;
	private String lugar;
	private Integer cantidadMin;
	private Integer cantidadMax;

	public Integer getTerminado() {
		return terminado;
	}
 
	public void setTerminado(Integer terminado) {
		this.terminado = terminado;
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
	public Integer getCantidadDisp() {
		return cantidadDisp;
	}
 
	public void setCantidadDisp(Integer cantidaddisp) {
		this.cantidadDisp = cantidaddisp;
	}
	public Integer getCanitdadEnProc() {
		return canitdadEnProc;
	}
 
	public void setCanitdadEnProc(Integer canitdadenproc) {
		this.canitdadEnProc = canitdadenproc;
	}
	public Integer getTiempoEspera() {
		return tiempoEspera;
	}
 
	public void setTiempoEspera(Integer tiempoespera) {
		this.tiempoEspera = tiempoespera;
	}
	public Double getCostoPromedio() {
		return costoPromedio;
	}
 
	public void setCostoPromedio(Double costopromedio) {
		this.costoPromedio = costopromedio;
	}
	public String getLugar() {
		return lugar;
	}
 
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Integer getCantidadMin() {
		return cantidadMin;
	}
 
	public void setCantidadMin(Integer cantidadmin) {
		this.cantidadMin = cantidadmin;
	}
	public Integer getCantidadMax() {
		return cantidadMax;
	}
 
	public void setCantidadMax(Integer cantidadmax) {
		this.cantidadMax = cantidadmax;
	}
	
}