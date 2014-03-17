package co.edu.uniandes.csw.factura.master.logic.dto;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _FacturaMasterDTO {

 
    protected FacturaDTO facturaEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public FacturaDTO getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaDTO facturaEntity) {
        this.facturaEntity = facturaEntity;
    }
    
    public List<ItemFacturaDTO> createItemFactura;
    public List<ItemFacturaDTO> updateItemFactura;
    public List<ItemFacturaDTO> deleteItemFactura;
    public List<ItemFacturaDTO> listItemFactura;	
	
	
	
    public List<ItemFacturaDTO> getCreateItemFactura(){ return createItemFactura; };
    public void setCreateItemFactura(List<ItemFacturaDTO> createItemFactura){ this.createItemFactura=createItemFactura; };
    public List<ItemFacturaDTO> getUpdateItemFactura(){ return updateItemFactura; };
    public void setUpdateItemFactura(List<ItemFacturaDTO> updateItemFactura){ this.updateItemFactura=updateItemFactura; };
    public List<ItemFacturaDTO> getDeleteItemFactura(){ return deleteItemFactura; };
    public void setDeleteItemFactura(List<ItemFacturaDTO> deleteItemFactura){ this.deleteItemFactura=deleteItemFactura; };
    public List<ItemFacturaDTO> getListItemFactura(){ return listItemFactura; };
    public void setListItemFactura(List<ItemFacturaDTO> listItemFactura){ this.listItemFactura=listItemFactura; };	
	
	
}

