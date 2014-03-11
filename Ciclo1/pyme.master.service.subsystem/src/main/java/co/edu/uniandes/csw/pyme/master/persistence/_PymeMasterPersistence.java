package co.edu.uniandes.csw.pyme.master.persistence;
import co.edu.uniandes.csw.client.logic.dto.ClientDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeClientEntity;
import co.edu.uniandes.csw.client.persistence.converter.ClientConverter;
import co.edu.uniandes.csw.product.logic.dto.ProductDTO;
import co.edu.uniandes.csw.pyme.master.persistence.entity.PymeProductEntity;
import co.edu.uniandes.csw.product.persistence.converter.ProductConverter;
import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import co.edu.uniandes.csw.pyme.master.logic.dto.PymeMasterDTO;
import co.edu.uniandes.csw.pyme.master.persistence.api._IPymeMasterPersistence;
import co.edu.uniandes.csw.pyme.persistence.api.IPymePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _PymeMasterPersistence implements _IPymeMasterPersistence {

    @PersistenceContext(unitName = "PymeMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IPymePersistence pymePersistence;  

    public PymeMasterDTO getPyme(Long pymeId) {
        PymeMasterDTO pymeMasterDTO = new PymeMasterDTO();
        PymeDTO pyme = pymePersistence.getPyme(pymeId);
        pymeMasterDTO.setPymeEntity(pyme);
        pymeMasterDTO.setListClient(getClientListForPyme(pymeId));
        pymeMasterDTO.setListProduct(getProductListForPyme(pymeId));
        return pymeMasterDTO;
    }

    public PymeClientEntity createPymeClient(PymeClientEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deletePymeClient(Long pymeId, Long clientId) {
        Query q = entityManager.createNamedQuery("PymeClientEntity.deletePymeClient");
        q.setParameter("pymeId", pymeId);
        q.setParameter("clientId", clientId);
        q.executeUpdate();
    }

    public List<ClientDTO> getClientListForPyme(Long pymeId) {
        ArrayList<ClientDTO> resp = new ArrayList<ClientDTO>();
        Query q = entityManager.createNamedQuery("PymeClientEntity.getClientListForPyme");
        q.setParameter("pymeId", pymeId);
        List<PymeClientEntity> qResult =  q.getResultList();
        for (PymeClientEntity pymeClientEntity : qResult) { 
            if(pymeClientEntity.getClientEntity()==null){
                entityManager.refresh(pymeClientEntity);
            }
            resp.add(ClientConverter.entity2PersistenceDTO(pymeClientEntity.getClientEntity()));
        }
        return resp;
    }
    public PymeProductEntity createPymeProduct(PymeProductEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deletePymeProduct(Long pymeId, Long productId) {
        Query q = entityManager.createNamedQuery("PymeProductEntity.deletePymeProduct");
        q.setParameter("pymeId", pymeId);
        q.setParameter("productId", productId);
        q.executeUpdate();
    }

    public List<ProductDTO> getProductListForPyme(Long pymeId) {
        ArrayList<ProductDTO> resp = new ArrayList<ProductDTO>();
        Query q = entityManager.createNamedQuery("PymeProductEntity.getProductListForPyme");
        q.setParameter("pymeId", pymeId);
        List<PymeProductEntity> qResult =  q.getResultList();
        for (PymeProductEntity pymeProductEntity : qResult) { 
            if(pymeProductEntity.getProductEntity()==null){
                entityManager.refresh(pymeProductEntity);
            }
            resp.add(ProductConverter.entity2PersistenceDTO(pymeProductEntity.getProductEntity()));
        }
        return resp;
    }

}
