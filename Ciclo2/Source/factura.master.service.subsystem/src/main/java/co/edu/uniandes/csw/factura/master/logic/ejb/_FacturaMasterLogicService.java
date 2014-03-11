package co.edu.uniandes.csw.factura.master.logic.ejb;

import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.persistence.api.IItemFacturaPersistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.api._IFacturaMasterLogicService;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api.IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import javax.inject.Inject;

public abstract class _FacturaMasterLogicService implements _IFacturaMasterLogicService {

    @Inject
    protected IFacturaPersistence facturaPersistance;
    @Inject
    protected IFacturaMasterPersistence facturaMasterPersistance;
    @Inject
    protected IItemFacturaPersistence itemFacturaPersistance;

    public FacturaMasterDTO createMasterFactura(FacturaMasterDTO factura) {
        FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(factura.getFacturaEntity());
        if (factura.getCreateItemFactura() != null) {
            for (ItemFacturaDTO itemFacturaDTO : factura.getCreateItemFactura()) {
                ItemFacturaDTO persistedItemFacturaDTO = itemFacturaPersistance.createItemFactura(itemFacturaDTO);
                FacturaItemFacturaEntity facturaItemFacturaEntity = new FacturaItemFacturaEntity(persistedFacturaDTO.getId(), persistedItemFacturaDTO.getId());
                facturaMasterPersistance.createFacturaItemFactura(facturaItemFacturaEntity);
            }
        }
        return factura;
    }

    public FacturaMasterDTO getMasterFactura(Long id) {
        return facturaMasterPersistance.getFactura(id);
    }

    public void deleteMasterFactura(Long id) {
        facturaPersistance.deleteFactura(id);
    }

    public void updateMasterFactura(FacturaMasterDTO factura) {
        facturaPersistance.updateFactura(factura.getFacturaEntity());

        //---- FOR RELATIONSHIP
        // persist new itemFactura
        if (factura.getCreateItemFactura() != null) {
            for (ItemFacturaDTO itemFacturaDTO : factura.getCreateItemFactura()) {
                ItemFacturaDTO persistedItemFacturaDTO = itemFacturaPersistance.createItemFactura(itemFacturaDTO);
                FacturaItemFacturaEntity facturaItemFacturaEntity = new FacturaItemFacturaEntity(factura.getFacturaEntity().getId(), persistedItemFacturaDTO.getId());
                facturaMasterPersistance.createFacturaItemFactura(facturaItemFacturaEntity);
            }
        }
        // update itemFactura
        if (factura.getUpdateItemFactura() != null) {
            for (ItemFacturaDTO itemFacturaDTO : factura.getUpdateItemFactura()) {
                itemFacturaPersistance.updateItemFactura(itemFacturaDTO);
            }
        }
        // delete itemFactura
        if (factura.getDeleteItemFactura() != null) {
            for (ItemFacturaDTO itemFacturaDTO : factura.getDeleteItemFactura()) {
                facturaMasterPersistance.deleteFacturaItemFactura(factura.getFacturaEntity().getId(), itemFacturaDTO.getId());
                itemFacturaPersistance.deleteItemFactura(itemFacturaDTO.getId());
            }
        }
    }
}
