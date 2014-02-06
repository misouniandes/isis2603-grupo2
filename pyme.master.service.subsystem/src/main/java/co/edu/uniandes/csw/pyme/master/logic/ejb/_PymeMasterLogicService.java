package co.edu.uniandes.csw.pyme.master.logic.ejb;

import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.persistence.api.IClientPersistence;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.persistence.api.IProductPersistence;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.api._IPymeMasterLogicService;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.master.persistence.api.IPymeMasterPersistence;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClientEntity;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeProductEntity;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import javax.inject.Inject;

public abstract class _PymeMasterLogicService implements _IPymeMasterLogicService {

    @Inject
    protected IPymePersistence pymePersistance;
    @Inject
    protected IPymeMasterPersistence pymeMasterPersistance;
    @Inject
    protected IClientPersistence clientPersistance;
    @Inject
    protected IProductPersistence productPersistance;

    public PymeMasterDTO createMasterPyme(PymeMasterDTO pyme) {
        PymeDTO persistedPymeDTO = pymePersistance.createPyme(pyme.getPymeEntity());
        if (pyme.getCreateClient() != null) {
            for (ClientDTO clientDTO : pyme.getCreateClient()) {
                ClientDTO persistedClientDTO = clientPersistance.createClient(clientDTO);
                PymeClientEntity pymeClientEntity = new PymeClientEntity(persistedPymeDTO.getId(), persistedClientDTO.getId());
                pymeMasterPersistance.createPymeClient(pymeClientEntity);
            }
        }
        if (pyme.getCreateProduct() != null) {
            for (ProductDTO productDTO : pyme.getCreateProduct()) {
                ProductDTO persistedProductDTO = productPersistance.createProduct(productDTO);
                PymeProductEntity pymeProductEntity = new PymeProductEntity(persistedPymeDTO.getId(), persistedProductDTO.getId());
                pymeMasterPersistance.createPymeProduct(pymeProductEntity);
            }
        }
        return pyme;
    }

    public PymeMasterDTO getMasterPyme(Long id) {
        return pymeMasterPersistance.getPyme(id);
    }

    public void deleteMasterPyme(Long id) {
        pymePersistance.deletePyme(id);
    }

    public void updateMasterPyme(PymeMasterDTO pyme) {
        pymePersistance.updatePyme(pyme.getPymeEntity());

        //---- FOR RELATIONSHIP
        // persist new client
        if (pyme.getCreateClient() != null) {
            for (ClientDTO clientDTO : pyme.getCreateClient()) {
                ClientDTO persistedClientDTO = clientPersistance.createClient(clientDTO);
                PymeClientEntity pymeClientEntity = new PymeClientEntity(pyme.getPymeEntity().getId(), persistedClientDTO.getId());
                pymeMasterPersistance.createPymeClient(pymeClientEntity);
            }
        }
        // update client
        if (pyme.getUpdateClient() != null) {
            for (ClientDTO clientDTO : pyme.getUpdateClient()) {
                clientPersistance.updateClient(clientDTO);
            }
        }
        // delete client
        if (pyme.getDeleteClient() != null) {
            for (ClientDTO clientDTO : pyme.getDeleteClient()) {
                pymeMasterPersistance.deletePymeClient(pyme.getPymeEntity().getId(), clientDTO.getId());
                clientPersistance.deleteClient(clientDTO.getId());
            }
        }
        // persist new product
        if (pyme.getCreateProduct() != null) {
            for (ProductDTO productDTO : pyme.getCreateProduct()) {
                ProductDTO persistedProductDTO = productPersistance.createProduct(productDTO);
                PymeProductEntity pymeProductEntity = new PymeProductEntity(pyme.getPymeEntity().getId(), persistedProductDTO.getId());
                pymeMasterPersistance.createPymeProduct(pymeProductEntity);
            }
        }
        // update product
        if (pyme.getUpdateProduct() != null) {
            for (ProductDTO productDTO : pyme.getUpdateProduct()) {
                productPersistance.updateProduct(productDTO);
            }
        }
        // delete product
        if (pyme.getDeleteProduct() != null) {
            for (ProductDTO productDTO : pyme.getDeleteProduct()) {
                pymeMasterPersistance.deletePymeProduct(pyme.getPymeEntity().getId(), productDTO.getId());
                productPersistance.deleteProduct(productDTO.getId());
            }
        }
    }
}
