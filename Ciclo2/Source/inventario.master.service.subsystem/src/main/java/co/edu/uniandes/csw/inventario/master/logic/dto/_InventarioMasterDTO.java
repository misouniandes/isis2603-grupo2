package co.edu.uniandes.csw.inventario.master.logic.dto;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.inventario.logic.dto.InventarioDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _InventarioMasterDTO {

 
    protected InventarioDTO inventarioEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public InventarioDTO getInventarioEntity() {
        return inventarioEntity;
    }

    public void setInventarioEntity(InventarioDTO inventarioEntity) {
        this.inventarioEntity = inventarioEntity;
    }
    
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
    public List<ProductoDTO> createProducto;
    public List<ProductoDTO> updateProducto;
    public List<ProductoDTO> deleteProducto;
    public List<ProductoDTO> listProducto;	
	
	
	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
    public List<ProductoDTO> getCreateProducto(){ return createProducto; };
    public void setCreateProducto(List<ProductoDTO> createProducto){ this.createProducto=createProducto; };
    public List<ProductoDTO> getUpdateProducto(){ return updateProducto; };
    public void setUpdateProducto(List<ProductoDTO> updateProducto){ this.updateProducto=updateProducto; };
    public List<ProductoDTO> getDeleteProducto(){ return deleteProducto; };
    public void setDeleteProducto(List<ProductoDTO> deleteProducto){ this.deleteProducto=deleteProducto; };
    public List<ProductoDTO> getListProducto(){ return listProducto; };
    public void setListProducto(List<ProductoDTO> listProducto){ this.listProducto=listProducto; };	
	
	
}

