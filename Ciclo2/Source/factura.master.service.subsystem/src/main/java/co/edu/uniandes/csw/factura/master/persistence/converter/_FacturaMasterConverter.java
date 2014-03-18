package co.edu.uniandes.csw.factura.master.persistence.converter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemFacturaEntity;
import co.edu.uniandes.csw.itemfactura.logic.dto.ItemFacturaDTO;
import co.edu.uniandes.csw.itemfactura.persistence.converter.ItemFacturaConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _FacturaMasterConverter {

    public static FacturaMasterDTO entity2PersistenceDTO(FacturaEntity facturaEntity 
    ,List<FacturaItemFacturaEntity> facturaItemFacturaEntity 
    ) {
        FacturaDTO facturaDTO = FacturaConverter.entity2PersistenceDTO(facturaEntity);
        ArrayList<ItemFacturaDTO> itemFacturaEntities = new ArrayList<ItemFacturaDTO>(facturaItemFacturaEntity.size());
        for (FacturaItemFacturaEntity facturaItemFactura : facturaItemFacturaEntity) {
            itemFacturaEntities.add(ItemFacturaConverter.entity2PersistenceDTO(facturaItemFactura.getItemFacturaEntity()));
        }
        FacturaMasterDTO respDTO  = new FacturaMasterDTO();
        respDTO.setFacturaEntity(facturaDTO);
        respDTO.setListItemFactura(itemFacturaEntities);
        return respDTO;
    }

}