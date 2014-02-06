package co.edu.uniandes.csw.pyme.master.persistence.converter;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClientEntity;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.client.persistence.converter.ClientConverter;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeProductEntity;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.product.persistence.converter.ProductConverter;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.persistence.converter.PymeConverter;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _PymeMasterConverter {

    public static PymeMasterDTO entity2PersistenceDTO(PymeEntity pymeEntity 
    ,List<PymeClientEntity> pymeClientEntity 
    ,List<PymeProductEntity> pymeProductEntity 
    ) {
        PymeDTO pymeDTO = PymeConverter.entity2PersistenceDTO(pymeEntity);
        ArrayList<ClientDTO> clientEntities = new ArrayList<ClientDTO>(pymeClientEntity.size());
        for (PymeClientEntity pymeClient : pymeClientEntity) {
            clientEntities.add(ClientConverter.entity2PersistenceDTO(pymeClient.getClientEntity()));
        }
        ArrayList<ProductDTO> productEntities = new ArrayList<ProductDTO>(pymeProductEntity.size());
        for (PymeProductEntity pymeProduct : pymeProductEntity) {
            productEntities.add(ProductConverter.entity2PersistenceDTO(pymeProduct.getProductEntity()));
        }
        PymeMasterDTO respDTO  = new PymeMasterDTO();
        respDTO.setPymeEntity(pymeDTO);
        respDTO.setListClient(clientEntities);
        respDTO.setListProduct(productEntities);
        return respDTO;
    }

}