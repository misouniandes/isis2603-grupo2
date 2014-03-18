package co.edu.uniandes.csw.productocomp.master.logic.dto;

import co.edu.uniandes.csw.necesidad.logic.dto.NecesidadDTO;
import co.edu.uniandes.csw.productocomp.logic.dto.ProductoCompDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _ProductoCompMasterDTO {

 
    protected ProductoCompDTO productocompEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductoCompDTO getProductoCompEntity() {
        return productocompEntity;
    }

    public void setProductoCompEntity(ProductoCompDTO productocompEntity) {
        this.productocompEntity = productocompEntity;
    }
    
    public List<NecesidadDTO> createNecesidad;
    public List<NecesidadDTO> updateNecesidad;
    public List<NecesidadDTO> deleteNecesidad;
    public List<NecesidadDTO> listNecesidad;	
	
	
	
    public List<NecesidadDTO> getCreateNecesidad(){ return createNecesidad; };
    public void setCreateNecesidad(List<NecesidadDTO> createNecesidad){ this.createNecesidad=createNecesidad; };
    public List<NecesidadDTO> getUpdateNecesidad(){ return updateNecesidad; };
    public void setUpdateNecesidad(List<NecesidadDTO> updateNecesidad){ this.updateNecesidad=updateNecesidad; };
    public List<NecesidadDTO> getDeleteNecesidad(){ return deleteNecesidad; };
    public void setDeleteNecesidad(List<NecesidadDTO> deleteNecesidad){ this.deleteNecesidad=deleteNecesidad; };
    public List<NecesidadDTO> getListNecesidad(){ return listNecesidad; };
    public void setListNecesidad(List<NecesidadDTO> listNecesidad){ this.listNecesidad=listNecesidad; };	
	
	
}

