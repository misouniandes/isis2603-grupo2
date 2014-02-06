package co.edu.uniandes.csw.pyme.master.logic.dto;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _PymeMasterDTO {

 
    protected PymeDTO pymeEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public PymeDTO getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeDTO pymeEntity) {
        this.pymeEntity = pymeEntity;
    }
    
    public List<ClientDTO> createClient;
    public List<ClientDTO> updateClient;
    public List<ClientDTO> deleteClient;
    public List<ClientDTO> listClient;	
    public List<ProductDTO> createProduct;
    public List<ProductDTO> updateProduct;
    public List<ProductDTO> deleteProduct;
    public List<ProductDTO> listProduct;	
	
	
	
    public List<ClientDTO> getCreateClient(){ return createClient; };
    public void setCreateClient(List<ClientDTO> createClient){ this.createClient=createClient; };
    public List<ClientDTO> getUpdateClient(){ return updateClient; };
    public void setUpdateClient(List<ClientDTO> updateClient){ this.updateClient=updateClient; };
    public List<ClientDTO> getDeleteClient(){ return deleteClient; };
    public void setDeleteClient(List<ClientDTO> deleteClient){ this.deleteClient=deleteClient; };
    public List<ClientDTO> getListClient(){ return listClient; };
    public void setListClient(List<ClientDTO> listClient){ this.listClient=listClient; };	
    public List<ProductDTO> getCreateProduct(){ return createProduct; };
    public void setCreateProduct(List<ProductDTO> createProduct){ this.createProduct=createProduct; };
    public List<ProductDTO> getUpdateProduct(){ return updateProduct; };
    public void setUpdateProduct(List<ProductDTO> updateProduct){ this.updateProduct=updateProduct; };
    public List<ProductDTO> getDeleteProduct(){ return deleteProduct; };
    public void setDeleteProduct(List<ProductDTO> deleteProduct){ this.deleteProduct=deleteProduct; };
    public List<ProductDTO> getListProduct(){ return listProduct; };
    public void setListProduct(List<ProductDTO> listProduct){ this.listProduct=listProduct; };	
	
	
}

