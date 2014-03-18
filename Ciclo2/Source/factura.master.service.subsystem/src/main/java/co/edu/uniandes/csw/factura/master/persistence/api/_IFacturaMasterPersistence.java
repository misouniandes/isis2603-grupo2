package co.edu.uniandes.csw.factura.master.persistence.api;

import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemFacturaEntity;
import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import java.util.List;

public interface _IFacturaMasterPersistence {

    public FacturaItemFacturaEntity createFacturaItemFactura(FacturaItemFacturaEntity entity);

    public void deleteFacturaItemFactura(Long facturaId, Long itemFacturaId);
    
    public List<ItemFacturaDTO> getItemFacturaListForFactura(Long facturaId);
    
    public FacturaMasterDTO getFactura(Long facturaId);

}
