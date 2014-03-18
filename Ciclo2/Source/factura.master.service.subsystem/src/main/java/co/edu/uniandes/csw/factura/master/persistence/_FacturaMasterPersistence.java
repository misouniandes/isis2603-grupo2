package co.edu.uniandes.csw.factura.master.persistence;
import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemFacturaEntity;
import co.edu.uniandes.csw.itemfactura.persistence.converter.ItemFacturaConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api._IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _FacturaMasterPersistence implements _IFacturaMasterPersistence {

    @PersistenceContext(unitName = "FacturaMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IFacturaPersistence facturaPersistence;  

    public FacturaMasterDTO getFactura(Long facturaId) {
        FacturaMasterDTO facturaMasterDTO = new FacturaMasterDTO();
        FacturaDTO factura = facturaPersistence.getFactura(facturaId);
        facturaMasterDTO.setFacturaEntity(factura);
        facturaMasterDTO.setListItemFactura(getItemFacturaListForFactura(facturaId));
        return facturaMasterDTO;
    }

    public FacturaItemFacturaEntity createFacturaItemFactura(FacturaItemFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaItemFactura(Long facturaId, Long itemFacturaId) {
        Query q = entityManager.createNamedQuery("FacturaItemFacturaEntity.deleteFacturaItemFactura");
        q.setParameter("facturaId", facturaId);
        q.setParameter("itemFacturaId", itemFacturaId);
        q.executeUpdate();
    }

    public List<ItemFacturaDTO> getItemFacturaListForFactura(Long facturaId) {
        ArrayList<ItemFacturaDTO> resp = new ArrayList<ItemFacturaDTO>();
        Query q = entityManager.createNamedQuery("FacturaItemFacturaEntity.getItemFacturaListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaItemFacturaEntity> qResult =  q.getResultList();
        for (FacturaItemFacturaEntity facturaItemFacturaEntity : qResult) { 
            if(facturaItemFacturaEntity.getItemFacturaEntity()==null){
                entityManager.refresh(facturaItemFacturaEntity);
            }
            resp.add(ItemFacturaConverter.entity2PersistenceDTO(facturaItemFacturaEntity.getItemFacturaEntity()));
        }
        return resp;
    }

}
