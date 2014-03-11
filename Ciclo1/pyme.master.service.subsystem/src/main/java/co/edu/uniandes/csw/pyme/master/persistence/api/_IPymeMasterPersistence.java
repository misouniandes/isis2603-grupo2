package co.edu.uniandes.csw.pyme.master.persistence.api;

import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClientEntity;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeProductEntity;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import java.util.List;

public interface _IPymeMasterPersistence {

    public PymeClientEntity createPymeClient(PymeClientEntity entity);

    public void deletePymeClient(Long pymeId, Long clientId);
    
    public List<ClientDTO> getClientListForPyme(Long pymeId);
    public PymeProductEntity createPymeProduct(PymeProductEntity entity);

    public void deletePymeProduct(Long pymeId, Long productId);
    
    public List<ProductDTO> getProductListForPyme(Long pymeId);
    
    public PymeMasterDTO getPyme(Long pymeId);

}
